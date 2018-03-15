<template>
  <div class="single-order-wrapper">
    <div class="img-wrapper" :style="{ backgroundImage: 'url(' + posterUrl + ')'}">
    </div>

    <div class="info-wrapper">
      <h1>2018咪咕音乐现场南京站徐佳莹心里学音乐会</h1>
      <el-tag type="success">已成交x</el-tag>
      <span class="order-time">2018-02-14 23:58</span>
      <br/>

      <p>时间：2018-03-24 19:30</p>
      <p>地点：南京太阳宫</p>
      <p>座位：一区2座，一区3座，一区4座</p>
      <p>价格：1000 元</p>
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
    data() {
      let name = 'poster.jpg'
      return {
        posterUrl: require('../../assets/img/' + name),
      }
    },
    methods: {}
  }
</script>

<style scoped src="./SingleOrder.css"></style>
