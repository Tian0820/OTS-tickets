<template>
  <div class="body-wrapper">
    <layout>
      <div class="container">
        <login-form :type="'venue'"></login-form>
      </div>
    </layout>
  </div>

</template>

<script>
  import Layout from '../components/Layout/Layout.vue'
  import LoginForm from '../components/Auth/LoginForm.vue'
  import {router} from '../main'

  export default {
    name: 'venue-login-page',
    components: {
      Layout,
      LoginForm
    },
    data() {
      return {}
    },
    methods: {}
  }

</script>

<style scoped></style>
