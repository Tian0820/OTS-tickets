<template>
  <div class="single-approval-wrapper" v-if="approval.state === '审批中'">

    <el-tag type="info">{{approval.type}}</el-tag>
    <h1>{{approval.venue.venueName}}</h1>
    <span class="approve-time">2018-03-24 17:31</span>
    <br/>
    <div class="info-wrapper">
      <p>编号：{{approval.venue.code}}</p>
      <p>地点：{{approval.venue.city}} / {{approval.venue.address}}</p>
      <p>类型：{{approval.venue.seatType}}</p>
    </div>
    <div class="approve-button-wrapper">
      <button class="approve-button" @click="handleApprove">通过</button>
      <button class="disapprove-button" @click="handleDisapprove">不通过</button>

    </div>
  </div>

</template>

<script>
  import {Tag, Message} from 'element-ui'
  import {router} from '../../main'
  import {mapActions} from 'vuex'

  export default {
    name: 'single-approval',
    components: {
      elTag: Tag,
      Message
    },
    props: ['approval'],
    data() {
      return {}
    },
    methods: {
      ...mapActions('manager', [
        'managerApprove'
      ]),
      handleApprove() {
        this.managerApprove({
          info: {
            approvalId: this.approval.id,
            approvalResult: '成功'
          },
          onSuccess: (success) => {
            Message.success(success)
            location.reload();
          },
          onError: (error) => {
            Message.error(error)
          }
        })
      },
      handleDisapprove() {
        this.managerApprove({
          info: {
            approvalId: this.approval.id,
            approvalResult: '失败'
          },
          onSuccess: (success) => {
            Message.success(success)
            location.reload();
          },
          onError: (error) => {
            Message.error(error)
          }
        })
      }
    }
  }
</script>

<style scoped src="./SingleApproval.css"></style>
