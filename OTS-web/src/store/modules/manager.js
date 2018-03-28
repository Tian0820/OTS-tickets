import * as managerApi from '../../api/manager'

const state = {
    currentManager: null,
    approvals: [],
    allUsers: null,
    userToBan: null,
    venueStatistics: null
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

  fetchApprovals({commit}) {
    managerApi.fetchApprovals(data => {
      if (data !== null && data !== undefined) {
        commit('saveApprovals', data)
      }
    })
  },

  managerApprove({state, commit}, {info, onSuccess, onError}) {
    managerApi.managerApprove(data => {
      if (data.result === true) {
        if (onSuccess) {
          onSuccess('操作成功！')
        }
      } else {
        onError('操作失败！')
      }
    }, info)
  },

  fetchAllUsers({commit}) {
    managerApi.fetchAllUsers(data => {
      commit('saveAllUsers', data)
    })
  },

  banUser({commit}, {userId, onSuccess, onError}) {
    managerApi.banUser(data => {
      if (data.result === true) {
        if (onSuccess) {
          onSuccess('注销成功！')
        }
      } else {
        onError('注销失败！')
      }
    }, userId)
  },

  fetchVenueStatistics({commit}) {
    managerApi.fetchVenueStatistics(data => {
      let statistics = []
      data.forEach(singleData => {
        let finishNum = 0
        let refundNum = 0
        singleData.orders.forEach(order => {
          if (order.state === '已完成') {
            finishNum++
          } else if (order.state === '已退款') {
            refundNum++
          }
        })
        statistics.push({
          name: singleData.name,
          code: singleData.code,
          profit: singleData.profit,
          address: singleData.address,
          orderNum: singleData.orders.length,
          showNum: singleData.showPlans.length,
          finishedOrderNum: finishNum,
          refundOrderNum: refundNum
        })
      })
      commit('saveVenueStatistics', statistics)
    })
  },

  clearProfit({commit}, {onSuccess}) {
    managerApi.clearProfit(data => {
      if (onSuccess) {
        onSuccess('盈利已结算至场馆！')
      }
    })
  }

};

const mutations = {
  'saveCurrentManager'(state, currentManager) {
    state.currentManager = currentManager
  },
  'saveApprovals'(state, approvals) {
    state.approvals = approvals
  },
  'saveAllUsers'(state, allUsers) {
    state.allUsers = allUsers
  },
  'saveUserToBan'(state, userToBan) {
    state.userToBan = userToBan
  },
  'saveVenueStatistics'(state, venueStatistics) {
    state.venueStatistics = venueStatistics
  }

};

export default {
  namespaced: true,
  state,
  // getters,
  actions,
  mutations
}
