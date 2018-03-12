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

          <div v-if="user === null || user === ''">
            <el-button type="text" @click="goToLoginPage">登录</el-button>
            <el-button type="text" @click="goToRegisterPage">注册</el-button>
          </div>

          <div v-else>
            <el-dropdown placement="bottom-start" @command="handleCommand">
          <span class="el-dropdown-link">
          {{user.username}}<i class="el-icon-caret-bottom el-icon--right"></i>
          </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="UserHomePage">我的主页</el-dropdown-item>
                <el-dropdown-item command="UserInfoPage">修改资料</el-dropdown-item>
                <el-dropdown-item command="signOut">退出账号</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </div>
      </div>

    </div>

    <exchange-coupon-modal></exchange-coupon-modal>
    <choose-seat-modal></choose-seat-modal>
    <buy-tickets-modal></buy-tickets-modal>
    <pay-modal></pay-modal>

  </div>
</template>


<script>
  import ExchangeCouponModal from '../Modal/ExchangeCouponModal.vue'
  import ChooseSeatModal from '../Modal/ChooseSeatModal.vue'
  import BuyTicketsModal from '../Modal/BuyTicketsModal.vue'
  import PayModal from '../Modal/PayModal.vue'
  import {Input, Button, Dropdown, DropdownMenu, DropdownItem, Message} from 'element-ui'
  import {router} from '../../main'
  import {mapMutations, mapState, mapActions} from 'vuex'

  //  Vue.use(Input)

  export default {
    name: 'navigator',
    components: {
      ExchangeCouponModal,
      ChooseSeatModal,
      BuyTicketsModal,
      PayModal,
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
    computed: {
      ...mapState('auth', {
        user: state => state.currentUser
      })
    },
    methods: {
      ...mapActions('auth', [
        'signOut'
      ]),
      goToIndexPage() {
        router.push({name: 'IndexPage'})
      },
      goToLoginPage() {
        router.push({name: 'LoginPage'})
      },
      goToRegisterPage() {
        router.push({name: 'RegisterPage'})
      },
      handleCommand(command) {
        if (command !== 'signOut') {
          if (command === 'UserHomePage') {
            router.push({name: 'UserHomePage', params: {userId: this.user.userId}})
          } else {
            router.push({name: command})
          }
          router.push({name: command})
        } else {
          this.signOut({
            onSuccess: (username) => {
              Message({
                message: 'Goodbye, ' + username + '!',
                type: 'success'
              });
            }
          });
        }
      },
    }
  }
</script>

<style scoped src="./Navigator.css"></style>
