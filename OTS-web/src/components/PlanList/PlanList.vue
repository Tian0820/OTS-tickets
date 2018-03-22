<template>

  <div class="list-wrapper">
    <div-header :header="'演出列表'"></div-header>
    <single-plan v-for="item in showPlans" :key="item.id" :showPlan="item"></single-plan>
  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import SinglePlan from './SinglePlan.vue'
  import {Row} from 'element-ui'
  import {store} from '../../main'
  import {mapActions, mapState} from 'vuex'

  export default {
    name: 'plan-list',
    components: {
      elRow: Row,
      DivHeader,
      SinglePlan
    },
    computed: {
      ...mapState('venue', {
        showPlans: state => state.showPlans
      })
    },
    data() {
      return {}
    },
    methods: {},
  }
</script>

<style scoped src="./PlanList.css"></style>
