<template>
  <div class="body-wrapper">
    <layout>
      <div class="container">
        <manager-statistics></manager-statistics>

      </div>
    </layout>
  </div>

</template>

<script>
  import Layout from '../components/Layout/Layout.vue'
  import ManagerStatistics from '../components/ManagerStatistics/ManagerStatistics.vue'
  import {Message} from 'element-ui'
  import {router, store} from '../main'

  export default {
    name: 'manager-statistics-page',
    components: {
      Layout,
      ManagerStatistics,
      Message
    },
    data() {
      return {}
    },
    methods: {},
    beforeRouteEnter(to, from, next) {
      store.dispatch('manager/refreshManager', {
        onSuccess: (success) => {
          store.dispatch('manager/fetchVenueStatistics')
        },
        onError: (error) => {
          Message.error('manager not login')
          router.push({name: 'IndexPage'})
        }
      })
      next(true)
    }
  }

</script>

<style scoped></style>
