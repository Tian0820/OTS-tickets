<template>

  <div class="register-wrapper">

    <h1>OTS会员注册</h1>

    <el-steps :active="0" finish-status="success" simple style="margin-top: 20px">
      <el-step title="用户邮箱认证"></el-step>
      <el-step title="设置密码"></el-step>
      <el-step title="个人信息"></el-step>
    </el-steps>

    <register-step1></register-step1>

    <register-step2></register-step2>

    <register-step3></register-step3>




  </div>

</template>

<script>
  import {
    Steps,
    Step,
  } from 'element-ui'
  import RegisterStep1 from './RegisterStep1.vue'
  import RegisterStep2 from './RegisterStep2.vue'
  import RegisterStep3 from './RegisterStep3.vue'



  export default {
    name: 'user-register',
    components: {
      elSteps: Steps,
      elStep: Step,
      RegisterStep1,
      RegisterStep2,
      RegisterStep3
    },
    data() {
      return {}
    },
    methods: {}
  }

</script>

<style scoped src="./UserRegister.css"></style>
