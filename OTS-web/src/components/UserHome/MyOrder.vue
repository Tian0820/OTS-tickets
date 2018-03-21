<template>

  <div class="my-order-wrapper">
    <div-header :header="'我的订单'"></div-header>

    <single-order v-for="item in userOrders" :order="item" :key="item.id"></single-order>

  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import SingleOrder from '../Order/SingleOrder.vue'
  import {router} from '../../main'
  import {mapState} from 'vuex'

  export default {
    name: 'my-user-order',
    components: {
      DivHeader,
      SingleOrder
    },
    computed: {
      ...mapState('auth', {
        user: state => state.currentUser,
        userOrders: state => state.userOrders
      })
    },
    data() {
      return {}
    },
    methods: {}
  }

</script>

<style scoped src="./MyOrder.css"></style>
