<template>

  <div class="my-order-wrapper">
    <div-header :header="'场馆订单'"></div-header>
  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import {router} from '../../main'

  export default {
    name: 'my-order',
    components: {
      DivHeader,
    },
    data() {
      return {}
    },
    methods: {}
  }

</script>

<style scoped src="./MyOrder.css"></style>
