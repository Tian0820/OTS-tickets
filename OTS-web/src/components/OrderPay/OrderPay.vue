<template>

  <div>

    <div class="order-pay-wrapper">
      <div-header :header="'支付订单'"></div-header>

      <div class="time-wrapper">
        <span class="time-label">剩余时间：</span>
        <span class="time">{{leftTime}}</span>
      </div>

      <div class="order-wrapper">
        <div class="poster-wrapper" :style="{ backgroundImage: 'url(' + posterUrl + ')'}">
        </div>

        <div class="info-wrapper">

          <el-tag type="info">订单{{currentOrder.id}}</el-tag>
          <p class="show-title">{{currentOrder.showPlan.name}}</p>
          <p class="show-info">类型：{{currentOrder.showPlan.type}}</p>
          <p class="show-info">时间：{{currentOrder.showPlan.time}}</p>
          <p class="show-info">艺人：{{currentOrder.showPlan.star}}</p>
          <p class="show-info">数量：{{currentOrder.seatNum}} 张</p>
          <p class="show-info">座位：{{currentOrder.seats.length === 0 ? '待分配' : currentOrder.seats.join(', ')}}</p>

          <el-tooltip placement="bottom" effect="light">
            <div slot="content">用户可以在个人主页<br/>使用积分兑换更多优惠券</div>
            <i class="el-icon-question"></i>
          </el-tooltip>
          <p class="show-info" :style="{display: 'inline-block', verticalAlign: 'top'}">优惠券：</p>
          <el-radio-group v-model="chosenCoupon">
            <el-radio :label="0">不使用优惠券</el-radio>
            <el-radio v-for="(coupon, index) in userCoupons" :label=index+1>{{coupon.couponName}}</el-radio>
            <!--<el-radio :label="3">备选项</el-radio>-->
            <!--<el-radio :label="6">备选项</el-radio>-->
            <!--<el-radio :label="9">备选项</el-radio>-->
          </el-radio-group>

          <!--<p class="show-info">价格：{{currentOrder.price}} 元</p>-->


        </div>

        <div class="qr-code-wrapper">
          <img src="../../assets/img/qrCode.jpg" width="100%"/>
          <p>支付宝扫码付款</p>
          <span>¥ {{currentOrder.price}}</span>
        </div>

        <div class="button-wrapper">
          <button>确认付款</button>
        </div>

      </div>


    </div>
  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import {Message, Tag, Radio, RadioGroup, Popover, Tooltip} from 'element-ui'
  import {store, router} from '../../main'
  import {mapActions, mapMutations, mapState} from 'vuex'

  export default {
    name: 'order-pay',
    components: {
      DivHeader,
      Message,
      elTag: Tag,
      elRadio: Radio,
      elRadioGroup: RadioGroup,
      elPopover: Popover,
      elTooltip: Tooltip
    },
    props: ['currentOrder', 'userCoupons'],
    data() {
      let name = 'poster.jpg'
      let createTime = new Date(this.currentOrder.createTime)
      return {
        posterUrl: require('../../assets/img/' + name),
        limitTime: new Date(createTime.setMinutes(createTime.getMinutes() + 15)),
        leftTime: '--:--',
        chosenCoupon: 0
      }
    },
    mounted: function () {
      this.calLeftTime()
    },
    computed: {
      ...mapState('auth', {
        user: state => state.currentUser,
      })
    },
    methods: {
      calLeftTime: function () {
        let that = this;
        let leftTime = that.leftTime;
        let interval = window.setInterval(() => {
          let currentDate = new Date()
          let leftMinutes = Math.floor((this.limitTime - currentDate) / 1000 / 60);
          let leftSeconds = Math.floor(((this.limitTime - currentDate) - (leftMinutes * 60 * 1000)) / 1000);
          let leftTime = ''
          if (leftMinutes < 0) {
            leftTime = '00:00'
          } else {
            leftTime = ('0' + leftMinutes).slice(-2) + ':' + ('0' + leftSeconds).slice(-2)
          }
//          console.log(leftTime)
          that.leftTime = leftTime;
        }, 1000)
      }
    },
  }
</script>

<style scoped src="./OrderPay.css"></style>
