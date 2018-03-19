import * as showApi from '../../api/showPlan'

const state = {
    allShowPlans: null,
    currentShow: null,
    chosenArea: 1,
  }
;

const actions = {

  fetchAllShowPlans({commit}) {
    showApi.fetchAllShowPlans(data => {
      if (data !== null) {
        commit('saveAllShowPlans', data)
      }
    })
  },

  fetchShowPlanById({commit}, id) {
    showApi.fetchShowPlanById(data => {
      if (data !== null) {
        commit('saveCurrentShow', data)
      }
    }, id)
  }
};

const mutations = {
  'saveAllShowPlans'(state, allShowPlans) {
    state.allShowPlans = allShowPlans;
  },
  'saveCurrentShow'(state, currentShow) {
    state.currentShow = currentShow
  },
  'saveChosenArea'(state, chosenArea) {
    state.chosenArea = chosenArea
  }
};

export default {
  namespaced: true,
  state,
  // getters,
  actions,
  mutations
}
