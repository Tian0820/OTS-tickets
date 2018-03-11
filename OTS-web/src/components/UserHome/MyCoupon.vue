<template>

  <div class="my-coupon-wrapper">
    <div-header :header="'我的优惠券'"></div-header>


  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import {router} from '../../main'

  export default {
    name: 'my-coupon',
    components: {
      DivHeader
    },
    data() {
      return {}
    },
    methods: {}
  }

</script>

<style scoped src="./MyCoupon.css"></style>
