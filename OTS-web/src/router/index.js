import Vue from 'vue'
import Router from 'vue-router'
import IndexPage from '@/pages/IndexPage'
import UserRegisterPage from '@/pages/UserRegisterPage'
import RegisterPage from '@/pages/RegisterPage'
import VenueRegisterPage from '@/pages/VenueRegisterPage'
import LoginPage from '@/pages/LoginPage'

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
    },
    {
      path: '/sign-up',
      name: 'RegisterPage',
      component: RegisterPage
    },
    {
      path: '/venue/sign-up',
      name: 'VenueRegisterPage',
      component: VenueRegisterPage
    },
    {
      path: '/sign-in',
      name: 'LoginPage',
      component: LoginPage
    }
  ]
})
