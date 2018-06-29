<template>

  <div>

    <div class="show-detail-wrapper">
      <div class="poster-wrapper" :style="{ backgroundImage: 'url(' + posterUrl + ')'}">
      </div>

      <div class="info-wrapper">

        <p class="show-title">{{currentShow.name}}</p>
        <p class="show-info">类型：{{currentShow.type}}</p>
        <p class="show-info">时间：{{currentShow.time}}</p>
        <p class="show-info">艺人：{{currentShow.star}}</p>
        <p class="show-info">
          地点：{{currentShow.venue.city}}&nbsp;{{currentShow.venue.venueName}}&nbsp;{{currentShow.venue.address}}</p>

        <p class="show-info" :style="{display: 'inline-block'}">
          <!--<img src="../../assets/img/price-tag.png" width="20"/>-->
          票价：
        </p>

        <el-checkbox-group
          v-model="checkboxGroup"
          size="small"
          :min="1"
          :max="3">
          <el-checkbox
            v-for="(price, index) in prices"
            :label="price"
            :key="index"
            @change="handleChange"
            border>{{price}}元
          </el-checkbox>
        </el-checkbox-group>

        <br/>

        <p class="show-info" :style="{display: 'inline-block', marginTop: '18px', verticalAlign: 'top'}">
          已选：
        </p>

        <div class="chosen-price-wrapper">
          <div v-for="(price, index) in chosenPrices">
            <div class="chosen-price">
              {{price}}元
              <el-input-number v-model="num[index]" @change="handleChange" :min="1" :max="10"
                               label="描述文字"></el-input-number>

              <span>（最多购买 10 张）</span>
            </div>
            <br/>
          </div>
        </div>
        <p class="show-info">总计：
          <span :style="{ color: '#E9A038', fontSize: '20px', fontWeight: '400'}">{{totalPrice}}</span> 元
        </p>
      </div>

      <button v-if="type === 'user'" @click="handleBuyTickets">立即购买</button>
      <button @click="handleChooseSeat">选座购票</button>


    </div>

    <div class="show-info-wrapper">
      <div-header :header="'演出信息'"></div-header>

      <pre>
         <!--<p>-->
        {{currentShow.introduction}}
        <!--</p>-->
      </pre>
    </div>
  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import {Checkbox, CheckboxGroup, RadioGroup, Radio, InputNumber, Message} from 'element-ui'
  import {store, router} from '../../main'
  import {mapActions, mapMutations, mapState} from 'vuex'

  export default {
    name: 'show-detail',
    components: {
      DivHeader,
      elCheckbox: Checkbox,
      elCheckboxGroup: CheckboxGroup,
      elRadioGroup: RadioGroup,
      elRadio: Radio,
      elInputNumber: InputNumber,
      Message
    },
    props: ['type', 'currentShow'],
    data() {
      let prices = [380, 580, 780]
//      let areas = this.currentShow.seats.length / 100
//      for (let i = 0; i < areas; i++) {
//        prices.push(this.currentShow.seats[i * 100].price)
//      }

      return {
        checkboxGroup: [prices[0]],
        prices: prices,
        num: [1, 1, 1]
      }
    },
    computed: {
      ...mapState('auth', {
        user: state => state.currentUser
      }),
      ...mapState('order', {
        currentOrder: state => state.currentOrder
      }),

      posterUrl: function () {
        let name = this.currentShow.id + '.jpeg'
        return require('../../assets/img/' + name)
      },

      chosenPrices: function () {
        return this.checkboxGroup
      },
      totalPrice: function () {
        let total = 0
        this.chosenPrices.forEach((p, index) => {
          total += p * this.num[index]
        })
        return total
      }
    },
    methods: {
      ...mapActions('order', [
        'createOrder'
      ]),
      ...mapMutations('showPlan', [
        'saveChosenArea'
      ]),
      handleChange(val) {
//        this.chosenPrices = this.checkboxGroup
      },
      handleChooseSeat() {
        if (this.type === 'user') {
          this.$modal.show('choose-seat-modal')
        } else if (this.type === 'venue') {
          this.$modal.show('user-account-modal')
        } else if (this.type === 'manager') {
          Message.warning('经理无法操作！')
        } else {
          Message.error('请登录后操作！')
          router.push({name: 'UserLoginPage'})
        }
      },
      handleBuyTickets() {
        let seatNum = 0
        this.chosenPrices.forEach((c, index) => {
          seatNum += this.num[index]
        })
        let seats = []
        for (let i = 0; i < seatNum; i++) {
          seats.push(0)
        }

        this.createOrder({
          info: {
            showId: this.currentShow.id,
            userId: this.user.userId,
            type: '分配',
            state: '未付款',
            price: this.totalPrice,
            seats: seats.join(';')
          },
          onSuccess: (success) => {
//            Message.success(success)
            router.push({name: 'PayPage', params: {orderId: this.currentOrder.id}})
          },
          onError:
            (error) => {
              Message.error(error)
            }
        })
      }
    },
  }
</script>

<style scoped src="./ShowDetail.css"></style>
