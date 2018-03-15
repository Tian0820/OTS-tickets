<template>
  <div class="body-wrapper">
    <layout>
      <div class="container">
        <show-detail :type="type"></show-detail>
      </div>
    </layout>
  </div>

</template>

<script>
  import {Message} from 'element-ui'
  import Layout from '../components/Layout/Layout.vue'
  import ShowDetail from '../components/ShowDetail/ShowDetail.vue'
  import {router, store} from '../main'
  import {mapState} from 'vuex'

  export default {
    name: 'show-detail-page',
    components: {
      Layout,
      ShowDetail
    },
    data() {
      return {}
    },
    computed: {
      ...mapState('auth', {
        type: state => state.loginType
      })
    },
    methods: {},
    beforeRouteEnter(to, from, next) {
      store.dispatch('auth/refreshUser', {
        onSuccess: (success) => {
          store.commit('auth/saveLoginType', 'user')
        },
        onError: (error) => {
          store.dispatch('venue/refreshVenue', {
            onSuccess: (success) => {
              store.commit('auth/saveLoginType', 'venue')
            },
            onError: (error) => {
//              Message.error('venue not login')
            }
          })
        }
      })
      next(true)
    }
  }

</script>

<style scoped></style>
