import * as venueApi from '../../api/venue'

const state = {
    currentLogin: null
  }
;

const actions = {};

const mutations = {
  'saveCurrentLogin'(state, currentLogin) {
    state.currentLogin = currentLogin
  }
};

export default {
  namespaced: true,
  state,
  // getters,
  actions,
  mutations
}
