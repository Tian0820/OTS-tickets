import * as orderApi from '../../api/order'

const state = {}
;

const actions = {

  createOrder({commit}, {info, onSuccess, onError}) {
    orderApi.createOrder(data => {
      if (data.result === true) {
        if (onSuccess) {
          onSuccess('下单成功！')
        }
      } else {
        onError('下单失败！')
      }
    }, info)
  }

};

const mutations = {};

export default {
  namespaced: true,
  state,
  // getters,
  actions,
  mutations
}
