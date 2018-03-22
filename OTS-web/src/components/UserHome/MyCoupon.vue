<template>

  <div class="my-coupon-wrapper">
    <div-header :header="'我的优惠券'"></div-header>
    <single-coupon v-for="item in coupons" :coupon="item"></single-coupon>

  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import SingleCoupon from '../Coupon/SingleCoupon.vue'
  import {router} from '../../main'
  import {mapState} from 'vuex'

  export default {
    name: 'my-coupon',
    components: {
      DivHeader,
      SingleCoupon
    },
    computed: {
      ...mapState('auth', {
        coupons: state => state.userCoupons
      })
    },
    data() {
      return {}
    },
    methods: {}
  }

</script>

<style scoped src="./MyCoupon.css"></style>
