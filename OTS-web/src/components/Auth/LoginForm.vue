<template>
  <div class="auth-wrapper">

    <div class="left-form-wrapper">
      <h1>登&nbsp;&nbsp;录</h1>

      <el-form :model="LoginForm" :rules="rules" ref="LoginForm" labelPosition="top">

        <el-form-item :label="label" prop="email" auto-complete="off">
          <el-input v-model="LoginForm.email"></el-input>
        </el-form-item>

        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="LoginForm.password" auto-complete="off"></el-input>
        </el-form-item>

        <el-form-item>
          <div class="go-sign-up">
            <span>还没有账号？</span>
            <el-button
              type="text"
              @click="goToRegisterPage"
            >
              去注册
            </el-button>
          </div>
          <div class="sign-in-button-wrapper">
            <el-button type="text" @click="submitForm('LoginForm')">登录</el-button>
          </div>
        </el-form-item>

      </el-form>

    </div>

  </div>
</template>

<script>
  import {Button, Input, Form, FormItem, Message} from 'element-ui'
  import {router} from '../../main'
  import {mapActions, mapState, mapMutations} from 'vuex'

  export default {
    name: 'login-form-page',
    components: {
      elButton: Button,
      elInput: Input,
      elForm: Form,
      elFormItem: FormItem,
      Message
    },
    data() {
      let checkUsername = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('请输入内容'))
        } else {
          callback()
        }
      }
      let validatePassword = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'))
        } else {
          callback()
        }
      }
      let label = ''
      if (this.type === 'user') {
        label = '用户邮箱'
      } else if (this.type === 'venue') {
        label = '场馆编号'
      } else {
        label = '经理名称'
      }
      return {
        label: label,
        activeName: 'first',
        LoginForm: {
          password: '',
          email: '',
        },
        rules: {
          password: [
            {validator: validatePassword, trigger: 'blur'}
          ],
          email: [
            {validator: checkUsername, trigger: 'blur'}
          ]
        }
      }
    },
    props: ['type'],
    computed: {
      ...mapState('auth', {
        user: state => state.user
      })
    },
    methods: {
      ...mapActions('auth', [
        'signIn'
      ]),
      ...mapActions('venue', [
        'venueLogin'
      ]),
      ...mapActions('manager', [
        'managerLogin'
      ]),
      ...mapMutations('auth', [
        'saveCurrentUsername'
      ]),
      goToRegisterPage() {
        router.push({name: 'UserRegisterPage'})
      },
      submitForm(data) {
        this.$refs[data].validate((valid) => {
          if (valid) {
            console.log('sign in !')
            if (this.type === 'user') {
              this.saveCurrentUsername(this.LoginForm.email)
              this.signIn({
                info: this.LoginForm,
                onSuccess: (success) => {
                  Message({
                    message: '欢迎，' + success + '！',
                    type: 'success'
                  })
                  router.push({name: 'IndexPage'})
                },
                onError: (error) => {
                  Message.error(error)
                }
              })
            } else if (this.type === 'venue') {
              this.venueLogin({
                info: {
                  code: this.LoginForm.email,
                  password: this.LoginForm.password
                },
                onSuccess: (success) => {
                  Message({
                    message: '欢迎，' + success + '！',
                    type: 'success'
                  })
                  router.push({name: 'IndexPage'})
                },
                onError: (error) => {
                  Message.error(error)
                }
              })
            } else {
              this.managerLogin({
                info: {
                  managerName: this.LoginForm.email,
                  password: this.LoginForm.password
                },
                onSuccess: (success) => {
                  Message({
                    message: '欢迎，' + success + '！',
                    type: 'success'
                  })
                  router.push({name: 'IndexPage'})
                },
                onError: (error) => {
                  Message.error(error)
                }
              })
            }

          } else {
            Message.error('请正确填写信息！')
          }
        })
      }
    }
  }
</script>

<style scoped src="./LoginForm.css"></style>
