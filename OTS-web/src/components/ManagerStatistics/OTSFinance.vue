<template>

  <div class="OTS-finance-wrapper">
    <div-header :header="'OTS财务情况'"></div-header>


    <p>
      OTS系统共有 <span>{{allOrders.length}}</span> 笔订单&nbsp;&nbsp;&nbsp;
      共盈利 <span>{{(totalProfit * 0.2).toFixed(2)}}</span> 元
    </p>


    <order-num-bar-chart v-if="num_x && num_y" :x="num_x" :y="num_y"></order-num-bar-chart>
    <order-profit-bar-chart v-if="profit_x && profit_y" :x="profit_x" :y="profit_y"></order-profit-bar-chart>

    <button @click="handleClear">一键结算</button>
    <br/>
    <p class="info">（总盈利80%结算至场馆，20%结算至OTS系统）</p>

  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import OrderNumBarChart from '../Chart/OrderNumBarChart.vue'
  import OrderProfitBarChart from '../Chart/OrderProfitBarChart.vue'
  import {Message} from 'element-ui'
  import {router} from '../../main'
  import {mapState, mapActions} from 'vuex'

  export default {
    name: 'OTS-finance-wrapper',
    components: {
      OrderProfitBarChart,
      DivHeader,
      OrderNumBarChart,
      Message
    },
    props: ['allOrders'],
    data() {
      let num_x = ['已完成', '已付款', '未付款', '已退款', '已过期']
      let num_y = [0, 0, 0, 0, 0]
      let profit_y = [0, 0, 0, 0, 0]
      let totalProfit = 0

      this.allOrders.forEach(order => {
        num_y[num_x.indexOf(order.state)]++
        profit_y[num_x.indexOf(order.state)] += order.price
        if (order.state === '已完成') {
          totalProfit += order.price
        }
      })
      return {
        num_x: num_x,
        num_y: num_y,
        profit_y: profit_y,
        profit_x: num_x,
        totalProfit: totalProfit
      }
    },
    methods: {
      ...mapActions('manager', [
        'clearProfit'
      ]),
      handleClear() {
        this.clearProfit({
          onSuccess: (success) => {
            Message.success(success)
          }
        })
      }
    }
  }

</script>

<style scoped src="./OTSFinance.css"></style>
