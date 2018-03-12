<template>

  <div>

    <div class="user-info-wrapper">

      <h1>个人信息</h1>

      <el-form :model="userInfoForm" :rules="rules" ref="userInfoForm" labelPosition="top">

        <el-form-item label="用户名" prop="username">
          <el-input v-model="userInfoForm.username"></el-input>
        </el-form-item>

        <el-form-item label="邮箱账号" prop="email">
          <el-input :disabled="true" v-model="userInfoForm.email"></el-input>
        </el-form-item>

        <el-form-item label="手机号" prop="phone">
          <el-input v-model="userInfoForm.phone"></el-input>
        </el-form-item>

        <el-form-item>
          <div class="button-wrapper">
            <button @click="submitInfoForm('userInfoForm')">修改</button>
          </div>
          <!--<el-button @click="resetForm('signInForm')">重置</el-button>-->
        </el-form-item>

      </el-form>


    </div>

    <div class="user-password-wrapper">
      <h1>修改密码</h1>

      <el-form :model="userPasswordForm" :rules="rules" ref="userPasswordForm" labelPosition="top">

        <el-form-item label="原密码" prop="oldPassword">
          <el-input type="password" v-model="userPasswordForm.oldPassword"></el-input>
        </el-form-item>

        <el-form-item label="新密码" prop="newPassword">
          <el-input type="password" v-model="userPasswordForm.newPassword"></el-input>
        </el-form-item>

        <el-form-item label="确认密码" prop="confirmPassword">
          <el-input type="password" v-model="userPasswordForm.confirmPassword"></el-input>
        </el-form-item>

        <el-form-item>
          <div class="button-wrapper">
            <button @click="submitPasswordForm('userPasswordForm')">修改</button>
          </div>
          <!--<el-button @click="resetForm('signInForm')">重置</el-button>-->
        </el-form-item>

      </el-form>

    </div>
  </div>


</template>

<script>
  import {Form, FormItem, Input} from 'element-ui'
  import {router} from '../../main'

  export default {
    name: 'user-info',
    components: {
      elForm: Form,
      elFormItem: FormItem,
      elInput: Input
    },
    props: ['user'],
    data() {
      let checkUsername = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('请输入用户名'))
        } else {
          callback()
        }
      };
      let checkEmail = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('请输入邮箱账号'))
        } else if (!/^([\w-_]+(?:\.[\w-_]+)*)@((?:[a-z0-9]+(?:-[a-zA-Z0-9]+)*)+\.[a-z]{2,6})$/i.test(value)) {
          return callback(new Error('请输入正确的邮箱格式'))
        } else {
          callback()
        }
      };
      let checkPhone = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('请输入手机号'))
        } else {
          callback()
        }
      };
      let validatePassword = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请输入密码'))
        } else {
          callback()
        }
      };
      let validateConfirmPassword = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.userPasswordForm.newPassword) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        userInfoForm: {
          username: this.user.username,
          email: this.user.email,
          phone: this.user.phone
        },
        userPasswordForm: {
          oldPassword: '',
          newPassword: '',
          confirmPassword: ''
        },
        rules: {
          username: [
            {validator: checkUsername, trigger: 'blur'}
          ],
          email: [
            {validator: checkEmail, trigger: 'blur'}
          ],
          phone: [
            {validator: checkPhone, trigger: 'blur'}
          ],
          oldPassword: [
            {validator: validatePassword, trigger: 'blur'}
          ],
          newPassword: [
            {validator: validatePassword, trigger: 'blur'}
          ],
          confirmPassword: [
            {validator: validateConfirmPassword, trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      submitInfoForm() {

      },
      submitPasswordForm() {

      }
    }
  }

</script>

<style scoped src="./UserInfo.css"></style>
