<template>

  <div class="manager-approval-wrapper">
    <div-header :header="'场馆审批'"></div-header>
    <single-approval v-for="item in 10"></single-approval>

  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import SingleApproval from './SingleApproval.vue'
  import {router} from '../../main'

  export default {
    name: 'manager-approval',
    components: {
      DivHeader,
      SingleApproval
    },
    data() {
      return {}
    },
    methods: {}
  }

</script>

<style scoped src="./ManagerApproval.css"></style>
