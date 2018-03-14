<template>
  <div class="body-wrapper">
    <layout>
      <div class="container">
        <venue-plan v-if="venue" :venue="venue"></venue-plan>
      </div>
    </layout>
  </div>

</template>

<script>
  import Layout from '../components/Layout/Layout.vue'
  import VenuePlan from '../components/VenuePlan/VenuePlan.vue'
  import {Message} from 'element-ui'
  import {router, store} from '../main'
  import {mapState} from 'vuex'


  export default {
    name: 'venue-plan-page',
    components: {
      Layout,
      VenuePlan,
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
