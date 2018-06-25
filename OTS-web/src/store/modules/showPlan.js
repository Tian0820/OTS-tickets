import * as showApi from '../../api/showPlan'

const state = {
  allShowPlans: null,
  typeShowPlans: null,
  currentShow: null,
  chosenArea: 1,
  chosenSeats: [],
  page: null,
  size: 8,
  totalPages: null,
  search: {
    keyword: '',
    city: '全部',
    // starttime
    type: null,
    showPlans: null,
    page: null,
    size: 8,
    totalPages: null,
  }
};

const actions = {

  fetchAllShowPlans({commit, state}, page) {
    showApi.fetchAllShowPlans(data => {
      if (data !== null) {
        commit('saveAllShowPlans', data)
      }
    }, 8, page)
  },

  fetchTypeShowPlans({commit, state}, type) {
    showApi.fetchTypeShowPlans(data => {
      if (data !== null) {
        commit('saveTypeShowPlans', data)
      }
    }, type, 4, 1)
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
  'saveTypeShowPlans'(state, data) {
    state.typeShowPlans = data.content
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
  },
  'saveSearchKeyword'(state, keyword) {
    state.search.keyword = keyword
  },
  'saveSearchCity'(state, city) {
    state.search.city = city
  }
};

export default {
  namespaced: true,
  state,
  // getters,
  actions,
  mutations
}
