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
  import LoginForm from '../components/Auth/LoginForm.vue'
  import {Message} from 'element-ui'
  import {router, store} from '../main'

  export default {
    name: 'manager-home-page',
    components: {
      Layout,
      LoginForm,
      Message
    },
    data() {
      return {}
    },
    methods: {},
    beforeRouteEnter(to, from, next) {
      store.dispatch('manager/refreshManager', {
        onSuccess: (success) => {
        },
        onError: (error) => {
          Message.error('manager not login')
          router.push({name: 'IndexPage'})
        }
      })
      next(true)
    }
  }

</script>

<style scoped></style>
