<template>

  <div class="form-wrapper">
    <el-form :model="registerForm" :rules="rules" ref="registerForm" labelPosition="top">

      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="registerForm.password" auto-complete="off"></el-input>
      </el-form-item>

      <el-form-item label="确认密码" prop="confirmPassword">
        <el-input type="password" v-model="registerForm.confirmPassword" auto-complete="off"></el-input>
      </el-form-item>

    </el-form>

    <button @click="goToNextStep('registerForm')">下一步</button>
  </div>

</template>

<script>
  import {
    Button,
    Input,
    Form,
    FormItem,
    Message
  } from 'element-ui'
  import {mapMutations} from 'vuex'


  export default {
    name: 'register-step2',
    components: {
      elButton: Button,
      elInput: Input,
      elForm: Form,
      elFormItem: FormItem,
    },
    data() {
      let validatePassword = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请输入密码'))
        } else {
          callback()
        }
      }
      let validateConfirmPassword = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请再次输入密码'))
        } else if (value !== this.registerForm.password) {
          callback(new Error('两次输入密码不一致!'))
        } else {
          callback()
        }
      }
      return {
        registerForm: {
          password: '',
          confirmPassword: ''
        },
        rules: {
          password: [
            {required: true, validator: validatePassword, trigger: 'blur'}
          ],
          confirmPassword: [
            {required: true, validator: validateConfirmPassword, trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      ...mapMutations('auth', [
        'saveRegisterStep',
        'savePassword'
      ]),
      goToNextStep(data) {
        this.$refs[data].validate((valid) => {
          if (valid) {
            this.savePassword(this.registerForm.password)
            this.saveRegisterStep(2)
          } else {
            Message.error('请正确输入密码！')
          }
        })
      }
    }
  }

</script>

<style scoped src="./RegisterStep1.css"></style>
