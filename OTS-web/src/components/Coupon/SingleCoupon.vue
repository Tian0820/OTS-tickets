<template>
  <div class="single-coupon-wrapper">


    <div class="info-wrapper">
      <h1>{{coupon.couponName}}</h1>
      <!--<span class="order-time">2018-03-25 18:32:21</span>-->
      <br/>

      <p>优惠内容：购买订单享受 {{coupon.discount * 10}} 折优惠</p>

    </div>

    <div class="circle-o left"></div>
    <div class="circle-o right"></div>

    <div class="rect left"></div>
    <div class="rect right"></div>

    <div class="num-wrapper">
      <p>
        剩余 {{coupon.num}} 张
      </p>

    </div>
  </div>

</template>

<script>

  export default {
    name: 'single-coupon',
    components: {},
    props: ['coupon'],
    data() {
      return {}
    },
    methods: {}
  }
</script>

<style scoped src="./SingleCoupon.css"></style>
