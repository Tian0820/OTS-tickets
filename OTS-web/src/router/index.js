import Vue from 'vue'
import Router from 'vue-router'
import IndexPage from '@/pages/IndexPage'
import UserRegisterPage from '@/pages/UserRegisterPage'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'IndexPage',
      component: IndexPage
    },
    {
      path: '/user/sign-up',
      name: 'UserRegisterPage',
      component: UserRegisterPage
    }
  ]
})
