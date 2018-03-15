<template>

  <div>

    <div class="show-detail-wrapper">
      <div class="poster-wrapper" :style="{ backgroundImage: 'url(' + posterUrl + ')'}">
      </div>

      <div class="info-wrapper">

        <p class="show-title">2018咪咕音乐现场南京站徐佳莹心里学音乐会</p>
        <p class="show-info">时间：2018-03-24 19:30</p>
        <p class="show-info">艺人：徐佳莹</p>
        <p class="show-info">地点：南京太阳宫</p>

        <p class="price">
          <img src="../../assets/img/price-tag.png" width="20"/>
          &nbsp;&nbsp;&nbsp;票价
        </p>

        <el-radio-group
          v-model="checkboxGroup"
          size="small"
          :max="1">
          <el-radio label="180元" border></el-radio>
          <el-radio label="280元" border></el-radio>
          <el-radio label="380元" border></el-radio>
          <el-radio label="480元" border></el-radio>
          <el-radio label="680元" border></el-radio>
        </el-radio-group>

        <button v-if="type === 'user'" @click="handleBuyTickets">立即购买</button>
        <button @click="handleChooseSeat">选座购票</button>


      </div>
    </div>

    <div class="show-info-wrapper">
      <div-header :header="'演出信息'"></div-header>
    </div>
  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import {Checkbox, CheckboxGroup, RadioGroup, Radio} from 'element-ui'
  import {store} from '../../main'
  import {mapActions} from 'vuex'
  import ElRadio from "../../../node_modules/element-ui/packages/radio/src/radio.vue";

  export default {
    name: 'show-detail',
    components: {
      DivHeader,
      elCheckbox: Checkbox,
      elCheckboxGroup: CheckboxGroup,
      elRadioGroup: RadioGroup,
      elRadio: Radio
    },
    props: ['type'],
    data() {
      let name = 'poster.jpg'
      return {
        posterUrl: require('../../assets/img/' + name),
        checkboxGroup: []
      }
    },
    methods: {
      handleChooseSeat() {
        this.$modal.show('choose-seat-modal')
      },
      handleBuyTickets() {
        this.$modal.show('buy-tickets-modal')
      }
    },
  }
</script>

<style scoped src="./ShowDetail.css"></style>
