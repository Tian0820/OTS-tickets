<template>

  <div class="OTS-finance-wrapper">
    <div-header :header="'OTS财务情况'"></div-header>

  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import {router} from '../../main'

  export default {
    name: 'OTS-finance-wrapper',
    components: {
      DivHeader,
    },
    data() {
      return {}
    },
    methods: {}
  }

</script>

<style scoped src="./OTSFinance.css"></style>
