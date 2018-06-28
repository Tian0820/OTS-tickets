<template>
  <div class="body-wrapper">
    <layout>
      <div class="container">
        <show-search class="show-search"
                     :isSelect="false"
                     :inputValue="keyword"
                     :onInputChange="handleSearchKeywordChange"
                     :onSearchClick="handleSearchClick"
        ></show-search>

        <filter-group class="filter" :filters="filters"></filter-group>

        <show-list v-if="showPlans"
                   :showPlans="showPlans"
                   :pageInfo="pageInfo"
                   :changePage="fetchSearchShowPlans"
        ></show-list>
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
  import {mapActions, mapMutations, mapState} from 'vuex'
  import {CITY, TYPE} from '../constant'

  export default {
    name: 'show-search-page',
    components: {
      Layout,
      ShowSearch,
      FilterGroup,
      ShowList
    },
    data() {
      return {

      }
    },
    computed: {
      ...mapState('showPlan', {
        keyword: state => state.search.keyword,
        city: state => state.search.city,
        type: state => state.search.type,
        showPlans: state => state.search.showPlans,
        pageInfo: state => {
          return {
            page: state.search.page,
            totalPages: state.search.totalPages
          }
        }
      }),
      filters () {
        return [
          {
            name: '地点',
            options: CITY,
            model: this.city,
            onChange: (value) => {
              this.saveSearchCity(value)
              this.fetchSearchShowPlans(1)
            }
          },
          {
            name: '类型',
            options: TYPE,
            model: this.type,
            onChange: (value) => {
              this.saveSearchType(value)
              this.fetchSearchShowPlans(1)
            }
          }
        ]
      }
    },
    methods: {
      ...mapActions('showPlan', [
        'fetchSearchShowPlans'
      ]),
      ...mapMutations('showPlan', [
        'saveSearchKeyword',
        'saveSearchCity',
        'saveSearchType'
      ]),
      handleSearchKeywordChange (keyword) {
        this.saveSearchKeyword(keyword)
      },
      handleCitySelectChange (city) {
        this.saveSearchCity(city)
      },
      handleSearchClick () {
//        router.push({name: 'ShowSearchPage'})
        this.fetchSearchShowPlans(1)
      },
      handlePageChange () {

      }
    },
    beforeRouteEnter(to, from, next) {
      store.dispatch('auth/refreshUser', {
        onSuccess: (success) => {
        },
        onError: (error) => {
        }
      })
      store.dispatch('showPlan/fetchSearchShowPlans', 1)
      next(true)
    }
  }

</script>

<style scoped src="./ShowSearchPage.css"></style>
