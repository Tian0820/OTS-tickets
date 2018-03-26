import * as orderApi from '../../api/order'

const state = {
    currentOrder: null
  }
;

const actions = {

  createOrder({commit}, {info, onSuccess, onError}) {
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

  payOrder({commit, state}, {order, onSuccess, onError}) {
    orderApi.payOrder(data => {
      if (data.result === true) {
        if (onSuccess) {
          onSuccess("支付成功！")
        }
      } else {
        onError("支付失败！")
      }
    }, order)
  }

};

const mutations = {
  'saveCurrentOrder'(state, currentOrder) {
    state.currentOrder = currentOrder
  },
  'saveNewOrderPrice'(state, price) {
    state.currentOrder.price = price
  }
};

export default {
  namespaced: true,
  state,
  // getters,
  actions,
  mutations
}
