<template>
  <div class="single-plan-wrapper" @click="goToSellTickets">
    <div class="img-wrapper" :style="{ backgroundImage: 'url(' + posterUrl + ')'}">
    </div>

    <div class="info-wrapper">
      <h1>2018咪咕音乐现场南京站徐佳莹心里学音乐会</h1>
      <br/><br/>

      <p>时间：2018-03-24 19:30</p>
      <p>地点：南京太阳宫</p>
    </div>

  </div>

</template>

<script>
  import {Tag} from 'element-ui'
  import {router} from '../../main'

  export default {
    name: 'single-plan',
    components: {
      elTag: Tag
    },
    data() {
      let name = 'poster.jpg'
      return {
        posterUrl: require('../../assets/img/' + name),
      }
    },
    methods: {
      goToSellTickets() {
        router.push({name: 'ShowDetailPage', params: {showId: 1}})
      }
    }
  }
</script>

<style scoped src="./SinglePlan.css"></style>
