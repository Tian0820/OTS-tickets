<template>
  <div class="nav-wrapper">
    <div class="container">
      <div class="left-wrapper" @click="goToIndexPage">
        <img src="../../assets/img/logo.png" width="40">
        <h1>OTS</h1>
      </div>

      <div class="right-wrapper">

        <!--<div class="button-wrapper">-->
        <!--<el-button type="text" @click="">职位</el-button>-->
        <!--<el-button type="text" @click="">公司</el-button>-->
        <!--<el-button type="text" @click="">待遇</el-button>-->
        <!--<el-button type="text" @click="">技能</el-button>-->
        <!--</div>-->

        <!--<div class="input-wrapper">-->
          <!--<el-input class="search-input"-->
                    <!--placeholder=""-->
                    <!--suffix-icon="el-icon-search"-->
                    <!--v-model="input2"-->
          <!--&gt;-->
          <!--</el-input>-->
        <!--</div>-->


        <div class="button-wrapper">

          <div v-if="user === null && venue === null && manager === null">
            <el-button type="text" @click="goToLoginPage">登录</el-button>
            <el-button type="text" @click="goToRegisterPage">注册</el-button>
          </div>

          <div v-else-if="user">
            <el-dropdown placement="bottom-start" @command="handleUserCommand">
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

          <div v-else-if="venue">
            <el-dropdown placement="bottom-start" @command="handleVenueCommand">
          <span class="el-dropdown-link">
          {{venue.venueName}}<i class="el-icon-caret-bottom el-icon--right"></i>
          </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="VenueHomePage">场馆主页</el-dropdown-item>
                <el-dropdown-item command="VenuePlanPage">发布计划</el-dropdown-item>
                <el-dropdown-item command="VenueSitePage">现场卖票</el-dropdown-item>
                <el-dropdown-item command="VenueInfoPage">修改信息</el-dropdown-item>
                <el-dropdown-item command="signOut">退出账号</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>

          <div v-else-if="manager">
            <el-dropdown placement="bottom-start" @command="handleManagerCommand">
          <span class="el-dropdown-link">
          {{manager.managerName}}<i class="el-icon-caret-bottom el-icon--right"></i>
          </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="ManagerHomePage">审批中心</el-dropdown-item>
                <el-dropdown-item command="ManagerStatisticsPage">统计信息</el-dropdown-item>
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
    <venue-code-modal></venue-code-modal>
    <user-account-modal></user-account-modal>
    <ban-user-modal></ban-user-modal>
    <refund-modal></refund-modal>

  </div>
</template>


<script>
  import ExchangeCouponModal from '../Modal/ExchangeCouponModal.vue'
  import ChooseSeatModal from '../Modal/ChooseSeatModal.vue'
  import BuyTicketsModal from '../Modal/BuyTicketsModal.vue'
  import PayModal from '../Modal/PayModal.vue'
  import VenueCodeModal from '../Modal/VenueCodeModal.vue'
  import UserAccountModal from '../Modal/UserAccountModal.vue'
  import BanUserModal from '../Modal/BanUserModal.vue'
  import RefundModal from '../Modal/RefundModal.vue'
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
      VenueCodeModal,
      UserAccountModal,
      BanUserModal,
      RefundModal,
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
      }),
      ...mapState('venue', {
        venue: state => state.currentVenue
      }),
      ...mapState('manager', {
        manager: state => state.currentManager
      })
    },
    methods: {
      ...mapActions('auth', [
        'signOut'
      ]),
      ...mapActions('venue', [
        'venueSignOut'
      ]),
      ...mapActions('manager', [
        'managerSignOut'
      ]),
      ...mapMutations('login', [
        'saveCurrentLogin'
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
      handleUserCommand(command) {
        if (command !== 'signOut') {
          if (command === 'UserHomePage') {
            router.push({name: 'UserHomePage', params: {userId: this.user.userId}})
          } else {
            router.push({name: command})
          }
          router.push({name: command})
        } else {
          this.saveCurrentLogin(null)
          this.signOut({
            onSuccess: (username) => {
              Message({
                message: 'Goodbye, ' + username + '!',
                type: 'success'
              });
              router.push({name: 'IndexPage'})
            }
          });
        }
      },
      handleVenueCommand(command) {
        if (command !== 'signOut') {
          if (command === 'VenueHomePage') {
            router.push({name: 'VenueHomePage', params: {venueCode: this.venue.code}})
          } else {
            router.push({name: command})
          }
          router.push({name: command})
        } else {
          this.venueSignOut({
            onSuccess: (venueName) => {
              Message({
                message: 'Goodbye, ' + venueName + '!',
                type: 'success'
              });
              router.push({name: 'IndexPage'})
            }
          });
        }
      },
      handleManagerCommand(command) {
        if (command !== 'signOut') {
          if (command === 'ManagerHomePage') {
            router.push({name: 'ManagerHomePage', params: {managerId: this.manager.managerId}})
          } else {
            router.push({name: command})
          }
          router.push({name: command})
        } else {
          this.managerSignOut({
            onSuccess: (success) => {
              Message({
                message: 'Goodbye, tian !',
                type: 'success'
              });
              router.push({name: 'IndexPage'})
            }
          });
        }
      }
    }
  }
</script>

<style scoped src="./Navigator.css"></style>
