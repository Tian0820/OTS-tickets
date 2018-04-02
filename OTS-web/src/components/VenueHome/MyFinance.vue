<template>

  <div class="my-finance-wrapper">
    <div-header :header="'场馆财务'"></div-header>

    <p>完成订单：{{finishedOrders.length}}</p>
    <p>订单收入：{{currentVenue.balance.toFixed(2)}}</p>
    <p>总订单数量：</p>

    <order-weekly-line-chart v-if="x&&y&&price"
                             :x="x"
                             :y="y"
                             :price="price"></order-weekly-line-chart>

  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import OrderWeeklyLineChart from '../Chart/OrderWeeklyLineChart.vue'
  import {router} from '../../main'
  import {mapState} from 'vuex'

  export default {
    name: 'my-finance',
    components: {
      DivHeader,
      OrderWeeklyLineChart
    },
    props: ['orders', 'weeklyStatistics'],
    computed: {
      ...mapState('venue', {
        currentVenue: state => state.currentVenue
      }),
      finishedOrders: function () {
        let finishedOrders = []
        this.orders.forEach(order => {
          if (order.state === '已完成') {
            finishedOrders.push(order)
          }
        })
        return finishedOrders
      },
    },

    data() {
      let x = []
      let y = []
      let price = []
      this.weeklyStatistics.forEach(order => {
        x.push(order.date)
        y.push(order.orderNum)
        price.push(order.price)
      })
      return {
        x: x,
        y: y,
        price: price
      }
    },
    methods: {}
  }

</script>

<style scoped src="./MyFinance.css"></style>
