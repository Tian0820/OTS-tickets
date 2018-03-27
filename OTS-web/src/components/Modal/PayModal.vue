<template>
  <modal
    name="pay-modal"
    :clickToClose="false"
    :height="350">

    <div class="modal-wrapper">

      <el-button
        icon="el-icon-close"
        @click="closeBox"></el-button>

      <div :style="{padding: '20px'}">
        <h1>支付</h1>

        <div :style="{textAlign: 'center', marginTop: '40px'}">
          <p class="need-to-pay">请在15分钟内完成付款！</p>
          <p class="need-to-pay">需支付
            <span
              :style="{fontSize: '24px', color: '#E9A038'}">{{currentOrder ? currentOrder.price * discount : null}}</span>
            元</p>
          <p class="balance" v-if="user">账户余额：{{user.balance}} 元</p>
          <p class="balance" v-if="venue && siteUser">（会员现场购票95折）</p>
        </div>

        <div class="coupon-wrapper" v-if="user">
          <div-header :header="'我的优惠券'"></div-header>

          <el-radio-group v-model="radio">
            <el-radio v-for="(item, index) in coupons" :key="index" :label="index">
              <single-coupon :coupon="item"></single-coupon>
            </el-radio>
          </el-radio-group>
        </div>


        <button class="confirm-button" @click="handlePay">确认支付</button>

      </div>
    </div>

  </modal>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import SingleCoupon from '../Coupon/SingleCoupon.vue'
  import {Button, Message, RadioGroup, Radio} from 'element-ui'
  import {router, store} from '../../main'
  import {mapState, mapActions} from 'vuex'

  export default {
    name: 'pay-modal',
    components: {
      DivHeader,
      SingleCoupon,
      elButton: Button,
      elRadioGroup: RadioGroup,
      elRadio: Radio,
      Message
    },
    data() {
      return {
        radio: 0,
      }
    },
    computed: {
      ...mapState('auth', {
        user: state => state.currentUser,
        coupons: state => state.userCoupons
      }),
      ...mapState('venue', {
        venue: state => state.currentVenue,
        siteUser: state => state.siteUser
      }),
      ...mapState('order', {
        currentOrder: state => state.currentOrder
      }),
      discount: function () {
        if (this.venue && this.siteUser) {
          console.log(0.95)
          return 0.95
        } else if (this.user) {
          return this.coupons[this.radio].discount
        } else {
          return 1
        }
      }
    },
    methods: {
      ...mapActions('order', [
        'payOrder'
      ]),
      handlePay() {
        if (this.user) {
          if (this.currentOrder.price * this.discount > this.user.balance) {
            Message.error('余额不足！')
          } else {
            this.payOrder({
              order: {
                orderId: this.currentOrder.id,
                price: this.currentOrder.price * this.discount,
                couponId: this.coupons ? this.coupons[this.radio].id : null
              },
              onSuccess: (success) => {
                Message.success(success)
                this.$modal.hide('pay-modal')
              },
              onError: (error) => {
                Message.error(error)
              }
            })
          }
        } else {
          this.payOrder({
            order: {
              orderId: this.currentOrder.id,
              price: this.currentOrder.price * this.discount,
              couponId: this.coupons ? this.coupons[this.radio].id : null
            },
            onSuccess: (success) => {
              Message.success(success)
              this.$modal.hide('pay-modal')
            },
            onError: (error) => {
              Message.error(error)
            }
          })
        }
      },
      closeBox() {
        Message.warning('请在15分钟内完成付款！可前往个人主页付款！')
        this.$modal.hide('pay-modal')
      }
    }
  }

</script>

<style scoped src="./PayModal.css"></style>
