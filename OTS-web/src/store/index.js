import Vue from 'vue'
import Vuex from 'vuex'
// import * as actions from './actions'
// import * as getters from './getters'
import auth from './modules/auth'
import venue from './modules/venue'
import login from './modules/login'
import manager from './modules/manager'
import showPlan from './modules/showPlan'
import order from './modules/order'
import createLogger from 'vuex/dist/logger'

Vue.use(Vuex)

const debug = process.env.NODE_ENV !== 'production'

export const strict = false

export default new Vuex.Store({
  // actions,
  // getters,
  modules: {
    auth,
    venue,
    login,
    manager,
    showPlan,
    order
  },
  strict: debug,
  plugins: debug ? [createLogger()] : []
})
