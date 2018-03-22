<template>
  <div class="body-wrapper">
    <layout>
      <div class="container">
        <manager-approval v-if="manager"></manager-approval>
        <user-banning v-if="manager"></user-banning>
      </div>
    </layout>
  </div>

</template>

<script>
  import Layout from '../components/Layout/Layout.vue'
  import ManagerApproval from '../components/ManagerApproval/ManagerApproval.vue'
  import UserBanning from '../components/ManagerApproval/UserBanning.vue'
  import {Message} from 'element-ui'
  import {router, store} from '../main'
  import {mapState} from 'vuex'

  export default {
    name: 'manager-home-page',
    components: {
      Layout,
      ManagerApproval,
      UserBanning,
      Message
    },
    computed: {
      ...mapState('manager', {
        manager: state => state.currentManager
      })
    },
    data() {
      return {}
    },
    methods: {},
    beforeRouteEnter(to, from, next) {
      store.dispatch('manager/refreshManager', {
        onSuccess: (success) => {
          store.dispatch('manager/fetchApprovals')
          store.dispatch('manager/fetchAllUsers')
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
