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

        <p class="price">
          <img src="../../assets/img/price-tag.png" width="20"/>
          &nbsp;&nbsp;&nbsp;票价
        </p>

        <el-radio-group
          v-model="checkboxGroup"
          size="small"
          :max="1">
          <el-radio
            v-for="(price, index) in prices"
            :label="price"
            :key="index"
            @change="handleChange"
            border></el-radio>
        </el-radio-group>

        <button v-if="type === 'user'" @click="handleBuyTickets">立即购买</button>
        <button @click="handleChooseSeat">选座购票</button>


      </div>
    </div>

    <div class="show-info-wrapper">
      <div-header :header="'演出信息'"></div-header>

      <p>{{currentShow.introduction}}</p>
    </div>
  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import {Checkbox, CheckboxGroup, RadioGroup, Radio, Message} from 'element-ui'
  import {store, router} from '../../main'
  import {mapActions, mapMutations} from 'vuex'
  import ElRadio from "../../../node_modules/element-ui/packages/radio/src/radio.vue";

  export default {
    name: 'show-detail',
    components: {
      DivHeader,
      elCheckbox: Checkbox,
      elCheckboxGroup: CheckboxGroup,
      elRadioGroup: RadioGroup,
      elRadio: Radio,
      Message
    },
    props: ['type', 'currentShow'],
    data() {
      let name = 'poster.jpg'
      let prices = []
      let areas = this.currentShow.seats.length / 100
      for (let i = 0; i < areas; i++) {
        prices.push(this.currentShow.seats[i * 100].price)
      }

      return {
        posterUrl: require('../../assets/img/' + name),
        checkboxGroup: prices[0],
        prices: prices
      }
    },
    methods: {
      ...mapMutations('showPlan', [
        'saveChosenArea'
      ]),
      handleChange(val) {
        this.saveChosenArea(this.prices.indexOf(val) + 1)
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
          router.push({name: 'LoginPage'})
        }
      },
      handleBuyTickets() {
        this.$modal.show('buy-tickets-modal')
      }
    },
  }
</script>

<style scoped src="./ShowDetail.css"></style>
