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
        <el-date-picker
          v-model="planInfoForm.time"
          type="datetime"
          :picker-options="pickerOption"
          placeholder="选择日期时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          @change="changeTime"
        >
        </el-date-picker>
      </el-form-item>

      <el-form-item label="演出类型" prop="type">
        <el-select v-model="planInfoForm.type" placeholder="请选择">
          <el-option
            v-for="item in types"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
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
                <el-input class="price-input" v-model="scope.row.price"></el-input>
                元
              </div>
            </template>
          </el-table-column>
        </el-table>


      </el-form-item>


      <el-form-item>
        <div class="button-wrapper">
          <button @click="submitInfoForm('planInfoForm')">发布</button>
        </div>
        <!--<el-button @click="resetForm('signInForm')">重置</el-button>-->
      </el-form-item>

    </el-form>


  </div>

</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import {Form, FormItem, Input, Message, Table, TableColumn, Tag, Button, Select, Option, DatePicker} from 'element-ui'
  import {router} from '../../main'
  import {mapState, mapActions} from 'vuex'

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
      elSelect: Select,
      elOption: Option,
      elDatePicker: DatePicker,
      Message
    },
    props: ['venue'],
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
        types: [{
          value: '演唱会',
          label: '演唱会'
        }, {
          value: '音乐会',
          label: '音乐会'
        }, {
          value: '舞蹈',
          label: '舞蹈'
        }, {
          value: '话剧',
          label: '话剧'
        }, {
          value: '体育比赛',
          label: '体育比赛'
        }],
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
        tableData: this.venue.seatType === '小剧场' ? [{
          area: '一区',
          price: 580,
        }, {
          area: '二区',
          price: 480,
        }, {
          area: '三区',
          price: 380,
        }] : [{
          area: '一区',
          price: 880,
        }, {
          area: '二区',
          price: 780,
        }, {
          area: '三区',
          price: 680,
        }, {
          area: '四区',
          price: 580,
        }, {
          area: '五区',
          price: 480,
        }, {
          area: '六区',
          price: 380,
        }],
        pickerOption: {
          disabledDate(time) {
//            console.log('111');
            let timeSpace = time.getTime() < Date.now();
            return timeSpace;
          }
        }
      }
    },
    methods: {
      ...mapActions('venue', [
        'uploadShowPlan'
      ]),
      changeTime(val) {
        console.log(val)
      },
      submitInfoForm(data) {
        this.$refs[data].validate((valid) => {
          if (valid) {
            let prices = []
            this.tableData.forEach(data => {
              prices.push(data.price)
            })
            this.uploadShowPlan({
              info: {
                name: this.planInfoForm.name,
                venueCode: this.venue.code,
                star: this.planInfoForm.star,
                type: this.planInfoForm.type,
                time: this.planInfoForm.time,
                introduction: this.planInfoForm.introduction,
                price: prices.join(';')
              },
              onSuccess: (success) => {
                Message.success(success)
                router.push({name: 'VenueHomePage', params: {venueCode: this.venue.code}})
              },
              onError: (error) => {
                Message.error(error)
              }
            })
          } else {
            Message.error('请正确填写信息！')
          }
        })


      },
    }
  }

</script>

<style scoped src="./VenuePlan.css"></style>
