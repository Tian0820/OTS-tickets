<template>
  <div class="body-wrapper">
    <layout>
      <div class="container">

        <user-home v-if="user"></user-home>

      </div>
    </layout>
  </div>

</template>

<script>
  import {Message} from 'element-ui'
  import Layout from '../components/Layout/Layout.vue'
  import UserHome from '../components/UserHome/UserHome.vue'
  import {router, store} from '../main'
  import {mapState} from 'vuex'

  export default {
    name: 'user-home-page',
    components: {
      Layout,
      UserHome
    },
    data() {
      return {}
    },
    computed: {
      ...mapState('auth', {
        user: state => state.currentUser,
        userOrders: state => state.userOrders
      })
    },
    methods: {},
    beforeRouteEnter(to, from, next) {
      store.dispatch('auth/refreshUser', {
        onSuccess: (success) => {
          store.dispatch('auth/getUserOrders', {
            onSuccess: (success) => {
            },
            onError: (error) => {
              Message.error(error)
            }
          })
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
