import * as authApi from '../../api/auth'

const state = {
    registerStep: 0,
    ifSendVerifyCode: false,
  }
;

const actions = {

  sendVerifyCode({commit}, {info, onSuccess, onError}) {
    authApi.sendVerifyCode((data => {
      if (data.result === true) {
        if (onSuccess) {
          commit('saveIfSendVerifyCode', true)
          onSuccess('发送成功！')
        }
      } else {
        onError(data.message)
      }
    }), info)
  }
};

const mutations = {
  'saveRegisterStep'(state, registerStep) {
    state.registerStep = registerStep
  },
  'saveIfSendVerifyCode'(state, ifSendVerifyCode) {
    state.ifSendVerifyCode = ifSendVerifyCode
  }
};

export default {
  namespaced: true,
  state,
  // getters,
  actions,
  mutations
}
