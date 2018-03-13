<template>

  <div class="my-finance-wrapper">
    <div-header :header="'场馆财务'"></div-header>


  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import {router} from '../../main'

  export default {
    name: 'my-finance',
    components: {
      DivHeader
    },
    data() {
      return {}
    },
    methods: {}
  }

</script>

<style scoped src="./MyFinance.css"></style>
