<template>
  <div class="body-wrapper">
    <layout>
      <banner></banner>

      <div class="container">
        <show-list></show-list>

      </div>

    </layout>
  </div>

</template>

<script>
  import Layout from '../components/Layout/Layout.vue'
  import Banner from '../components/Banner/Banner.vue'
  import ShowList from '../components/ShowList/ShowList.vue'
  import {store} from '../main'
  import {mapActions} from 'vuex'

  export default {
    name: 'index-page',
    components: {
      Layout,
      Banner,
      ShowList
    },
    data() {
      return {}
    },
    methods: {
      ...mapActions('auth', [
        'refreshUser'
      ])
    },
    beforeRouteEnter(to, from, next) {
      store.dispatch('auth/refreshUser', {
        onSuccess: (success) => {
        },
        onError: (error) => {
        }
      })
      next(true)
    }
  }
</script>

<style scoped src="./IndexPage.css"></style>
