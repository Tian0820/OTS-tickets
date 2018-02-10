<template>
  <div class="nav-wrapper">
    <div class="container">
      <div class="left-wrapper" @click="goToIndexPage">
        <img src="../../assets/img/logo.png" width="30">
      </div>

      <div class="right-wrapper">

        <div class="button-wrapper">
          <el-button type="text" @click="">职位</el-button>
          <el-button type="text" @click="">公司</el-button>
          <el-button type="text" @click="">待遇</el-button>
          <el-button type="text" @click="">技能</el-button>
        </div>

        <div class="input-wrapper">
          <el-input class="search-input"
                    placeholder=""
                    suffix-icon="el-icon-search"
                    v-model="input2"
          >
          </el-input>
        </div>


        <div class="button-wrapper">

          <el-button type="text" @click="goToLoginPage">登录</el-button>
          <el-button type="text" @click="goToRegisterPage">注册</el-button>

          <!--<el-dropdown placement="bottom-start" @command="">-->
          <!--<span class="el-dropdown-link">-->
          <!--用户<i class="el-icon-caret-bottom el-icon&#45;&#45;right"></i>-->
          <!--</span>-->
          <!--<el-dropdown-menu slot="dropdown">-->
          <!--<el-dropdown-item command="UserHomePage">个人主页</el-dropdown-item>-->
          <!--<el-dropdown-item command="AccountPage">修改资料</el-dropdown-item>-->
          <!--<el-dropdown-item command="signOut">退出账号</el-dropdown-item>-->
          <!--</el-dropdown-menu>-->
          <!--</el-dropdown>-->
        </div>
      </div>

    </div>

  </div>
</template>


<script>
  import {Input, Button, Dropdown, DropdownMenu, DropdownItem, Message} from 'element-ui'
  import {router} from '../../main'
  //  import { mapMutations, mapState, mapActions } from 'vuex'

  //  Vue.use(Input)

  export default {
    name: 'navigator',
    components: {
      elInput: Input,
      elButton: Button,
      elDropdown: Dropdown,
      elDropdownMenu: DropdownMenu,
      elDropdownItem: DropdownItem,
    },
    data() {
      return {
        input2: ''
      }
    },
    computed: {},
    methods: {
      goToIndexPage() {
        router.push({name: 'IndexPage'})
      },
      goToLoginPage() {
        router.push({name: 'LoginPage'})
      },
      goToRegisterPage() {
        router.push({name: 'RegisterPage'})
      },
    }
  }
</script>

<style scoped src="./Navigator.css"></style>
