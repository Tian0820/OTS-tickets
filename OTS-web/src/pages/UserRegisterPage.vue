<template>
  <div class="body-wrapper">
    <layout>
      <div class="container">
        <user-register></user-register>

      </div>
    </layout>
  </div>

</template>

<script>
  import Layout from '../components/Layout/Layout.vue'
  import UserRegister from '../components/Auth/UserRegister.vue'
  import {store} from '../main'

  export default {
    name: 'user-register-page',
    components: {
      Layout,
      UserRegister
    },
    data() {
      return {}
    },
    methods: {},
    beforeRouteEnter(to, from, next) {
      store.commit('auth/saveIfSendVerifyCode', false)
      next(true)
    }
  }

</script>

<style scoped src="./UserRegisterPage.css"></style>
