<template>
  <div class="body-wrapper">
    <layout>
      <banner></banner>

      <div class="container">
        <show-search v-if="showPlans" class="show-search"></show-search>

        <type-tabs v-if="showPlans"></type-tabs>
        <show-list v-if="showPlans" :showPlans="typeShowPlans"></show-list>

        <div-header :header="'最近演出'"></div-header>
        <show-list v-if="showPlans" :showPlans="showPlans" :pageInfo="pageInfo" :changePage="fetchAllShowPlans"></show-list>
        <show-list v-else-if="venueShowPlan" :showPlans="venueShowPlan" :pageInfo="pageInfo"></show-list>

      </div>

    </layout>
  </div>

</template>

<script>
  import DivHeader from '../components/Util/DivHeader.vue'
  import Layout from '../components/Layout/Layout.vue'
  import Banner from '../components/Banner/Banner.vue'
  import ShowList from '../components/ShowList/ShowList.vue'
  import ShowSearch from '../components/Search/ShowSearch.vue'
  import TypeTabs from '../components/Filter/TypeTabs.vue'
  import {Message} from 'element-ui'
  import {store} from '../main'
  import {mapActions, mapState} from 'vuex'

  export default {
    name: 'index-page',
    components: {
      DivHeader,
      ShowSearch,
      TypeTabs,
      Layout,
      Banner,
      ShowList,
      Message,
    },
    data() {
      return {}
    },
    computed: {
      ...mapState('showPlan', {
        showPlans: state => state.allShowPlans,
        typeShowPlans: state => state.allShowPlans.slice(0, 4),
        pageInfo: state => {
            return {
                page:state.page,
                totalPages:state.totalPages
            }
        }
      }),
      ...mapState('venue', {
        venueShowPlan: state => state.showPlans
      })
    },
    methods: {
      ...mapActions('auth', [
        'refreshUser'
      ]),
      ...mapActions('showPlan', [
        'fetchAllShowPlans'
      ])
    },
    beforeRouteEnter(to, from, next) {
      store.dispatch('auth/refreshUser', {
        onSuccess: (success) => {
          store.dispatch('showPlan/fetchAllShowPlans', 1)
        },
        onError: (error) => {
//          Message.error('user not login')
          store.dispatch('venue/refreshVenue', {
            onSuccess: (success) => {
              store.dispatch('venue/fetchVenueShowPlans')
            },
            onError: (error) => {
//              Message.error('venue not login')
              store.dispatch('manager/refreshManager', {
                onSuccess: (success) => {
                  store.dispatch('showPlan/fetchAllShowPlans', 1)
                },
                onError: (error) => {
                  store.dispatch('showPlan/fetchAllShowPlans', 1)
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
