<template>

  <div class="my-coupon-wrapper">
    <div-header :header="'我的优惠券'"></div-header>
    <single-coupon v-for="item in 5" :key="item"></single-coupon>

  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import SingleCoupon from '../Coupon/SingleCoupon.vue'
  import {router} from '../../main'

  export default {
    name: 'my-coupon',
    components: {
      DivHeader,
      SingleCoupon
    },
    data() {
      return {}
    },
    methods: {}
  }

</script>

<style scoped src="./MyCoupon.css"></style>
