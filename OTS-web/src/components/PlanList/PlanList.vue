<template>

  <div class="list-wrapper">
    <div-header :header="'演出列表'"></div-header>
    <single-plan v-for="item in 5" :key="item"></single-plan>
  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import SinglePlan from './SinglePlan.vue'
  import {Row} from 'element-ui'
  import {store} from '../../main'
  import {mapActions} from 'vuex'

  export default {
    name: 'plan-list',
    components: {
      elRow: Row,
      DivHeader,
      SinglePlan
    },
    data() {
      return {}
    },
    methods: {},
  }
</script>

<style scoped src="./PlanList.css"></style>
