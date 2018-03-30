<template>

  <div class="venue-statistics-wrapper">
    <div-header :header="'场馆统计'"></div-header>

    <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="场馆名称">
              <span>{{ props.row.name }}</span>
            </el-form-item>
            <el-form-item label="场馆编号">
              <span>{{ props.row.code }}</span>
            </el-form-item>
            <el-form-item label="场馆地址">
              <span>{{ props.row.address }}</span>
            </el-form-item>
            <el-form-item label="订单数量">
              <span>{{ props.row.orderNum }}</span>
            </el-form-item>
            <el-form-item label="演出数量">
              <span>{{ props.row.showNum }}</span>
            </el-form-item>
            <el-form-item label="已完成订单">
              <span>{{ props.row.finishedOrderNum }}</span>
            </el-form-item>
            <el-form-item label="已退款订单">
              <span>{{ props.row.refundOrderNum }}</span>
            </el-form-item>
            <el-form-item label="场馆利润">
              <span>{{ props.row.profit }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
        label="场馆名称"
        prop="name">
      </el-table-column>
      <el-table-column
        label="场馆订单"
        prop="orderNum">
      </el-table-column>
      <el-table-column
        label="场馆利润"
        prop="profit">
      </el-table-column>
    </el-table>

    <show-type-bar-chart v-if="x && y" :x="x" :y="y"></show-type-bar-chart>


  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import ShowTypeBarChart from '../Chart/ShowTypeBarChart.vue'
  import {Table, TableColumn, Form, FormItem} from 'element-ui'
  import {router} from '../../main'

  export default {
    name: 'venue-statistics',
    components: {
      DivHeader,
      ShowTypeBarChart,
      elTable: Table,
      elTableColumn: TableColumn,
      elForm: Form,
      elFormItem: FormItem
    },
    props: ['venueStatistics', 'allShowPlans'],
    data() {
      let x = ['演唱会', '音乐会', '舞蹈', '话剧', '体育比赛']
      let y = [0, 0, 0, 0, 0]
      this.allShowPlans.forEach(show => {
        y[x.indexOf(show.type)]++
      })
      return {
        tableData: this.venueStatistics,
        x: x,
        y: y
      }
    },
    created() {
      console.log('venueStatistics', this.venueStatistics)
    },
    methods: {}
  }

</script>

<style scoped src="./VenueStatistics.css"></style>
