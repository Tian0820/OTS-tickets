<template>
  <div class="body-wrapper">
    <layout>
      <div class="container">
        <login-form :type="'manager'"></login-form>
      </div>
    </layout>
  </div>

</template>

<script>
  import Layout from '../components/Layout/Layout.vue'
  import LoginForm from '../components/Auth/LoginForm.vue'
  import {router} from '../main'

  export default {
    name: 'manager-login-page',
    components: {
      Layout,
      LoginForm
    },
    data() {
      return {}
    },
    methods: {},
    beforeRouteEnter(to, from, next) {

      next(true)
    }
  }

</script>

<style scoped></style>
