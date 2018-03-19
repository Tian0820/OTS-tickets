<template>
  <div class="body-wrapper">
    <layout>
      <banner></banner>

      <div class="container">
        <show-list v-if="showPlans" :showPlans="showPlans"></show-list>

      </div>

    </layout>
  </div>

</template>

<script>
  import Layout from '../components/Layout/Layout.vue'
  import Banner from '../components/Banner/Banner.vue'
  import ShowList from '../components/ShowList/ShowList.vue'
  import {Message} from 'element-ui'
  import {store} from '../main'
  import {mapActions, mapState} from 'vuex'

  export default {
    name: 'index-page',
    components: {
      Layout,
      Banner,
      ShowList,
      Message
    },
    data() {
      return {}
    },
    computed: {
      ...mapState('showPlan', {
        showPlans: state => state.allShowPlans
      })
    },
    methods: {
      ...mapActions('auth', [
        'refreshUser'
      ])
    },
    beforeRouteEnter(to, from, next) {
      store.dispatch('showPlan/fetchAllShowPlans')

      store.dispatch('auth/refreshUser', {
        onSuccess: (success) => {
        },
        onError: (error) => {
//          Message.error('user not login')
          store.dispatch('venue/refreshVenue', {
            onSuccess: (success) => {
            },
            onError: (error) => {
//              Message.error('venue not login')
              store.dispatch('manager/refreshManager', {
                onSuccess: (success) => {
                },
                onError: (error) => {
//              Message.error('venue not login')
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

<style scoped src="./IndexPage.css"></style>
