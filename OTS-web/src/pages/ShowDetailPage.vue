<template>
  <div class="body-wrapper">
    <layout>
      <div class="container">
        <show-detail v-if="currentShow" :type="type" :currentShow="currentShow"></show-detail>
      </div>
    </layout>

    <choose-seat-modal></choose-seat-modal>

  </div>
</template>

<script>
  import {Message} from 'element-ui'
  import Layout from '../components/Layout/Layout.vue'
  import ShowDetail from '../components/ShowDetail/ShowDetail.vue'
  import ChooseSeatModal from '../components/Modal/ChooseSeatModal.vue'

  import {router, store} from '../main'
  import {mapState} from 'vuex'

  export default {
    name: 'show-detail-page',
    components: {
      Layout,
      ShowDetail,
      ChooseSeatModal
    },
    data() {
      return {}
    },
    computed: {
      ...mapState('auth', {
        type: state => state.loginType
      }),
      ...mapState('showPlan', {
        currentShow: state => state.currentShow
      })
    },
    methods: {},
    beforeRouteEnter(to, from, next) {
      store.dispatch('showPlan/fetchShowPlanById', to.params.showId)
      store.dispatch('auth/refreshUser', {
        onSuccess: (success) => {
          store.dispatch('auth/getUserCoupons')
          store.commit('auth/saveLoginType', 'user')
        },
        onError: (error) => {
          store.dispatch('venue/refreshVenue', {
            onSuccess: (success) => {
              store.commit('auth/saveLoginType', 'venue')
            },
            onError: (error) => {
              store.dispatch('manager/refreshManager', {
                onSuccess: (success) => {
                  store.commit('auth/saveLoginType', 'manager')
                },
                onError: (error) => {
                  store.commit('auth/saveLoginType', null)
                }
              })

            }
          })
        }
      })
      next(true)
    }
  }

</script>

<style scoped></style>
