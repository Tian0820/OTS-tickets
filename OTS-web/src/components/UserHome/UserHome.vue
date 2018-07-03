<template>

  <div class="user-home-wrapper">
    <user-info-brief v-if="homeType === 'info'"></user-info-brief>
    <my-order v-if="homeType === 'order'" :userOrders="userOrders"></my-order>
    <my-coupon v-if="homeType === 'coupon'"></my-coupon>

  </div>


</template>

<script>
  import {router} from '../../main'
  import UserInfoBrief from '../Admin/UserInfoBrief.vue'
  import MyOrder from '../UserHome/MyOrder.vue'
  import MyCoupon from '../UserHome/MyCoupon.vue'
  import {mapState} from 'vuex'

  export default {
    name: 'user-home',
    components: {
      UserInfoBrief,
      MyOrder,
      MyCoupon
    },
    computed: {
      ...mapState('auth', {
        userOrders: state => state.userOrders,
        homeType: state => state.homeType
      })
    },
    data() {
      return {}
    },
    methods: {}
  }

</script>

<style scoped src="./UserHome.css"></style>
