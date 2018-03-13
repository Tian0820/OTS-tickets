import Vue from 'vue'
import Router from 'vue-router'
import IndexPage from '@/pages/IndexPage'
import UserRegisterPage from '@/pages/UserRegisterPage'
import RegisterPage from '@/pages/RegisterPage'
import VenueRegisterPage from '@/pages/VenueRegisterPage'
import LoginPage from '@/pages/LoginPage'
import UserLoginPage from '@/pages/UserLoginPage'
import VenueLoginPage from '@/pages/VenueLoginPage'
import ManagerLoginPage from '@/pages/ManagerLoginPage'
import UserHomePage from '@/pages/UserHomePage'
import ShowDetailPage from '@/pages/ShowDetailPage'
import UserInfoPage from '@/pages/UserInfoPage'

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
    },
    {
      path: '/user/sign-in',
      name: 'UserLoginPage',
      component: UserLoginPage
    },
    {
      path: '/venue/sign-in',
      name: 'VenueLoginPage',
      component: VenueLoginPage
    },
    {
      path: '/manager/sign-in',
      name: 'ManagerLoginPage',
      component: ManagerLoginPage
    },
    {
      path: '/user/:userId',
      name: 'UserHomePage',
      component: UserHomePage
    },
    {
      path: '/show/:showId',
      name: 'ShowDetailPage',
      component: ShowDetailPage
    },
    {
      path: '/user/me/info',
      name: 'UserInfoPage',
      component: UserInfoPage
    }
  ]
})
