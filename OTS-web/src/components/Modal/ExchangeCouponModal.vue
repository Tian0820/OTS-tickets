<template>
  <modal
    name="exchange-coupon-modal"
    :clickToClose="true"
    :height="350">

    <div class="modal-wrapper">
      <h1>优惠券兑换</h1>

      <el-radio-group v-model="radio">
        <el-radio :label="1"><span><span class="number">1000</span> 积分 兑换 <span class="number">9折 </span>优惠券</span>
        </el-radio>
        <br/>
        <el-radio :label="2"><span><span class="number">2000</span> 积分 兑换 <span class="number">8折 </span>优惠券</span>
        </el-radio>
        <br/>
        <el-radio :label="3"><span><span class="number">4000</span> 积分 兑换 <span class="number">7折 </span>优惠券</span>
        </el-radio>
      </el-radio-group>

      <button @click="handleExchange">确认兑换</button>

    </div>

  </modal>


</template>

<script>
  import {RadioGroup, Radio, Message} from 'element-ui'
  import {router} from '../../main'

  export default {
    name: 'exchange-coupon-modal',
    components: {
      elRadioGroup: RadioGroup,
      elRadio: Radio,
      Message
    },
    data() {
      return {
        radio: 3
      }
    },
    methods: {
      handleExchange() {
        Message.success('兑换成功！')
        this.$modal.hide('exchange-coupon-modal')
      }
    }
  }

</script>

<style scoped src="./ExchangeCouponModal.css"></style>
