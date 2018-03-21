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
          <p class="need-to-pay">需支付
            <span :style="{fontSize: '24px', color: '#E9A038'}">{{currentOrder ? currentOrder.price : null}}</span>
            元</p>
          <p class="balance" v-if="user">账户余额：{{user.balance}} 元</p>
        </div>


        <button class="confirm-button" @click="handlePay">确认支付</button>

      </div>
    </div>

  </modal>


</template>

<script>
  import {Button, Message} from 'element-ui'
  import {router} from '../../main'
  import {mapState, mapActions} from 'vuex'

  export default {
    name: 'pay-modal',
    components: {
      elButton: Button,
      Message
    },
    data() {
      return {}
    },
    computed: {
      ...mapState('auth', {
        user: state => state.currentUser
      }),
      ...mapState('order', {
        currentOrder: state => state.currentOrder
      })
    },
    methods: {
      ...mapActions('order', [
        'payOrder'
      ]),
      handlePay() {
        if (this.currentOrder.price > this.user.balance) {
          Message.error('余额不足！')
        } else {
          this.payOrder({
            order: this.currentOrder.id,
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
        this.$modal.hide('pay-modal')
      }
    }
  }

</script>

<style scoped src="./PayModal.css"></style>
