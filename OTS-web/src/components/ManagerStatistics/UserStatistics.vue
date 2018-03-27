<template>

  <div class="user-statistics-wrapper">
    <div-header :header="'用户统计'"></div-header>

  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import {router} from '../../main'

  export default {
    name: 'user-statistics',
    components: {
      DivHeader,
    },
    data() {
      return {}
    },
    methods: {}
  }

</script>

<style scoped src="./UserStatistics.css"></style>
