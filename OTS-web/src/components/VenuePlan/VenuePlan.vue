<template>
  <div class="plan-wrapper">
    <div-header :header="'发布计划'"></div-header>

    <el-form :model="planInfoForm" :rules="rules" ref="planInfoForm" labelPosition="top">

      <el-form-item label="演出名称" prop="name">
        <el-input v-model="planInfoForm.name"></el-input>
      </el-form-item>

      <el-form-item label="演出艺人" prop="star">
        <el-input v-model="planInfoForm.star"></el-input>
      </el-form-item>

      <el-form-item label="演出时间" prop="time">
        <el-input v-model="planInfoForm.time"></el-input>
      </el-form-item>

      <el-form-item label="演出类型" prop="type">
        <el-input v-model="planInfoForm.type"></el-input>
      </el-form-item>

      <el-form-item label="演出简介" prop="introduction">
        <el-input type="textarea" v-model="planInfoForm.introduction"></el-input>
      </el-form-item>

      <el-form-item label="票价设定" prop="price">

        <el-table
          :data="tableData"
          style="width: 100%">
          <el-table-column
            label="区号"
            width="180">
            <template slot-scope="scope">
              <span style="margin-left: 10px">{{ scope.row.area }}</span>
            </template>
          </el-table-column>
          <el-table-column
            label="票价"
          >
            <template slot-scope="scope">
              <div>
              <el-input class="price-input" v-model="scope.row.price"></el-input>  元
              </div>
            </template>
          </el-table-column>
        </el-table>


      </el-form-item>


      <el-form-item>
        <div class="button-wrapper">
          <button @click="submitInfoForm('planInfoForm')">修改</button>
        </div>
        <!--<el-button @click="resetForm('signInForm')">重置</el-button>-->
      </el-form-item>

    </el-form>


  </div>

</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import {Form, FormItem, Input, Message, Table, TableColumn, Tag, Button} from 'element-ui'
  import {router} from '../../main'

  export default {
    name: 'venue-plan',
    components: {
      DivHeader,
      elForm: Form,
      elFormItem: FormItem,
      elInput: Input,
      elTable: Table,
      elTableColumn: TableColumn,
      elTag: Tag,
      elButton: Button,
      Message
    },
    data() {
      let checkName = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('请输入演出名称'))
        } else {
          callback()
        }
      };
      let checkStar = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('请输入艺人名称'))
        } else {
          callback()
        }
      };
      let checkIntroduction = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('请输入演出简介'))
        } else {
          callback()
        }
      };
      return {
        planInfoForm: {
          name: '',
          type: '',
          star: '',
          time: '',
          introduction: '',
          price: 100
        },
        rules: {
          name: [
            {validator: checkName, trigger: 'blur'}
          ],
          star: [
            {validator: checkStar, trigger: 'blur'}
          ],
          introduction: [
            {validator: checkIntroduction, trigger: 'blur'}
          ]
        },
        tableData: [{
          area: '一区',
          price: 380,
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          area: '二区',
          price: 480,
          address: '上海市普陀区金沙江路 1517 弄'
        }, {
          area: '三区',
          price: 580,
          address: '上海市普陀区金沙江路 1519 弄'
        }, {
          area: '四区',
          price: 680,
          address: '上海市普陀区金沙江路 1516 弄'
        }]
      }
    },
    methods: {
      submitInfoForm() {

      },
    }
  }

</script>

<style scoped src="./VenuePlan.css"></style>
