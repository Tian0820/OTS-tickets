<template>

  <div class="list-wrapper">
    <div-header :header="'最近演出'"></div-header>

    <el-row :gutter="40">
        <el-col :xs="12" :sm="8" :md="6" :lg="6" v-for="item in showPlans"  :key="item.id">
          <single-show :show="item"></single-show>
        </el-col>
    </el-row>

    <div class="pagination">
      <el-pagination
        background
        layout="prev, pager, next"
        :total="1000">
      </el-pagination>
    </div>


  </div>

</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import SingleShow from './SingleShow.vue'
  import {Row, Col, Pagination} from 'element-ui'
  import {store} from '../../main'
  import {mapActions} from 'vuex'

  export default {
    name: 'show-list',
    components: {
      elRow: Row,
      elCol: Col,
      elPagination: Pagination,
      DivHeader,
      SingleShow
    },
    props: ['showPlans'],
    data() {
        return {}
    },
    computed: {
    },
    methods: {
    },
  }
</script>

<style scoped src="./ShowList.css"></style>
