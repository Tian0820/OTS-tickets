<template>

  <div class="my-order-wrapper">
    <div-header :header="'我的订单'"></div-header>

    <p>您共有
      <span>{{userOrders.length}}</span> 笔订单，
      其中 <span>{{finishNum}}</span> 笔已完成，
      <span>{{payNum}}</span> 笔待付款，
      <span>{{distributeNum}}</span> 笔待开票
    </p>

    <single-order v-for="item in userOrders" :order="item" :key="item.id"></single-order>

  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import SingleOrder from '../Order/SingleOrder.vue'
  import {router} from '../../main'
  import {mapState} from 'vuex'

  export default {
    name: 'my-user-order',
    components: {
      DivHeader,
      SingleOrder
    },
    props: ['userOrders'],
    data() {
      let finishNum = 0
      let payNum = 0
      let distributeNum = 0
      this.userOrders.forEach(order => {
        if (order.seats === null || order.seats.length === 0 && order.type === '分配') {
          distributeNum++
        }
        if (order.state === '已完成') {
          finishNum++
        } else if (order.state === '未付款') {
          payNum++
        }
      })
      return {
        distributeNum: distributeNum,
        payNum: payNum,
        finishNum: finishNum
      }
    },
    methods: {}
  }

</script>

<style scoped src="./MyOrder.css"></style>
