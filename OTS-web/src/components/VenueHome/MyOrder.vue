<template>

  <div class="my-order-wrapper">
    <div-header :header="'场馆订单'"></div-header>
    <single-order v-for="item in orders" :key="item.id" :order="item"></single-order>
  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import SingleOrder from '../Order/SingleOrder.vue'
  import {router} from '../../main'
  import {mapState, mapActions, mapMutations} from 'vuex'

  export default {
    name: 'my-order',
    components: {
      DivHeader,
      SingleOrder
    },
    props: ['orders'],
    data() {
      return {}
    },
    methods: {}
  }

</script>

<style scoped src="./MyOrder.css"></style>
