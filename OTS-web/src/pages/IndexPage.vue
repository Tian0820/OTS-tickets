<template>
  <div class="body-wrapper">
    <layout>

      <div class="container">


      </div>

    </layout>
  </div>

</template>

<script>
  import Layout from '../components/Layout/Layout.vue'
  import {store} from '../main'
  import {mapActions} from 'vuex'

  export default {
    name: 'index-page',
    components: {
      Layout,
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
