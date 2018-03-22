<template>

  <div class="venue-site-wrapper">

    <plan-list></plan-list>
    <check-list></check-list>

  </div>

</template>

<script>
  import {router} from '../../main'
  import PlanList from '../PlanList/PlanList.vue'
  import CheckList from './CheckTicket.vue'

  export default {
    name: 'venue-site',
    components: {
      PlanList,
      CheckList
    },
    data() {
      return {}
    },
    methods: {}
  }

</script>

<style scoped src="./VenueSite.css"></style>
