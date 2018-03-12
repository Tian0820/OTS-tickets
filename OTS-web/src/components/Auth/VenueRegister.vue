<template>

  <div class="register-wrapper">

    <h1>OTS场馆注册</h1>

    <div class="form-wrapper">
      <el-form :model="registerForm" :rules="rules" ref="registerForm" labelPosition="top">

        <el-form-item label="场馆名" prop="venueName">
          <el-input v-model="registerForm.venueName"></el-input>
        </el-form-item>

        <el-form-item label="场馆城市" prop="venueCity">
          <el-select v-model="registerForm.venueCity" placeholder="请选择地点">
            <el-option
              v-for="item in addresses"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="具体地点" prop="venueAddress">
          <el-input v-model="registerForm.venueAddress"></el-input>
        </el-form-item>

        <el-form-item label="座位情况" prop="venueSeats">
          <el-radio-group
            v-model="registerForm.venueSeats"
          >
            <el-radio label="大剧场">
              <p>大剧场：一共分为六个区，每个区100个座位</p>
              <img src="http://www.chncpa.org/ycgp_220/cgzwt/gjy1/201607/P020160823576857440865.jpg"/>

            </el-radio>
            <el-radio label="小剧场">
              <p>小剧场：一共分为三个区，每个区100个座位</p>
              <img src="http://www.chncpa.org/ycgp_220/cgzwt/xjc2/201607/P020160823577871558172.jpg"/>
            </el-radio>
          </el-radio-group>
        </el-form-item>

      </el-form>

      <button @click="submitForm('registerForm')">注册</button>

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
    Checkbox,
    Radio,
    RadioGroup,
    Message
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
      elCheckbox: Checkbox,
      elRadioGroup: RadioGroup,
      elRadio: Radio,
      Message
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
          return callback(new Error('请输入场馆地址'))
        } else {
          callback()
        }
      };
      return {
        registerForm: {
          venueName: '',
          venueCity: '北京',
          venueSeats: '大剧场',
          venueAddress: ''
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
          venueCity: [
            {required: true, validator: checkVenueAddress, trigger: 'blur'}
          ],
          venueAddress: [
            {required: true, validator: checkVenueAddress, trigger: 'blur'}
          ]
        }
      }
    },
    computed: {},
    methods: {
      submitForm(data) {
        this.$refs[data].validate((valid) => {
          if (valid) {

          } else {
            Message.error('请正确填写信息！')
          }

        })
      }
    }
  }

</script>

<style scoped src="./VenueRegister.css"></style>
