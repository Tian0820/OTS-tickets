<template>
  <div class="body-wrapper">
    <layout>
      <div class="container">
        <venue-site v-if="venue" :venue="venue"></venue-site>
      </div>
    </layout>
  </div>

</template>

<script>
  import Layout from '../components/Layout/Layout.vue'
  import VenueSite from '../components/VenueSite/VenueSite.vue'
  import {Message} from 'element-ui'
  import {router, store} from '../main'
  import {mapState} from 'vuex'


  export default {
    name: 'venue-site-page',
    components: {
      Layout,
      VenueSite,
      Message
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
