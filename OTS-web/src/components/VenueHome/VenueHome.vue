<template>

  <div class="venue-home-wrapper">
    <my-order></my-order>
    <my-finance></my-finance>
  </div>


</template>

<script>
  import {router} from '../../main'
  import MyOrder from '../VenueHome/MyOrder.vue'
  import MyFinance from '../VenueHome/MyFinance.vue'

  export default {
    name: 'venue-home',
    components: {
      MyOrder,
      MyFinance
    },
    data() {
      return {}
    },
    methods: {}
  }

</script>

<style scoped src="./VenueHome.css"></style>
