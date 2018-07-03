import * as showApi from '../../api/showPlan'

const state = {
  allShowPlans: null,
  typeShowPlans: null,
  currentShow: null,
  chooseSeatModal: false,
  chosenArea: 1,
  chosenSeats: [],
  page: null,
  size: 8,
  totalPages: null,
  search: {
    keyword: '',
    city: '全部',
    date: ['全部'],
    type: '全部',
    showPlans: null,
    page: null,
    size: 8,
    totalPages: null,
  }
};

const actions = {

  fetchAllShowPlans({commit, state}, page) {
    commit('saveBlankAllShowPlans')
    showApi.fetchAllShowPlans(data => {
      if (data !== null) {
        commit('saveAllShowPlans', data)
      }
    }, 8, page)
  },

  fetchTypeShowPlans({commit, state}, type) {
    // commit('saveBlankTypeShowPlans')
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
  },

  fetchSearchShowPlans({commit, state}, page) {
    commit('saveBlankSearchShowPlans')

    let keyword = state.search.keyword
    let city = state.search.city === '全部' ? '' : state.search.city
    let type = state.search.type === '全部' ? '' : state.search.type
    let startDate = state.search.date[0] === '全部' ? '' : state.search.date[1][0]
    let endDate = state.search.date[0] === '全部' ? '' : state.search.date[1][1]
    showApi.fetchSearchShowPlans(data => {
      if (data !== null) {
        commit('saveSearchShowPlans', data)
      }
    }, keyword, city, type, startDate, endDate, 8, page)
  },

};

const mutations = {
  'saveAllShowPlans'(state, data) {
    state.allShowPlans = data.content
    state.page = data.number + 1
    state.totalPages = data.totalPages
  },
  'saveBlankAllShowPlans'(state ) {
    state.allShowPlans = null
  },
  'saveTypeShowPlans'(state, data) {
    state.typeShowPlans = data.content
  },
  'saveBlankTypeShowPlans'(state ) {
    state.typeShowPlans = null
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
  },
  'saveSearchType'(state, type) {
    state.search.type = type
  },
  'saveSearchDate'(state, date) {
    state.search.date = date
  },
  'saveBlankSearchShowPlans'(state) {
    state.search.showPlans = null
  },
  'saveSearchShowPlans'(state, data) {
    state.search.showPlans = data.content
    state.search.page = data.number + 1
    state.search.totalPages = data.totalPages
  },
  'saveChooseSeatModal'(state, chooseSeatModal) {
    state.chooseSeatModal = chooseSeatModal
  },
};

export default {
  namespaced: true,
  state,
  // getters,
  actions,
  mutations
}
