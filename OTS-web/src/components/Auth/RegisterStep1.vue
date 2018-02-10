<template>


  <div class="form-wrapper">
    <el-form :model="registerForm" :rules="rules" ref="registerForm" labelPosition="top">

      <el-form-item label="用户名" prop="username">
        <el-input v-model="registerForm.username"></el-input>
      </el-form-item>

      <el-form-item label="邮箱账号" prop="email">
        <el-input class="email-input" v-model="registerForm.email"></el-input>
        <button class="verify-button">发送验证码</button>
      </el-form-item>

      <el-form-item label="验证码（请填写邮箱中收到的验证码）" prop="code">
        <el-input v-model="registerForm.code"></el-input>
      </el-form-item>

    </el-form>

    <button>下一步</button>
  </div>


</template>

<script>
  import {
    Button,
    Input,
    Form,
    FormItem,
  } from 'element-ui'


  export default {
    name: 'register-step1',
    components: {
      elButton: Button,
      elInput: Input,
      elForm: Form,
      elFormItem: FormItem,
    },
    data() {
      let checkUsername = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('请输入用户名'))
        } else {
          callback()
        }
      }
      let checkEmail = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('请输入邮箱账号'))
        } else if (!/^([\w-_]+(?:\.[\w-_]+)*)@((?:[a-z0-9]+(?:-[a-zA-Z0-9]+)*)+\.[a-z]{2,6})$/i.test(value)) {
          return callback(new Error('请输入正确的邮箱格式'))
        } else {
          callback()
        }
      }
      let checkCode = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('请输入验证码'))
        } else {
          callback()
        }
      }
      return {
        registerForm: {
          username: '',
          email: '',
          code: ''
        },
        rules: {
          username: [
            {required: true, validator: checkUsername, trigger: 'blur'}
          ],
          email: [
            {required: true, validator: checkEmail, trigger: 'blur'}
          ],
          code: [
            {required: true, validator: checkCode, trigger: 'blur'}
          ]
        }
      }
    },
    methods: {}
  }

</script>

<style scoped src="./RegisterStep1.css"></style>
