<template>
  <div class="single-order-wrapper">
    <div class="img-wrapper" :style="{ backgroundImage: 'url(' + posterUrl + ')'}">
    </div>

    <div class="info-wrapper">
      <h1>{{order.showPlan.name}}</h1>
      <el-tag :type="order.state === '已过期' ? 'danger' : 'success'">{{order.state}}</el-tag>
      <span class="order-time">{{order.createTime}}</span>
      <br/>

      <button v-if="order.state === '已付款' && user !== null">退款</button>

      <p>时间：{{order.showPlan.time}}</p><br/>
      <p>地点：{{order.showPlan.venue.city}}   {{order.showPlan.venue.venueName}}    {{order.showPlan.venue.address}}</p>
      <br/>
      <p>座位：{{orderSeats.join(', ')}}</p><br/>
      <p>价格：{{order.price}} 元</p>
    </div>

  </div>

</template>

<script>
  import {Tag} from 'element-ui'
  import {mapState} from 'vuex'

  export default {
    name: 'single-order',
    components: {
      elTag: Tag
    },
    props: ['order'],
    computed: {
      ...mapState('auth', {
        user: state => state.currentUser
      })
    },
    data() {
      let name = 'poster.jpg'
      let orderSeats = []
      this.order.seats.forEach(seat => {
        let row = Math.floor((seat.number + 1) % 10) === 0 ? Math.floor((seat.number + 1) / 10) : Math.floor((seat.number + 1) / 10) + 1
        let col = Math.floor((seat.number + 1) % 10) === 0 ? 10 : Math.floor((seat.number + 1) % 10)
        orderSeats.push(seat.area + '区' + row + '排' + col + '座')
      })
      return {
        posterUrl: require('../../assets/img/' + name),
        orderSeats: orderSeats
      }
    },
    methods: {}
  }
</script>

<style scoped src="./SingleOrder.css"></style>
