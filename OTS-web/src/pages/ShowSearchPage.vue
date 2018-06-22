<template>
  <div class="body-wrapper">
    <layout>
      <div class="container">
        <show-search v-if="showPlans" class="show-search"></show-search>

        <filter-group class="filter"></filter-group>

        <show-list :showPlans="showPlans"></show-list>
      </div>
    </layout>
  </div>

</template>

<script>
  import Layout from '../components/Layout/Layout.vue'
  import ShowList from '../components/ShowList/ShowList.vue'
  import ShowSearch from '../components/Search/ShowSearch.vue'
  import FilterGroup from '../components/Filter/FilterGroup.vue'
  import {router, store} from '../main'
  import {mapState} from 'vuex'

  export default {
    name: 'show-search-page',
    components: {
      Layout,
      ShowSearch,
      FilterGroup,
      ShowList
    },
    data() {
      return {}
    },
    computed: {
      ...mapState('showPlan', {
        showPlans: state => state.allShowPlans
      }),
    },
    methods: {},
    beforeRouteEnter(to, from, next) {
      store.dispatch('auth/refreshUser', {
        onSuccess: (success) => {
        },
        onError: (error) => {
        }
      })
      store.dispatch('showPlan/fetchAllShowPlans')
      next(true)
    }
  }

</script>

<style scoped src="./ShowSearchPage.css"></style>
