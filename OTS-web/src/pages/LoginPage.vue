<template>
  <div class="body-wrapper">
    <layout>
      <div class="container">

        <div class="button-wrapper">
          <button @click="userLogin">
            会员登录
          </button>

          <button @click="venueLogin">
            场馆登录
          </button>

          <button @click="managerLogin">
            经理登录
          </button>
        </div>

      </div>
    </layout>
  </div>

</template>

<script>
  import Layout from '../components/Layout/Layout.vue'
  import LoginForm from '../components/Auth/LoginForm.vue'
  import {router} from '../main'
  import {mapMutations, mapState} from 'vuex'

  export default {
    name: 'login-page',
    components: {
      Layout,
      LoginForm
    },
    data() {
      return {}
    },
    computed: {
      ...mapState('login', {
        currentLogin: state => state.currentLogin
      })
    },
    methods: {
      ...mapMutations('login', [
        'saveCurrentLogin'
      ]),
      userLogin() {
        this.saveCurrentLogin('user')
        router.push({name: 'UserLoginPage'})
      },
      venueLogin() {
        this.saveCurrentLogin('venue')
        router.push({name: 'VenueLoginPage'})
      },
      managerLogin() {
        this.saveCurrentLogin('manager')
        router.push({name: 'ManagerLoginPage'})
      }
    }
  }

</script>

<style scoped src="./LoginPage.css"></style>
