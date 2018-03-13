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
