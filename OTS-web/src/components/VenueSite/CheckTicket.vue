<template>

  <div class="check-ticket-wrapper">

    <div-header :header="'现场检票'"></div-header>

    <div :style="{textAlign: 'center'}">
      <p>请输入订单号：</p>
      <el-input v-model="orderId"></el-input>

      <button @click="handleCheck">检票</button>
    </div>

  </div>

</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import {Input, Message} from 'element-ui'
  import {router} from '../../main'
  import {mapActions} from 'vuex'

  export default {
    name: 'check-ticket',
    components: {
      elInput: Input,
      DivHeader,
      Message
    },
    data() {
      return {
        orderId: ''
      }
    },
    methods: {
      ...mapActions('venue', [
        'checkTicket'
      ]),
      handleCheck() {
        this.checkTicket({
          orderId: this.orderId,
          onSuccess: (success) => {
            Message.success(success)
            this.orderId = ''
          },
          onError: (error) => {
            Message.error(error)
          }
        })
      }
    }
  }

</script>

<style scoped src="./CheckTicket.css"></style>
