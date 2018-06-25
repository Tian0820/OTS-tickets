<template>

  <div class="list-wrapper">


    <el-row :gutter="40">
      <el-col :xs="12" :sm="8" :md="6" :lg="6" v-for="item in showPlans" :key="item.id">
        <single-show :show="item"></single-show>
      </el-col>
    </el-row>

    <div v-if="showPlans.length === 0" class="blank">
      <p>抱歉，没有找到您需要的演出！</p>
    </div>

    <div v-if="showPlans.length > 0" class="pagination">
      <el-pagination
        v-if="pageInfo"
        background
        layout="prev, pager, next"
        :current-page="pageInfo.page"
        :page-count="pageInfo.totalPages"
        @current-change="handleChangePage"
      >
      </el-pagination>
    </div>


  </div>

</template>

<script>
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
      SingleShow
    },
    props: {
      showPlans: {
        type: Array,
      },
      pageInfo: {
        type: Object
      },
      changePage: {
        type: Function
      }
    },
    data() {
      return {}
    },
    computed: {},
    methods: {
      handleChangePage: function (value) {
        console.log(value)
        this.changePage(value)
      }
    },
  }
</script>

<style scoped src="./ShowList.css"></style>
