import * as orderApi from '../../api/order'

const state = {
    currentOrder: null,
    allOrders: null
  }
;

const actions = {

  createOrder({commit}, {info, onSuccess, onError}) {
    console.log('info', info)
    orderApi.createOrder(data => {
      if (data !== null && data !== undefined) {
        commit('saveCurrentOrder', data)
        if (onSuccess) {
          onSuccess('下单成功！')
        }
      } else {
        onError('下单失败！')
      }
    }, info)
  },

  fetchOrder({commit}, id) {
    orderApi.fetchOrder(data => {
      if (data !== null && data !== undefined) {
        commit('saveCurrentOrder', data)
      }
    }, id)
  },

  payOrder({commit, state}, {order, onSuccess, onError}) {
    console.log('pay-order', order)
    orderApi.payOrder(data => {
      if (data.result === true) {
        if (onSuccess) {
          onSuccess("支付成功！")
        }
      } else {
        onError("支付失败！")
      }
    }, order)
  },

  refundOrder({commit}, {orderId, onSuccess, onError}) {
    orderApi.refundOrder(data => {
      if (data.result === true) {
        if (onSuccess) {
          onSuccess("退款成功！")
        }
      } else {
        onError(data.message)
      }
    }, orderId)
  },

  fetchAllOrders({commit}) {
    orderApi.fetchAllOrders(data => {
      commit('saveAllOrders', data)
    })
  }

};

const mutations = {
  'saveCurrentOrder'(state, currentOrder) {
    state.currentOrder = currentOrder
  },
  'saveNewOrderPrice'(state, price) {
    state.currentOrder.price = price
  },
  'saveAllOrders'(state, allOrders) {
    state.allOrders = allOrders
  }
};

export default {
  namespaced: true,
  state,
  // getters,
  actions,
  mutations
}
