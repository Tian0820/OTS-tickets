<template>
  <div class="body-wrapper">
    <layout>
      <div class="container">
        <show-detail></show-detail>
      </div>
    </layout>
  </div>

</template>

<script>
  import {Message} from 'element-ui'
  import Layout from '../components/Layout/Layout.vue'
  import ShowDetail from '../components/ShowDetail/ShowDetail.vue'
  import {router, store} from '../main'

  export default {
    name: 'show-detail-page',
    components: {
      Layout,
      ShowDetail
    },
    data() {
      return {}
    },
    methods: {},
    beforeRouteEnter(to, from, next) {
      store.dispatch('auth/refreshUser', {
        onSuccess: (success) => {
        },
        onError: (error) => {
          Message.error(error)
        }
      })
      next(true)
    }
  }

</script>

<style scoped></style>
