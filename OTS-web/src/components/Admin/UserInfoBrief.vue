<template>

  <div class="user-info-wrapper">

    <div class="top-wrapper">
      <div class="avatar-wrapper" :style="{ backgroundImage: 'url(' + avatarUrl + ')' }">
      </div>
      <h1 :style="{fontWeight: 400}">欢迎，{{user.username}}！</h1>
    </div>


    <div class="info-wrapper" v-if="user !== null">

      <div class="row">
        <span>邮箱</span>
        <span>{{user.email}}</span>
      </div>
      <div class="row">
        <span>手机</span>
        <span>{{user.phone}}</span>
      </div>
      <div class="row">
        <span>等级</span>
        <span>{{user.level}}</span>
      </div>
      <div class="row">
        <span>总消费</span>
        <span>{{user.consume}}</span>
      </div>
      <div class="row">
        <span>余额</span>
        <span>{{user.balance}}</span>
      </div>
      <div class="row">
        <span>积分</span>
        <span>{{user.point.toFixed(2)}}
          <el-button type="primary" @click="handleExchangeCoupon" :disabled="user.point.toFixed(2) === '0.00'">兑换优惠券</el-button>
        </span>

      </div>

    </div>

  </div>


</template>

<script>
  import {Button} from 'element-ui'
  import {router} from '../../main'
  import {mapState} from 'vuex'

  export default {
    name: 'user-info-brief',
    components: {
      elButton: Button
    },
    computed: {
      ...mapState('auth', {
        user: state => state.currentUser
      })
    },
    data() {
      return {
        avatarUrl: 'https://cdn.dribbble.com/users/548267/screenshots/2657798/wagon_v1_dribbble.jpg',
      }
    },
    methods: {
      handleExchangeCoupon() {
        this.$modal.show('exchange-coupon-modal')
      }
    }
  }

</script>

<style scoped src="./UserInfoBrief.css"></style>
