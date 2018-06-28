<template>
  <div class="pay-success-wrapper">

    <el-steps :active="2" finish-status="success" align-center>
      <el-step title="选择座位"></el-step>
      <el-step title="支付订单"></el-step>
      <el-step title="下单成功"></el-step>
    </el-steps>

    <div class="info-wrapper">
      <i class="el-icon-success"></i>
      <span>付款成功！</span><br/>
      <p @click="goToUserHome">回到我的主页</p>
    </div>

  </div>

</template>

<script>
  import {Message, Steps, Step} from 'element-ui'
  import {mapActions, mapState} from 'vuex'
  import {router} from '../../main'

  export default {
    name: 'pay-success',
    components: {
      Message,
      elSteps: Steps,
      elStep: Step
    },
    data() {
      return {}
    },
    computed: {
      ...mapState('auth', {
        user: state => state.currentUser,
      })
    },
    methods: {
      goToUserHome() {
        router.push({name: 'UserHomePage', params: {userId: this.user.userId}})
      }
    }
  }
</script>

<style scoped src="./PaySuccess.css"></style>
