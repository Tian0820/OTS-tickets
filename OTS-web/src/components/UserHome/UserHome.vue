<template>

  <div class="user-home-wrapper">
    <user-info-brief></user-info-brief>
    <my-order></my-order>
    <my-coupon></my-coupon>
  </div>


</template>

<script>
  import {router} from '../../main'
  import UserInfoBrief from '../Admin/UserInfoBrief.vue'
  import MyOrder from '../UserHome/MyOrder.vue'
  import MyCoupon from '../UserHome/MyCoupon.vue'

  export default {
    name: 'user-home',
    components: {
      UserInfoBrief,
      MyOrder,
      MyCoupon
    },
    data() {
      return {}
    },
    methods: {}
  }

</script>

<style scoped src="./UserHome.css"></style>
