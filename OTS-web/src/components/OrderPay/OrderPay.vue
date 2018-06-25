<template>

  <div>

    <div class="order-pay-wrapper">
      <div-header :header="'支付订单'"></div-header>

      <div class="time-wrapper">
        <span class="time-label">剩余时间：</span>
        <span class="time">15:00</span>
      </div>

      <div class="order-wrapper">
        <div class="poster-wrapper" :style="{ backgroundImage: 'url(' + posterUrl + ')'}">
        </div>

        <div class="info-wrapper">

          <p class="show-title">{{currentOrder.showPlan.name}}</p>
          <p class="show-info">类型：{{currentOrder.showPlan.type}}</p>
          <p class="show-info">时间：{{currentOrder.showPlan.time}}</p>
          <p class="show-info">艺人：{{currentOrder.showPlan.star}}</p>
          <p class="show-info">座位：{{currentOrder.seats.length === 0 ? '待分配': currentOrder.seats.join(', ')}}</p>

          <p class="show-info">票价：{{currentOrder.price}} 元</p>
        </div>

      </div>
    </div>
  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import {Message} from 'element-ui'
  import {store, router} from '../../main'
  import {mapActions, mapMutations, mapState} from 'vuex'

  export default {
    name: 'order-pay',
    components: {
      DivHeader,
      Message
    },
    props: ['currentOrder'],
    data() {
      let name = 'poster.jpg'
      return {
        posterUrl: require('../../assets/img/' + name),
      }
    },
    computed: {
      ...mapState('auth', {
        user: state => state.currentUser
      })
    },
    methods: {},
  }
</script>

<style scoped src="./OrderPay.css"></style>
