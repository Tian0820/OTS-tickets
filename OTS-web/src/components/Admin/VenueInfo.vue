<template>

  <div>

    <div class="user-info-wrapper">

      <h1>场馆信息</h1>

      <el-form :model="venueInfoForm" :rules="rules" ref="venueInfoForm" labelPosition="top">

        <el-form-item label="场馆名" prop="venueName">
          <el-input v-model="venueInfoForm.venueName"></el-input>
        </el-form-item>

        <el-form-item label="场馆编号" prop="code">
          <el-input :disabled="true" v-model="venueInfoForm.code"></el-input>
        </el-form-item>

        <el-form-item label="场馆城市" prop="city">
          <el-input :disabled="true" v-model="venueInfoForm.city"></el-input>
        </el-form-item>

        <el-form-item label="具体地址" prop="address">
          <el-input v-model="venueInfoForm.address"></el-input>
        </el-form-item>


        <el-form-item>
          <div class="button-wrapper">
            <button @click="submitInfoForm('venueInfoForm')">修改</button>
          </div>
          <!--<el-button @click="resetForm('signInForm')">重置</el-button>-->
        </el-form-item>

      </el-form>


    </div>

    <div class="user-password-wrapper">
      <h1>修改密码</h1>

      <el-form :model="venuePasswordForm" :rules="rules" ref="venuePasswordForm" labelPosition="top">

        <el-form-item label="原密码" prop="oldPassword">
          <el-input type="password" v-model="venuePasswordForm.oldPassword"></el-input>
        </el-form-item>

        <el-form-item label="新密码" prop="newPassword">
          <el-input type="password" v-model="venuePasswordForm.newPassword"></el-input>
        </el-form-item>

        <el-form-item label="确认密码" prop="confirmPassword">
          <el-input type="password" v-model="venuePasswordForm.confirmPassword"></el-input>
        </el-form-item>

        <el-form-item>
          <div class="button-wrapper">
            <button @click="submitPasswordForm('venuePasswordForm')">修改</button>
          </div>
          <!--<el-button @click="resetForm('signInForm')">重置</el-button>-->
        </el-form-item>

      </el-form>

    </div>
  </div>


</template>

<script>
  import {Form, FormItem, Input, Message} from 'element-ui'
  import {router} from '../../main'
  import {mapActions} from 'vuex'

  export default {
    name: 'venue-info',
    components: {
      elForm: Form,
      elFormItem: FormItem,
      elInput: Input,
      Message
    },
    props: ['venue'],
    data() {
      let checkVenueName = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('请输入场馆名'))
        } else {
          callback()
        }
      };
      let checkAddress = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('请输入场馆地址'))
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
        } else if (value !== this.venuePasswordForm.newPassword) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        venueInfoForm: {
          venueName: this.venue.venueName,
          code: this.venue.code,
          city: this.venue.city,
          address: this.venue.address
        },
        venuePasswordForm: {
          oldPassword: '',
          newPassword: '',
          confirmPassword: ''
        },
        rules: {
          venueName: [
            {validator: checkVenueName, trigger: 'blur'}
          ],
          address: [
            {validator: checkAddress, trigger: 'blur'}
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
      ...mapActions('venue', [
        'editVenueInfo',
        'editVenuePassword'
      ]),
      submitInfoForm(data) {
        this.$refs[data].validate((valid) => {
          if (valid) {
            this.editVenueInfo({
              info: this.venueInfoForm,
              onSuccess: (success) => {
                Message.success(success)
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
      submitPasswordForm(data) {
        this.$refs[data].validate((valid) => {
          if (valid) {
            this.editVenuePassword({
              info: {
                id: this.venue.venueId,
                oldPassword: this.venuePasswordForm.oldPassword,
                newPassword: this.venuePasswordForm.newPassword
              },
              onSuccess: (success) => {
                Message.success(success)
                this.venuePasswordForm.oldPassword = ''
                this.venuePasswordForm.newPassword = ''
                this.venuePasswordForm.confirmPassword = ''
              },
              onError: (error) => {
                Message.error(error)
              }
            })
          } else {
            Message.error('请正确填写信息！')
          }
        })
      }
    }
  }

</script>

<style scoped src="./UserInfo.css"></style>
