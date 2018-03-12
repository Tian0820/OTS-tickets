<template>
  <modal
    name="exchange-coupon-modal"
    :clickToClose="true"
    :height="350">

    <div class="modal-wrapper">
      <h1>优惠券兑换</h1>

      <el-radio-group v-model="radio">
        <el-radio :label="1000"><span><span class="number">1000</span> 积分 兑换 <span class="number">9折 </span>优惠券</span>
        </el-radio>
        <br/>
        <el-radio :label="2000"><span><span class="number">2000</span> 积分 兑换 <span class="number">8折 </span>优惠券</span>
        </el-radio>
        <br/>
        <el-radio :label="4000"><span><span class="number">4000</span> 积分 兑换 <span class="number">7折 </span>优惠券</span>
        </el-radio>
      </el-radio-group>

      <button @click="handleExchange">确认兑换</button>

    </div>

  </modal>


</template>

<script>
  import {RadioGroup, Radio, Message} from 'element-ui'
  import {router} from '../../main'
  import {mapState, mapActions} from 'vuex'

  export default {
    name: 'exchange-coupon-modal',
    components: {
      elRadioGroup: RadioGroup,
      elRadio: Radio,
      Message
    },
    data() {
      return {
        radio: 1000
      }
    },
    computed: {
      ...mapState('auth', {
        user: state => state.currentUser
      })
    },
    methods: {
      handleExchange() {
        if (this.user.point < this.radio) {
          Message.error('积分不足，无法兑换！')
        } else {
          Message.success('兑换成功！')
          this.$modal.hide('exchange-coupon-modal')
        }

      }
    }
  }

</script>

<style scoped src="./ExchangeCouponModal.css"></style>
