<template>
  <div class="body-wrapper">
    <layout>
      <div class="container">
        <side-layout>
          <ost-menu slot="side" :defaultIndex="menuDefault" :items="menuItems" :onSelect="handleMenuSelect"></ost-menu>
          <user-home slot="main" v-if="user"></user-home>
        </side-layout>
      </div>
    </layout>
  </div>

</template>

<script>
  import {Message} from 'element-ui'
  import Layout from '../components/Layout/Layout.vue'
  import SideLayout from '../components/Layout/SideLayout.vue'
  import Menu from '../components/Layout/Menu.vue'
  import UserHome from '../components/UserHome/UserHome.vue'
  import ExchangeCouponModal from '../components/Modal/ExchangeCouponModal.vue'
  import {router, store} from '../main'
  import {mapState, mapMutations} from 'vuex'

  export default {
    name: 'user-home-page',
    components: {
      Layout,
      SideLayout,
      ostMenu:Menu,
      UserHome,
      ExchangeCouponModal
    },
    data() {
      return {
        menuItems:[
          {
            name: "个人信息",
            icon: "solution",
            index: "info"
          },
          {
            name: "我的订单",
            icon: "order",
            index: "order"
          },
          {
            name: "我的优惠券",
            icon: "coupon",
            index: "coupon"
          }
        ],
        menuDefault: 'info'
      }
    },
    computed: {
      ...mapState('auth', {
        user: state => state.currentUser,
        userOrders: state => state.userOrders,
        exchangeCouponModal: state => state.exchangeCouponModal
      })
    },
    methods: {
      ...mapMutations('auth', [
        'saveHomeType'
      ]),
      handleMenuSelect (key) {
        this.saveHomeType(key)
      },

    },
    beforeRouteEnter(to, from, next) {
      store.dispatch('auth/refreshUser', {
        onSuccess: (success) => {
          store.dispatch('auth/getUserOrders')
          store.dispatch('auth/getUserCoupons')
        },
        onError: (error) => {
          Message.error(error)
          router.push({name: 'IndexPage'})
        }
      })
      next(true)
    }
  }

</script>

<style scoped>
  .container {
    margin-top: 40px;
  }
</style>
