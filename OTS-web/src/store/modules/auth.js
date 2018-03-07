import * as authApi from '../../api/auth'

const state = {
    registerStep: 0,
    ifSendVerifyCode: false,
    username: '',
    email: '',
    password: '',
    phone: '',
    currentUsername: '',
    currentUser: null,
  }
;

const actions = {

  sendVerifyCode({commit}, {info, onSuccess, onError}) {
    commit('saveIfSendVerifyCode', true)
    authApi.sendVerifyCode(data => {
      if (data.result === true) {
        if (onSuccess) {
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
  },

  signIn({dispatch, state}, {info, onSuccess, onError}) {
    authApi.signIn(data => {
      if (data.result === false) {
        onError(data.message)
      } else {
        console.log(data)
        localStorage.setItem('token', state.currentUsername)
        dispatch('fetchCurrentUser', {onSuccess})
      }
    }, info)
  },

  fetchCurrentUser({commit, state}, {onSuccess, onError}) {
    let currentUsername = localStorage.getItem('token')
    authApi.fetchCurrentUser(data => {
      console.log('fetchCurrentUser', data)
      commit('saveCurrentUser', data)
      if (data !== null) {
        if (onSuccess) {
          onSuccess(state.currentUser.username)
        }
      } else {
        onError('登录已过期，请重新登录！')
      }
    }, currentUsername)
  },

  refreshUser({dispatch}, {onSuccess, onError}) {
    const token = localStorage.getItem('token')
    if (token !== null) {
      dispatch('fetchCurrentUser', {onSuccess, onError})
    }
  },

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
  'saveCurrentUser'(state, currentUser) {
    state.currentUser = currentUser
  },
  'saveCurrentUsername'(state, currentUsername) {
    state.currentUsername = currentUsername
  }
};

export default {
  namespaced: true,
  state,
  // getters,
  actions,
  mutations
}
