<template>

  <div class="register-wrapper">

    <h1>OTS场馆注册</h1>

    <div class="form-wrapper">
      <el-form :model="registerForm" :rules="rules" ref="registerForm" labelPosition="top">

        <el-form-item label="场馆名" prop="venueName">
          <el-input v-model="registerForm.venueName"></el-input>
        </el-form-item>

        <el-form-item label="场馆地点" prop="venueAddress">
          <el-select v-model="registerForm.venueAddress" placeholder="请选择地点">
            <el-option
              v-for="item in addresses"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="座位情况" prop="venueSeats">
          <el-checkbox-group
            v-model="registerForm.venueSeats"
            :min="1"
            :max="4">
            <el-checkbox label="歌剧院">
              <img src="http://www.chncpa.org/ycgp_220/cgzwt/gjy1/201607/P020160823576857440865.jpg"/>
            </el-checkbox>
            <el-checkbox label="小剧场">
              <img src="http://www.chncpa.org/ycgp_220/cgzwt/xjc2/201607/P020160823577871558172.jpg"/>
            </el-checkbox>
            <el-checkbox label="音乐厅">
              <img src="http://www.chncpa.org/ycgp_220/cgzwt/yyt1/201607/P020160823577391563903.jpg"/>
            </el-checkbox>
            <el-checkbox label="戏剧场">
              <img src="http://www.chncpa.org/ycgp_220/cgzwt/xjc1/201607/P020160823577654015951.jpg"/>
            </el-checkbox>
          </el-checkbox-group>
        </el-form-item>

      </el-form>

      <button @click="">注册</button>

    </div>


  </div>

</template>

<script>
  import {
    Button,
    Input,
    Form,
    FormItem,
    Select,
    Option,
    CheckboxGroup,
    Checkbox
  } from 'element-ui'

  export default {
    name: 'venue-register',
    components: {
      elButton: Button,
      elInput: Input,
      elForm: Form,
      elFormItem: FormItem,
      elSelect: Select,
      elOption: Option,
      elCheckboxGroup: CheckboxGroup,
      elCheckbox: Checkbox
    },
    data() {
      let checkVenueName = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('请输入场馆名'))
        } else {
          callback()
        }
      };
      let checkVenueAddress = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('请输入场馆名'))
        } else {
          callback()
        }
      };
      return {
        registerForm: {
          venueName: '',
          venueAddress: '',
          venueSeats: ['歌剧院'],
        },
        addresses: [
          {
            value: '选项1',
            label: '北京'
          }, {
            value: '选项2',
            label: '上海'
          }, {
            value: '选项3',
            label: '广州'
          }, {
            value: '选项4',
            label: '南京'
          }, {
            value: '选项5',
            label: '成都'
          }
        ],
        rules: {
          venueName: [
            {required: true, validator: checkVenueName, trigger: 'blur'}
          ],
          venueAddress: [
            {required: true, validator: checkVenueAddress, trigger: 'blur'}
          ]
        }
      }
    },
    computed: {},
    methods: {}
  }

</script>

<style scoped src="./VenueRegister.css"></style>
