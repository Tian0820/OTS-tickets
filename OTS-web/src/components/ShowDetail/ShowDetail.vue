<template>

  <div class="show-detail-wrapper">
    <div class="poster-wrapper" :style="{ backgroundImage: 'url(' + posterUrl + ')'}">
    </div>

    <div class="info-wrapper">

      <p class="show-title">2018咪咕音乐现场南京站徐佳莹心里学音乐会</p>
      <p class="show-location">时间：2018-03-24 19:30</p>
      <p class="show-location">艺人：徐佳莹</p>
      <p class="show-location">地点：南京太阳宫</p>

      <p class="price">
        <img src="../../assets/img/price-tag.png" width="20"/>
        &nbsp;&nbsp;&nbsp;票价
      </p>

      <el-checkbox-group v-model="checkboxGroup" size="small">
        <el-checkbox label="180元" border></el-checkbox>
        <el-checkbox label="280元" border></el-checkbox>
        <el-checkbox label="380元" border></el-checkbox>
        <el-checkbox label="480元" border></el-checkbox>
        <el-checkbox label="680元" border></el-checkbox>
      </el-checkbox-group>

      <button>选座购票</button>


    </div>


  </div>


</template>

<script>
  import {Checkbox, CheckboxGroup} from 'element-ui'
  import {store} from '../../main'
  import {mapActions} from 'vuex'

  export default {
    name: 'show-detail',
    components: {
      elCheckbox: Checkbox,
      elCheckboxGroup: CheckboxGroup
    },
    data() {
      let name = 'poster.jpg'
      return {
        posterUrl: require('../../assets/img/' + name),
        checkboxGroup: []
      }
    },
    methods: {},
  }
</script>

<style scoped src="./ShowDetail.css"></style>
