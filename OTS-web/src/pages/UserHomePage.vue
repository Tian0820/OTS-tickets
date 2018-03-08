<template>
  <div class="body-wrapper">
    <layout>
      <div class="container">

        <user-home></user-home>

      </div>
    </layout>
  </div>

</template>

<script>
  import {Message} from 'element-ui'
  import Layout from '../components/Layout/Layout.vue'
  import UserHome from '../components/UserHome/UserHome.vue'
  import {router, store} from '../main'

  export default {
    name: 'user-home-page',
    components: {
      Layout,
      UserHome
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
          router.push({name: 'IndexPage'})
        }
      })
      next(true)
    }
  }

</script>

<style scoped></style>
