import * as authApi from '../../api/auth'

const state = {
    registerStep: 0,
    ifSendVerifyCode: false,
    username: '',
    email: '',
    password: '',
    phone: ''
  }
;

const actions = {

  sendVerifyCode({commit}, {info, onSuccess, onError}) {
    authApi.sendVerifyCode(data => {
      if (data.result === true) {
        if (onSuccess) {
          commit('saveIfSendVerifyCode', true)
          onSuccess('发送成功！')
        }
      } else {
        onError(data.message)
      }
    }, info)
  },

  fetchVerifyResult({commit}, {info, onSuccess, onError}) {
    authApi.fetchVerifyResult(data => {
      if (data.result === true) {
        if (onSuccess) {
          onSuccess('验证成功！')
        }
      } else {
        onError(data.message)
      }
    }, info)
  },

  userRegister({commit, state}, {onSuccess, onError}) {
    let info = {
      username: state.username,
      email: state.email,
      password: state.password,
      phone: state.phone
    }
    authApi.userRegister(data => {
      if (data.result === true) {
        if (onSuccess) {
          onSuccess('会员注册成功！')
        }
      } else {
        onError(data.message)
      }
    }, info)
  }

};

const mutations = {
  'saveRegisterStep'(state, registerStep) {
    state.registerStep = registerStep
  },
  'saveIfSendVerifyCode'(state, ifSendVerifyCode) {
    state.ifSendVerifyCode = ifSendVerifyCode
  },
  'saveUsername'(state, username) {
    state.username = username
  },
  'saveEmail'(state, email) {
    state.email = email
  },
  'savePassword'(state, password) {
    state.password = password
  },
  'savePhone'(state, phone) {
    state.phone = phone
  },
};

export default {
  namespaced: true,
  state,
  // getters,
  actions,
  mutations
}
