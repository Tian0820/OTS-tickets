<template>
  <div class="single-order-wrapper">
    <div class="img-wrapper" :style="{ backgroundImage: 'url(' + posterUrl + ')'}">
    </div>

    <div class="info-wrapper">
      <h1>{{order.showPlan.name}}</h1>
      <el-tag type="success">{{order.state}}</el-tag>
      <span class="order-time">{{order.createTime}}</span>
      <br/>

      <p>时间：{{order.showPlan.time}}</p>
      <p>地点：{{order.showPlan.venue.city}}   {{order.showPlan.venue.venueName}}    {{order.showPlan.venue.address}}</p>
      <p>座位：{{orderSeats.join(', ')}}</p>
      <p>价格：{{order.price}} 元</p>
    </div>

  </div>

</template>

<script>
  import {Tag} from 'element-ui'

  export default {
    name: 'single-order',
    components: {
      elTag: Tag
    },
    props: ['order'],
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
