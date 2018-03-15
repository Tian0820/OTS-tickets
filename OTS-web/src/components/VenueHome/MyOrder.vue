<template>

  <div class="my-order-wrapper">
    <div-header :header="'场馆订单'"></div-header>
    <single-order v-for="item in 10"></single-order>
  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import SingleOrder from '../Order/SingleOrder.vue'
  import {router} from '../../main'

  export default {
    name: 'my-order',
    components: {
      DivHeader,
      SingleOrder
    },
    data() {
      return {}
    },
    methods: {}
  }

</script>

<style scoped src="./MyOrder.css"></style>
