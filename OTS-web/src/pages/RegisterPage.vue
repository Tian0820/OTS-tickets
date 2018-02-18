<template>
  <div class="body-wrapper">
    <layout>
      <div class="container">

        <div class="button-wrapper">
          <button @click="goToUserRegister">
            会员注册
          </button>

          <button @click="goToVenueRegister">
            场馆注册
          </button>
        </div>


      </div>
    </layout>
  </div>

</template>

<script>
  import Layout from '../components/Layout/Layout.vue'
  import {router} from '../main'

  export default {
    name: 'register-page',
    components: {
      Layout,
    },
    data() {
      return {}
    },
    methods: {
      goToUserRegister() {
        router.push({name: 'UserRegisterPage'})
      },
      goToVenueRegister() {
        router.push({name: 'VenueRegisterPage'})
      }
    }
  }

</script>

<style scoped src="./RegisterPage.css"></style>
