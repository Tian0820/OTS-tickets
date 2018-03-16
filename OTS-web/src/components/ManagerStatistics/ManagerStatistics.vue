<template>

  <div class="manager-statistics-wrapper">
    <div-header :header="'场馆统计'"></div-header>


  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import {router} from '../../main'

  export default {
    name: 'manager-statistics',
    components: {
      DivHeader,
    },
    data() {
      return {}
    },
    methods: {}
  }

</script>

<style scoped src="./ManagerStatistics.css"></style>
