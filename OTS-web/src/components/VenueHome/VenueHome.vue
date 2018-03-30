<template>

  <div class="venue-home-wrapper">
    <my-order :orders="orders"></my-order>
    <my-finance v-if="orders&&weeklyStatistics"
                :orders="orders"
                :weeklyStatistics="weeklyStatistics"></my-finance>
  </div>


</template>

<script>
  import {router} from '../../main'
  import MyOrder from '../VenueHome/MyOrder.vue'
  import MyFinance from '../VenueHome/MyFinance.vue'
  import {mapState} from 'vuex'

  export default {
    name: 'venue-home',
    components: {
      MyOrder,
      MyFinance
    },
    computed: {
      ...mapState('venue', {
        orders: state => state.orders,
        weeklyStatistics: state => state.weeklyStatistics
      })
    },
    data() {
      return {}
    },
    methods: {}
  }

</script>

<style scoped src="./VenueHome.css"></style>
