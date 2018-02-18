import * as authApi from '../../api/auth'

const state = {
  registerStep: 0,
};

const actions = {};

const mutations = {
  'saveRegisterStep'(state, registerStep) {
    state.registerStep = registerStep
  }
};

export default {
  namespaced: true,
  state,
  // getters,
  actions,
  mutations
}
