<template>

  <div class="manager-statistics-wrapper">
    <venue-statistics v-if="venueStatistics && allShowPlans"
                      :venueStatistics="venueStatistics"
                      :allShowPlans="allShowPlans"></venue-statistics>
    <user-statistics v-if="allUsers" :userStatistics="allUsers"></user-statistics>
    <o-t-s-finance v-if="allOrders" :allOrders="allOrders"></o-t-s-finance>

  </div>


</template>

<script>
  import VenueStatistics from './VenueStatistics.vue'
  import UserStatistics from './UserStatistics.vue'
  import OTSFinance from './OTSFinance.vue'
  import {router} from '../../main'
  import {mapState} from 'vuex'

  export default {
    name: 'manager-statistics',
    components: {
      VenueStatistics,
      UserStatistics,
      OTSFinance
    },

    computed: {
      ...mapState('manager', {
        venueStatistics: state => state.venueStatistics,
        allUsers: state => state.allUsers
      }),
      ...mapState('order', {
        allOrders: state => state.allOrders
      }),
      ...mapState('showPlan', {
        allShowPlans: state => state.allShowPlans
      })
    },
    data() {
      return {}
    },
    methods: {}
  }

</script>

<style scoped src="./ManagerStatistics.css"></style>
