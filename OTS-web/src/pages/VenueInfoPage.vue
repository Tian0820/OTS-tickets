<template>
  <div class="body-wrapper">
    <layout>
      <div class="container">
        <venue-info v-if="venue" :venue="venue"></venue-info>
      </div>
    </layout>
  </div>

</template>

<script>
  import VenueInfo from '../components/Admin/VenueInfo.vue'
  import Layout from '../components/Layout/Layout.vue'
  import {Message} from 'element-ui'
  import {router, store} from '../main'
  import {mapState} from 'vuex'

  export default {
    name: 'venue-info-page',
    components: {
      Layout,
      VenueInfo
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
