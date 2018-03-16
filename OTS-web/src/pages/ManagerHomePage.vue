<template>
  <div class="body-wrapper">
    <layout>
      <div class="container">
        <manager-approval></manager-approval>

      </div>
    </layout>
  </div>

</template>

<script>
  import Layout from '../components/Layout/Layout.vue'
  import ManagerApproval from '../components/ManagerApproval/ManagerApproval.vue'
  import {Message} from 'element-ui'
  import {router, store} from '../main'

  export default {
    name: 'manager-home-page',
    components: {
      Layout,
      ManagerApproval,
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
