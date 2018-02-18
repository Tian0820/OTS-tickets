<template>

  <div class="form-wrapper">
    <el-form :model="registerForm" :rules="rules" ref="registerForm" labelPosition="top">

      <el-form-item label="手机号" prop="phone">
        <el-input v-model="registerForm.phone"></el-input>
      </el-form-item>

    </el-form>

    <button @click="uploadRegister">完成注册</button>
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
    name: 'register-step3',
    components: {
      elButton: Button,
      elInput: Input,
      elForm: Form,
      elFormItem: FormItem,
    },
    data() {
      let checkPhone = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('请输入手机号'))
        } else if (!/^1[34578]\d{9}$/.test(value)) {
          return callback(new Error('请输入正确的手机号'))
        } else {
          callback()
        }
      }
      return {
        registerForm: {
          phone: ''
        },
        rules: {
          phone: [
            {required: true, validator: checkPhone, trigger: 'blur'}
          ],
        }
      }
    },
    methods: {
      ...mapMutations('auth', [
        'saveRegisterStep'
      ]),
      uploadRegister() {
        this.saveRegisterStep(0)
      }
    }
  }

</script>

<style scoped src="./RegisterStep1.css"></style>
