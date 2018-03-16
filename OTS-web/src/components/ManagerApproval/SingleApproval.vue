<template>
  <div class="single-approval-wrapper">

    <el-tag type="info">注册</el-tag>
    <h1>南京欧拉艺术中心</h1>
    <span class="approve-time">2018-03-24 17:31</span>
    <br/>
    <div class="info-wrapper">
      <p>编号：0000006</p>
      <p>地点：南京 / 南京玄武区太阳宫B1层</p>
      <p>类型：小剧场</p>
    </div>
    <div class="approve-button-wrapper">
      <button class="approve-button">通过</button>
      <button class="disapprove-button">不通过</button>

    </div>
  </div>

</template>

<script>
  import {Tag} from 'element-ui'
  import {router} from '../../main'

  export default {
    name: 'single-approval',
    components: {
      elTag: Tag
    },
    data() {
      return {}
    },
    methods: {}
  }
</script>

<style scoped src="./SingleApproval.css"></style>
