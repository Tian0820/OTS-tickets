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
            <span :style="{fontSize: '24px', color: '#E9A038'}">2000</span>
            元</p>
          <p class="balance">账户余额：{{user.balance}} 元</p>
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
      })
    },
    methods: {
      handlePay() {

      },
      closeBox() {
        this.$modal.hide('pay-modal')
      }
    }
  }

</script>

<style scoped src="./PayModal.css"></style>
