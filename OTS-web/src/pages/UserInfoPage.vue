<template>
  <div class="body-wrapper">
    <layout>
      <div class="container">
        <side-layout>
          <ost-menu slot="side" :defaultIndex="menuDefault" :items="menuItems" :onSelect="handleMenuSelect"></ost-menu>
          <user-info slot="main" v-if="user" :user="user"></user-info>
        </side-layout>

      </div>
    </layout>
  </div>

</template>

<script>
  import UserInfo from '../components/Admin/UserInfo.vue'
  import Layout from '../components/Layout/Layout.vue'
  import SideLayout from '../components/Layout/SideLayout.vue'
  import Menu from '../components/Layout/Menu.vue'
  import {Message} from 'element-ui'
  import {router, store} from '../main'
  import {mapState, mapMutations} from 'vuex'

  export default {
    name: 'user-info-page',
    components: {
      Layout,
      SideLayout,
      ostMenu:Menu,
      UserInfo
    },
    data() {
      return {
        menuItems:[
          {
            name: "修改资料",
            icon: "solution",
            index: "info"
          },
          {
            name: "修改密码",
            icon: "lock",
            index: "password"
          }
        ],
        menuDefault: 'info'
      }
    },
    computed: {
      ...mapState('auth', {
        user: state => state.currentUser
      })
    },
    methods: {
      ...mapMutations('auth', [
        'saveModifyType'
      ]),
      handleMenuSelect (key) {
        this.saveModifyType(key)
      }
    },
    beforeRouteEnter(to, from, next) {
      store.dispatch('auth/refreshUser', {
        onSuccess: (success) => {
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
