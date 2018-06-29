<template>

  <div class="my-coupon-wrapper">
    <div-header :header="'我的优惠券'"></div-header>
    <el-button type="primary" @click="handleExchangeCoupon" :disabled="user.point.toFixed(2) === '0.00'">兑换优惠券
    </el-button>
    <div class="coupons-wrapper">
      <single-coupon v-for="item in coupons" :coupon="item"></single-coupon>
    </div>

  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import SingleCoupon from '../Coupon/SingleCoupon.vue'
  import {Tooltip, Button} from 'element-ui'
  import {router} from '../../main'
  import {mapState, mapMutations} from 'vuex'

  export default {
    name: 'my-coupon',
    components: {
      DivHeader,
      SingleCoupon,
      elTooltip: Tooltip,
      elButton: Button
    },
    computed: {
      ...mapState('auth', {
        coupons: state => state.userCoupons,
        user: state => state.currentUser
      })
    },
    data() {
      return {}
    },
    methods: {
      ...mapMutations('auth', [
        'saveExchangeCouponModal'
      ]),
      handleExchangeCoupon() {
        this.saveExchangeCouponModal(true)
      }
    }
  }

</script>

<style scoped src="./MyCoupon.css"></style>
