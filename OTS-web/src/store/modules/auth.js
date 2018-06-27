import * as authApi from '../../api/auth'

const state = {
    loginType: null,
    registerStep: 0,
    ifSendVerifyCode: false,
    username: '',
    email: '',
    password: '',
    phone: '',
    currentUsername: '',
    currentUser: null,
    userOrders: [],
    userCoupons: [],
    modifyType: 'info'
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

  refreshUser({dispatch, commit}, {onSuccess, onError}) {
    const token = localStorage.getItem('token')
    if (token === null || token.indexOf('@') < 0) {
      if (onError) {
        onError('')
      }
    } else {
      // commit('saveLoginType', 'user');
      dispatch('fetchCurrentUser', {onSuccess, onError})
    }

  },

  signOut({commit}, {onSuccess}) {
    const username = state.currentUser.username
    localStorage.setItem('token', '')
    commit('saveCurrentUser', null)
    if (onSuccess) {
      onSuccess(username)
    }
  },

  editUserInfo({commit}, {userInfo, onSuccess, onError}) {
    authApi.editUserInfo((data => {
      if (data.result === true) {
        if (onSuccess) {
          onSuccess('修改成功！')
        }
      } else {
        onError(data.message)
      }
    }), userInfo)
  },

  editUserPassword({commit}, {info, onSuccess, onError}) {
    authApi.editUserPassword(data => {
      if (data.result === true) {
        if (onSuccess) {
          onSuccess('修改成功！')
        }
      } else {
        onError(data.message)
      }
    }, info)
  },

  exchangeCoupon({commit}, {info, onSuccess, onError}) {
    console.log('info', info)
    authApi.exchangeCoupon(data => {
      if (data.result === true) {
        if (onSuccess) {
          onSuccess('兑换成功！')
        }
      } else {
        onError(data.message)
      }
    }, info)
  },

  getUserOrders({commit, state}) {
    let userId = state.currentUser ? state.currentUser.userId : null
    authApi.getUserOrders(data => {
      if (data !== null && data !== undefined) {
        commit('saveUserOrders', data)
      }
    }, userId)
  },

  getUserCoupons({commit, state}) {
    let userId = state.currentUser ? state.currentUser.userId : null
    authApi.getUserCoupons(data => {
      if (data !== null && data !== undefined) {
        commit('saveUserCoupons', data)
      }
    }, userId)
  }

};

const mutations = {
  'saveLoginType'(state, loginType) {
    state.loginType = loginType
  },
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
  },
  'saveUserOrders'(state, userOrders) {
    state.userOrders = userOrders
  },
  'saveUserCoupons'(state, userCoupons) {
    state.userCoupons = userCoupons
  },
  'saveModifyType'(state, type) {
    state.modifyType = type
  }
};

export default {
  namespaced: true,
  state,
  // getters,
  actions,
  mutations
}
