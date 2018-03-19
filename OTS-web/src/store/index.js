import Vue from 'vue'
import Vuex from 'vuex'
// import * as actions from './actions'
// import * as getters from './getters'
import auth from './modules/auth'
import venue from './modules/venue'
import login from './modules/login'
import manager from './modules/manager'
import showPlan from './modules/showPlan'
import createLogger from 'vuex/dist/logger'

Vue.use(Vuex)

const debug = process.env.NODE_ENV !== 'production'

export default new Vuex.Store({
  // actions,
  // getters,
  modules: {
    auth,
    venue,
    login,
    manager,
    showPlan
  },
  strict: debug,
  plugins: debug ? [createLogger()] : []
})
