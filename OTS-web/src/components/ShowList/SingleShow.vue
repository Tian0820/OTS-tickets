<template>
    <div class="single-show-wrapper">

      <div class="poster-wrapper"
           :style="{ backgroundImage: 'url(' + posterUrl + ')'}"
           @click="goToShowDetail">
        <div class="show-detail">
          <p>{{show.venue.city}}</p>
          <p>{{show.time}}</p>
        </div>
      </div>

      <div class="show-info-wrapper">
        <p class="show-title" @click="goToShowDetail" :title="show.name">
          <span class="show-type">{{show.type}}</span>{{show.name}}
        </p>
        <!--<p class="show-info">-->
          <!--{{show.venue.city}}&nbsp;&nbsp;&nbsp;&nbsp;{{show.time}}-->
        <!--</p>-->
        <p class="show-price">
          票价：<span :style="{fontWeight: '500', fontSize: '18px', color: '#595959'}">380</span>&nbsp;起
        </p>
      </div>

    </div>
</template>

<script>
  import {Col} from 'element-ui'
  import {router} from '../../main'
  import {mapActions} from 'vuex'

  export default {
    name: 'single-show',
    components: {
    },
    data() {
      let name = 'poster.jpg'
      return {
        posterUrl: require('../../assets/img/' + name)
      }
    },
    props: ['show'],
    methods: {
      goToShowDetail() {
        router.push({name: 'ShowDetailPage', params: {showId: this.show.id}})
      }
    },
  }
</script>

<style scoped src="./SingleShow.css"></style>
