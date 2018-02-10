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

  export default {
    name: 'user-register-page',
    components: {
      Layout,
      UserRegister
    },
    data() {
      return {}
    },
    methods: {}
  }

</script>

<style scoped src="./UserRegisterPage.css"></style>
