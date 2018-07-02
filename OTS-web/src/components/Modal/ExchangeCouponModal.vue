<template>

  <el-dialog
    class="modal-wrapper"
    title="优惠券兑换"
    :visible="exchangeCouponModal"
    @close="handleClose"
    width="50%">

    <el-radio-group v-model="radio">
      <el-radio :label="1000"><span><span class="number">1000</span> 积分 兑换 <span
        class="number">9折 </span>优惠券</span>
      </el-radio>
      <br/>
      <el-radio :label="2000"><span><span class="number">2000</span> 积分 兑换 <span
        class="number">8折 </span>优惠券</span>
      </el-radio>
      <br/>
      <el-radio :label="4000"><span><span class="number">4000</span> 积分 兑换 <span
        class="number">7折 </span>优惠券</span>
      </el-radio>
    </el-radio-group>

    <span slot="footer" class="dialog-footer">
      <el-button size="medium" @click="handleClose">取 消</el-button>
      <el-button size="medium" type="primary" @click="handleExchange">确 定</el-button>
    </span>
  </el-dialog>

</template>

<script>
  import {Dialog, Button, RadioGroup, Radio, Message} from 'element-ui'
  import {router} from '../../main'
  import {mapState, mapActions, mapMutations} from 'vuex'

  export default {
    name: 'exchange-coupon-modal',
    components: {
      elDialog: Dialog,
      elButton: Button,
      elRadioGroup: RadioGroup,
      elRadio: Radio,
      Message
    },
    data() {
      return {
        radio: 1000,
        points: [1000, 2000, 4000],
        discount: [0.9, 0.8, 0.7]
      }
    },
    computed: {
      ...mapState('auth', {
        user: state => state.currentUser,
        exchangeCouponModal: state => state.exchangeCouponModal
      })
    },
    methods: {
      ...mapActions('auth', [
        'exchangeCoupon',
        'getUserCoupons',
        'fetchCurrentUser'
      ]),
      ...mapMutations('auth', [
        'saveExchangeCouponModal'
      ]),
      handleClose() {
        this.saveExchangeCouponModal(false)
      },
      handleExchange() {

        if (this.user.point < this.radio) {
          Message.error('积分不足，无法兑换！')
        } else {
          this.exchangeCoupon({
            info: {
              userId: this.user.userId,
              point: this.radio,
              discount: this.discount[this.points.indexOf(this.radio)]
            },
            onSuccess: (success) => {
              this.saveExchangeCouponModal(false)
              this.getUserCoupons()
              this.fetchCurrentUser({})
              Message.success(success)
            },
            onError: (error) => {
              Message.error(error)
            }
          })
        }
      }
    }
  }

</script>

<style scoped src="./ExchangeCouponModal.css"></style>
