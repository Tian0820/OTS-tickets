<template>

  <div class="my-finance-wrapper">
    <div-header :header="'场馆财务'"></div-header>

    <p>完成订单：{{finishedOrders.length}}</p>
    <p>订单收入：{{money}}</p>
  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import {router} from '../../main'
  import {mapState} from 'vuex'

  export default {
    name: 'my-finance',
    components: {
      DivHeader
    },
    props: ['orders'],
    computed: {
      finishedOrders: function () {
        let finishedOrders = []
        this.orders.forEach(order => {
          if (order.state === '已完成') {
            finishedOrders.push(order)
          }
        })
        return finishedOrders
      },
      money: function () {
        let money = 0
        this.orders.forEach(order => {
          if (order.state === '已完成') {
            money += order.price
          }
        })
        return money
      }
    },

    data() {
      return {}
    },
    methods: {}
  }

</script>

<style scoped src="./MyFinance.css"></style>
