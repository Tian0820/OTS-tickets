<template>

  <div class="venue-site-wrapper">

    <plan-list></plan-list>

  </div>

</template>

<script>
  import {router} from '../../main'
  import PlanList from '../PlanList/PlanList.vue'

  export default {
    name: 'venue-site',
    components: {
      PlanList
    },
    data() {
      return {}
    },
    methods: {}
  }

</script>

<style scoped src="./VenueSite.css"></style>
