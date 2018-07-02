<template>
  <div class="single-order-wrapper">
    <div class="img-wrapper" :style="{ backgroundImage: 'url(' + posterUrl + ')'}">
    </div>

    <div class="info-wrapper">
      <h1>{{order.showPlan.name}}（订单号：{{order.id}}）</h1>
      <p class="order-time">{{order.createTime}}</p>
      <div class="right-top">
        <!--<el-tag size="small" :type="type">{{order.state}}</el-tag>-->
        <span :class="['tag', `tag-${color}`, 'tag-active']">{{order.state}}</span>
      </div>

      <div class="button-wrapper">
        <el-button type="primary" size="small" v-if="order.state === '已付款' && user !== null" @click="handleRefund">退款
        </el-button>
        <el-button type="primary" size="small" v-if="order.state === '未付款' && user !== null" @click="handlePay">付款
        </el-button>
      </div>

      <div class="detail-wrapper">
        <p>时间：{{order.showPlan.time}}</p>
        <p>地点：{{order.showPlan.venue.city}}   {{order.showPlan.venue.venueName}}    {{order.showPlan.venue.address}}</p>
        <p>座位：{{orderSeats.join(', ')}}</p>
        <p>价格：{{order.price}} 元</p>
      </div>
    </div>

  </div>

</template>

<script>
  import {Tag, Button} from 'element-ui'
  import {mapState, mapActions, mapMutations} from 'vuex'
  import {ORDER_TYPE} from '../../constant'
  import {router} from '../../main'

  export default {
    name: 'single-order',
    components: {
      elTag: Tag,
      elButton: Button
    },
    props: ['order'],
    computed: {
      ...mapState('auth', {
        user: state => state.currentUser
      }),
      ...mapState('order', {
        currentOrder: state => state.currentOrder
      }),
    },
    data() {
      let name = this.order.showPlan.id + '.jpeg'
      let orderSeats = []
      if (this.order.seats === null || this.order.seats.length === 0 && this.order.state === '已过期' || this.order.state === '已退款') {
        orderSeats.push('座位已被注销')
      } else if (this.order.seats === null || this.order.seats.length === 0 && this.order.type === '分配') {
        orderSeats.push('座位待分配（演出开始前两周）')
      } else {
        this.order.seats.forEach(seat => {
          let row = Math.floor((seat.number + 1) % 10) === 0 ? Math.floor((seat.number + 1) / 10) : Math.floor((seat.number + 1) / 10) + 1
          let col = Math.floor((seat.number + 1) % 10) === 0 ? 10 : Math.floor((seat.number + 1) % 10)
          orderSeats.push(seat.area + '区' + row + '排' + col + '座')
        })
      }

      return {
        posterUrl: require('../../assets/img/' + name),
        orderSeats: orderSeats,
        color: ORDER_TYPE[this.order.state].color
      }
    },
    methods: {
      ...mapMutations('order', [
        'saveCurrentOrder',
        'saveRefundModal'
      ]),
      handlePay() {
        this.saveCurrentOrder(this.order)
        router.push({name: 'PayPage', params: {orderId: this.currentOrder.id}})
      },
      handleRefund() {
        this.saveCurrentOrder(this.order)
        this.saveRefundModal(true)
      }
    }
  }
</script>

<style scoped src="./SingleOrder.css"></style>
