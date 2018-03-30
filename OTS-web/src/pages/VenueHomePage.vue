<template>
  <div class="body-wrapper">
    <layout>
      <div class="container">

        <venue-home v-if="venue"></venue-home>

      </div>
    </layout>
  </div>

</template>

<script>
  import {Message} from 'element-ui'
  import Layout from '../components/Layout/Layout.vue'
  import VenueHome from '../components/VenueHome/VenueHome.vue'
  import {router, store} from '../main'
  import {mapState} from 'vuex'

  export default {
    name: 'venue-home-page',
    components: {
      Layout,
      VenueHome
    },
    data() {
      return {}
    },
    computed: {
      ...mapState('venue', {
        venue: state => state.currentVenue
      })
    },
    methods: {},
    beforeRouteEnter(to, from, next) {
      store.dispatch('venue/refreshVenue', {
        onSuccess: (success) => {
          store.dispatch('venue/fetchVenueOrders')
          store.dispatch('venue/fetchWeeklyStatistics')
        },
        onError: (error) => {
          Message.error(error)
          router.push({name: 'IndexPage'})
        }
      })
      next(true)
    }
  }

</script>

<style scoped></style>
