<template>

  <div class="manager-approval-wrapper">
    <div-header :header="'场馆审批'"></div-header>

    <div class="approve-list">
      <single-approval v-for="item in approvals" v-if="item.state === '审批中'" :key="item.id"
                       :approval="item"></single-approval>
    </div>

  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import SingleApproval from './SingleApproval.vue'
  import {router} from '../../main'
  import {mapState} from 'vuex'

  export default {
    name: 'manager-approval',
    components: {
      DivHeader,
      SingleApproval
    },
    computed: {
      ...mapState('manager', {
        manager: state => state.currentManager,
        approvals: state => state.approvals
      })
    },
    data() {
      return {}
    },
    methods: {}
  }

</script>

<style scoped src="./ManagerApproval.css"></style>
