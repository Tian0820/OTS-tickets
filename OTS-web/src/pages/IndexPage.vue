<template>
  <div class="body-wrapper">
    <layout>
      <banner></banner>

      <div class="container">
        <show-search v-if="showPlans" class="show-search"
                     :isSelect="true"
                     :inputValue="keyword"
                     :onInputChange="handleSearchKeywordChange"
                     :selectValue="city"
                     :onSelectChange="handleCitySelectChange"
                     :onSearchClick="handleSearchClick"
        ></show-search>

        <type-tabs v-if="showPlans" :types="tabTypes" :value="tabValue" :tabClick="handleTypeTabClick"></type-tabs>
        <show-list v-if="typeShowPlans" :showPlans="typeShowPlans"></show-list>
        <div>
          <button v-if="typeShowPlans" class="more" @click="handleClickMore">
            更多<i class="el-icon-d-arrow-right el-icon--right"></i>
          </button>
        </div>

        <div v-if="showPlans" class="recent">
          <div-header :header="'最近演出'"></div-header>
          <show-list :showPlans="showPlans"
                     :pageInfo="pageInfo"
                     :changePage="fetchAllShowPlans"
          ></show-list>
        </div>

        <div v-if="venueShowPlans">
          <show-list :showPlans="venueShowPlans"
                     :pageInfo="pageInfo"
                     :changePage="fetchAllShowPlans"></show-list>
        </div>
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
  import {store, router} from '../main'
  import {mapActions, mapMutations, mapState} from 'vuex'
  import {SHOW_TYPE} from '../constant'

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
      return {
        tabValue: '演唱会',
        tabTypes: SHOW_TYPE,
      }
    },
    computed: {
      ...mapState('showPlan', {
        keyword: state => state.search.keyword,
        city: state => state.search.city,
        showPlans: state => state.allShowPlans,
        typeShowPlans: state => state.typeShowPlans,
        pageInfo: state => {
          return {
            page: state.page,
            totalPages: state.totalPages
          }
        }
      }),
      ...mapState('venue', {
        venueShowPlans: state => state.showPlans
      })
    },
    methods: {
      ...mapActions('auth', [
        'refreshUser'
      ]),
      ...mapActions('showPlan', [
        'fetchAllShowPlans',
        'fetchTypeShowPlans'
      ]),
      ...mapMutations('showPlan', [
        'saveSearchKeyword',
        'saveSearchCity',
        'saveSearchType'
      ]),
      handleTypeTabClick (tab) {
        this.tabValue = tab
        this.fetchTypeShowPlans(tab)
      },
      handleSearchKeywordChange (keyword) {
        this.saveSearchKeyword(keyword)
      },
      handleCitySelectChange (city) {
        this.saveSearchCity(city)
      },
      handleSearchClick () {
        this.saveSearchType('全部')
        router.push({name: 'ShowSearchPage'})
      },
      handleClickMore () {
        this.saveSearchType(this.tabValue)
        router.push({name: 'ShowSearchPage'})
      }
    },
    beforeRouteEnter(to, from, next) {
      store.dispatch('auth/refreshUser', {
        onSuccess: (success) => {
          store.dispatch('showPlan/fetchAllShowPlans', 1)
          store.dispatch('showPlan/fetchTypeShowPlans', SHOW_TYPE[0].name)
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
                  store.dispatch('showPlan/fetchTypeShowPlans', SHOW_TYPE[0].name)
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
