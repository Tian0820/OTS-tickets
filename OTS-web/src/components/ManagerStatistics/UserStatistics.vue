<template>

  <div class="user-statistics-wrapper">
    <div-header :header="'用户统计'"></div-header>


    <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="用户名称">
              <span>{{ props.row.username }}</span>
            </el-form-item>
            <el-form-item label="用户id">
              <span>{{ props.row.id }}</span>
            </el-form-item>
            <el-form-item label="用户状态">
              <span>{{ props.row.activate ? '正常' : '封禁' }}</span>
            </el-form-item>
            <el-form-item label="用户邮箱">
              <span>{{ props.row.email }}</span>
            </el-form-item>
            <el-form-item label="用户手机">
              <span>{{ props.row.phone }}</span>
            </el-form-item>
            <el-form-item label="用户等级">
              <span>{{ props.row.level }}</span>
            </el-form-item>
            <el-form-item label="用户积分">
              <span>{{ props.row.point }}</span>
            </el-form-item>
            <el-form-item label="用户余额">
              <span>{{ props.row.balance }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
        label="用户名称"
        prop="username">
      </el-table-column>
      <el-table-column
        label="用户等级"
        prop="level">
      </el-table-column>
      <el-table-column
        label="用户余额"
        prop="balance">
      </el-table-column>
    </el-table>


  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import {Table, TableColumn, Form, FormItem} from 'element-ui'
  import {router} from '../../main'

  export default {
    name: 'user-statistics',
    components: {
      DivHeader,
      elTable: Table,
      elTableColumn: TableColumn,
      elForm: Form,
      elFormItem: FormItem
    },
    props: ['userStatistics'],
    data() {
      return {
        tableData: this.userStatistics
      }
    },
    methods: {}
  }

</script>

<style scoped src="./UserStatistics.css"></style>
