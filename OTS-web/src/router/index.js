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
import VenueHomePage from '@/pages/VenueHomePage'
import ShowDetailPage from '@/pages/ShowDetailPage'
import ShowSearchPage from '@/pages/ShowSearchPage'
import UserInfoPage from '@/pages/UserInfoPage'
import VenueInfoPage from '@/pages/VenueInfoPage'
import VenuePlanPage from '@/pages/VenuePlanPage'
import VenueSitePage from '@/pages/VenueSitePage'
import ManagerHomePage from '@/pages/ManagerHomePage'
import ManagerStatisticsPage from '@/pages/ManagerStatisticsPage'
import PayPage from '@/pages/PayPage'

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
      path: '/venue/:venueCode',
      name: 'VenueHomePage',
      component: VenueHomePage
    },
    {
      path: '/show/:showId',
      name: 'ShowDetailPage',
      component: ShowDetailPage,
      scrollBehavior (to, from, savedPosition) {
        return { x: 0, y: 0 }
      }
    },
    {
      path: '/search',
      name: 'ShowSearchPage',
      component: ShowSearchPage
    },
    {
      path: '/user/me/info',
      name: 'UserInfoPage',
      component: UserInfoPage
    },
    {
      path: '/venue/me/info',
      name: 'VenueInfoPage',
      component: VenueInfoPage
    },
    {
      path: '/venue/plan',
      name: 'VenuePlanPage',
      component: VenuePlanPage
    },
    {
      path: '/venue/site',
      name: 'VenueSitePage',
      component: VenueSitePage
    },
    {
      path: '/manager/:managerId',
      name: 'ManagerHomePage',
      component: ManagerHomePage
    },
    {
      path: '/manager/statistics',
      name: 'ManagerStatisticsPage',
      component: ManagerStatisticsPage
    },
    {
      path: '/order/:orderId/pay',
      name: 'PayPage',
      component: PayPage
    }
  ]
})
