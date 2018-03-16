import * as managerApi from '../../api/manager'

const state = {
    currentManager: null
  }
;

const actions = {

  managerLogin({dispatch}, {info, onSuccess, onError}) {
    managerApi.signIn(data => {
      if (data.result === false) {
        onError(data.message)
      } else {
        console.log(data)
        localStorage.setItem('token', 'tian')
        dispatch('fetchCurrentManager', {onSuccess})
      }
    }, info)
  },

  fetchCurrentManager({commit, state}, {onSuccess, onError}) {
    let currentVenue = localStorage.getItem('token')
    managerApi.fetchCurrentManager(data => {
      console.log('fetchCurrentManager', data)
      commit('saveCurrentManager', data)
      if (data !== null) {
        if (onSuccess) {
          onSuccess(state.currentManager.managerName)
        }
      } else {
        onError('登录已过期，请重新登录！')
      }
    }, currentVenue)
  },

  refreshManager({dispatch, commit}, {onSuccess, onError}) {
    const token = localStorage.getItem('token')
    if (token === null || token !== 'tian') {
      if (onError) {
        onError('')
      }
    } else {
      dispatch('fetchCurrentManager', {onSuccess, onError})
    }
  },

  managerSignOut({commit, state}, {onSuccess}) {
    localStorage.setItem('token', null)
    commit('saveCurrentManager', null)
    if (onSuccess) {
      onSuccess('')
    }
  },


};

const mutations = {
  'saveCurrentManager'(state, currentManager) {
    state.currentManager = currentManager
  }
};

export default {
  namespaced: true,
  state,
  // getters,
  actions,
  mutations
}
