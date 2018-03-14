import * as venueApi from '../../api/venue'

const state = {
    currentVenue: null,
    venueCode: ''
  }
;

const actions = {
  venueRegister({commit, state}, {info, onSuccess, onError}) {
    venueApi.venueRegister(data => {
      if (data.result === true) {
        if (onSuccess) {
          commit('saveVenueCode', data.message)
          onSuccess('场馆注册成功！')
        }
      } else {
        onError(data.message)
      }
    }, info)
  },

  venueLogin({dispatch}, {info, onSuccess, onError}) {
    venueApi.signIn(data => {
      if (data.result === false) {
        onError(data.message)
      } else {
        console.log(data)
        localStorage.setItem('token', data.message)
        dispatch('fetchCurrentVenue', {onSuccess})
      }
    }, info)
  },

  fetchCurrentVenue({commit, state}, {onSuccess, onError}) {
    let currentVenue = localStorage.getItem('token')
    venueApi.fetchCurrentVenue(data => {
      console.log('fetchCurrentVenue', data)
      commit('saveCurrentVenue', data)
      if (data !== null) {
        if (onSuccess) {
          onSuccess(state.currentVenue.venueName)
        }
      } else {
        onError('登录已过期，请重新登录！')
      }
    }, currentVenue)
  },

  refreshVenue({dispatch, commit}, {onSuccess, onError}) {
    const token = localStorage.getItem('token')
    if (token === null || token.length !== 7) {
      if (onError) {
        onError('')
      }
    } else {
      dispatch('fetchCurrentVenue', {onSuccess, onError})
    }
  },

  venueSignOut({commit, state}, {onSuccess}) {
    const venueName = state.currentVenue.venueName
    localStorage.setItem('token', null)
    commit('saveCurrentVenue', null)
    if (onSuccess) {
      onSuccess(venueName)
    }
  },


};

const mutations = {
  'saveCurrentVenue'(state, currentVenue) {
    state.currentVenue = currentVenue
  },

  'saveVenueCode'(state, venueCode) {
    state.venueCode = venueCode
  }
};

export default {
  namespaced: true,
  state,
  // getters,
  actions,
  mutations
}
