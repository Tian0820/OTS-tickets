<template>

  <div class="list-wrapper">
    <div-header :header="'最近演出'"></div-header>
    <el-row :gutter="10">
      <single-show v-for="item in 8" :key="item"></single-show>
    </el-row>

  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import SingleShow from './SingleShow.vue'
  import {Row} from 'element-ui'
  import {store} from '../../main'
  import {mapActions} from 'vuex'

  export default {
    name: 'show-list',
    components: {
      elRow: Row,
      DivHeader,
      SingleShow
    },
    data() {
      return {}
    },
    methods: {},
  }
</script>

<style scoped src="./ShowList.css"></style>
