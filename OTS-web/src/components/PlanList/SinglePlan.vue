<template>
  <div class="single-plan-wrapper" @click="goToSellTickets">
    <div class="img-wrapper" :style="{ backgroundImage: 'url(' + posterUrl + ')'}">
    </div>

    <div class="info-wrapper">
      <h1>{{showPlan.name}}</h1>
      <br/><br/>

      <p>时间：{{showPlan.time}}</p>
      <p>地点：{{showPlan.venue.city}}  {{showPlan.venue.venueName}}  {{showPlan.venue.address}}</p>
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
    props: ['showPlan'],
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
