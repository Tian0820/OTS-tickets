<template>
  <div class="body-wrapper">
    <layout>
      <div class="container">
        <user-info v-if="user" :user="user"></user-info>
      </div>
    </layout>
  </div>

</template>

<script>
  import UserInfo from '../components/Admin/UserInfo.vue'
  import Layout from '../components/Layout/Layout.vue'
  import {Message} from 'element-ui'
  import {router, store} from '../main'
  import {mapState} from 'vuex'

  export default {
    name: 'user-info-page',
    components: {
      Layout,
      UserInfo
    },
    data() {
      return {}
    },
    computed: {
      ...mapState('auth', {
        user: state => state.currentUser
      })
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
