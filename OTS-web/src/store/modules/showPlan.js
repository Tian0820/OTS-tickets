import * as showApi from '../../api/showPlan'

const state = {
  allShowPlans: null,
  currentShow: null,
  chosenArea: 1,
  chosenSeats: [],
  page: null,
  size: 8,
  totalPages: null,
};

const actions = {

  fetchAllShowPlans({commit, state}, page) {
    showApi.fetchAllShowPlans(data => {
      if (data !== null) {
        commit('saveAllShowPlans', data)
      }
    }, 8, page)
  },

  fetchShowPlanById({commit}, id) {
    showApi.fetchShowPlanById(data => {
      if (data !== null) {
        data.seats.sort(function (a, b) {
          return a.id - b.id
        })
        commit('saveCurrentShow', data)
      }
    }, id)
  }
};

const mutations = {
  'saveAllShowPlans'(state, data) {
    state.allShowPlans = data.content
    state.page = data.number + 1
    state.totalPages = data.totalPages
  },
  'saveCurrentShow'(state, currentShow) {
    currentShow.seats.sort(function (a, b) {
      return a.number - b.number
    })

    state.currentShow = currentShow
  },
  'saveChosenArea'(state, chosenArea) {
    state.chosenArea = chosenArea
  },
  'saveChosenSeats'(state, chosenSeats) {
    state.chosenSeats = chosenSeats
  }
};

export default {
  namespaced: true,
  state,
  // getters,
  actions,
  mutations
}
