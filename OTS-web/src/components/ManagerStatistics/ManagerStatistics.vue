<template>

  <div class="manager-statistics-wrapper">
    <venue-statistics v-if="venueStatistics" :venueStatistics="venueStatistics"></venue-statistics>
    <user-statistics></user-statistics>
    <o-t-s-finance></o-t-s-finance>

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
        venueStatistics: state => state.venueStatistics
      })
    },
    data() {
      return {}
    },
    methods: {}
  }

</script>

<style scoped src="./ManagerStatistics.css"></style>
