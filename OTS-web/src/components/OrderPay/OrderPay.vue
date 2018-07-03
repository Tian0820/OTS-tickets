<template>

  <div>

    <div class="order-pay-wrapper">

      <el-steps :active="1" finish-status="success" align-center>
        <el-step title="选择座位"></el-step>
        <el-step title="支付订单"></el-step>
        <el-step title="下单成功"></el-step>
      </el-steps>


      <div class="time-wrapper">
        <div-header :header="'剩余时间'"></div-header>

        <!--<span class="time-label">剩余时间：</span>-->
        <div :style="{textAlign: 'center'}">
          <span class="time">{{leftTime}}</span>
        </div>
      </div>

      <div class="order-wrapper">
        <div-header :header="'订单详情'"></div-header>
        <div class="poster-wrapper" :style="{ backgroundImage: 'url(' + posterUrl + ')'}">
        </div>

        <div class="info-wrapper">

          <el-tag :type="expired?'danger':'warning'">{{expired ? '已过期' : '待付款'}}</el-tag>
          <p class="show-title">{{currentOrder.showPlan.name}}</p>
          <p class="show-info">类型：{{currentOrder.showPlan.type}}</p>
          <p class="show-info">时间：{{currentOrder.showPlan.time}}</p>
          <p class="show-info">艺人：{{currentOrder.showPlan.star}}</p>
          <p class="show-info">数量：{{currentOrder.seatNum}} 张</p>
          <p class="show-info">座位：{{seats}}</p>

          <el-tooltip placement="bottom" effect="light">
            <div slot="content">用户可以在个人主页<br/>使用积分兑换更多优惠券</div>
            <i class="el-icon-question"></i>
          </el-tooltip>
          <p class="show-info" :style="{display: 'inline-block', verticalAlign: 'top'}">优惠券：</p>
          <el-radio-group v-model="chosenDiscount">
            <el-radio :label="1">不使用优惠券</el-radio>
            <el-radio v-for="(coupon, index) in coupons" :label=coupon.discount>{{coupon.couponName}}</el-radio>
            <!--<el-radio :label="3">备选项</el-radio>-->
            <!--<el-radio :label="6">备选项</el-radio>-->
            <!--<el-radio :label="9">备选项</el-radio>-->
          </el-radio-group>

          <!--<p class="show-info">价格：{{currentOrder.price}} 元</p>-->


        </div>

        <div class="qr-code-wrapper">
          <img src="../../assets/img/qrCode.jpg" width="100%" draggable="false"/>
          <p>支付宝扫码付款</p>
          <span>¥ {{currentOrder.price * chosenDiscount}}</span>
          <el-tooltip placement="bottom" effect="light">
            <div slot="content">下单成功可增加 {{currentOrder.price * chosenDiscount / 10}} 积分</div>
            <i class="el-icon-question"></i>
          </el-tooltip>
        </div>

        <div class="button-wrapper">
          <button @click="handlePay" :disabled="expired">我已支付</button>
        </div>

      </div>


    </div>
  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import {Message, Tag, Radio, RadioGroup, Popover, Tooltip, Steps, Step} from 'element-ui'
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
      elTooltip: Tooltip,
      elSteps: Steps,
      elStep: Step
    },
    props: ['currentOrder', 'userCoupons'],
    data() {
      let name = this.currentOrder.showPlan.id + '.jpeg'
      let createTime = new Date(this.currentOrder.createTime)
      let minDiscount = 1
      this.userCoupons.map(c => {
        if (c.discount < minDiscount) {
          minDiscount = c.discount
        }
      })

      return {
        posterUrl: require('../../assets/img/' + name),
        limitTime: new Date(createTime.setMinutes(createTime.getMinutes() + 15)),
        leftTime: '--:--',
        expired: false,
        chosenDiscount: minDiscount
      }
    },
    mounted: function () {
      this.calLeftTime()
    },
    computed: {
      ...mapState('auth', {
        user: state => state.currentUser,
      }),

      seats: function () {
        let seats = ''
        if (this.currentOrder.seats.length === 0) {
          seats = '待分配'
        } else {
          this.currentOrder.seats.forEach(s => {
            seats += this.calSeatNumber(s) + '; '
          })
        }
        return seats
      },

      coupons: function () {
        let coupons = []
        this.userCoupons.forEach(coupon => {
          let temp = coupons.filter(c => c.couponName === coupon.couponName)
          if (temp.length === 0) {
            coupons.push(coupon)
          }
        })
        return coupons
      }
    },
    methods: {
      ...mapActions('order', [
        'payOrder'
      ]),

      calSeatNumber(seat) {
        let row = Math.floor((seat.number + 1) % 10) === 0 ? Math.floor((seat.number + 1) / 10) : Math.floor((seat.number + 1) / 10) + 1
        let col = Math.floor((seat.number + 1) % 10) === 0 ? 10 : Math.floor((seat.number + 1) % 10)
        return row + '排' + col + '座'
      },

      calLeftTime: function () {
        let that = this;
        let leftTime = that.leftTime;
        let interval = window.setInterval(() => {
          let currentDate = new Date()
          let leftMinutes = Math.floor((this.limitTime - currentDate) / 1000 / 60);
          let leftSeconds = Math.floor(((this.limitTime - currentDate) - (leftMinutes * 60 * 1000)) / 1000);
          let leftTime = ''
          if (leftMinutes < 0) {
            that.expired = true
            leftTime = '00:00'
          } else {
            leftTime = ('0' + leftMinutes).slice(-2) + ':' + ('0' + leftSeconds).slice(-2)
          }
//          console.log(leftTime)
          that.leftTime = leftTime;
        }, 1000)
      },

      handlePay() {
        if (!this.expired) {
          Message.warning('正在检查支付情况...')

          let couponId = null
          switch (this.chosenDiscount) {
            case 0.9:
              couponId = 1
              break;
            case 0.8:
              couponId = 2
              break;
            case 0.7:
              couponId = 3
              break;
            default:
              couponId = null
          }

          this.payOrder({
            order: {
              orderId: this.currentOrder.id,
              price: this.currentOrder.price * this.chosenDiscount,
              couponId: couponId
            },
            onSuccess: (success) => {
              Message.success(success)
//              router.go(0)
            },
            onError: (error) => {
              Message.error(error)
            }
          })
        } else {
          Message.error('订单已过期，请重新下单！')
          router.push({name: 'UserHomePage', params: {userId: this.user.userId}})
        }

      }
    },
  }
</script>

<style scoped src="./OrderPay.css"></style>
