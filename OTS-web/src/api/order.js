import axios from 'axios'

export function createOrder(callback, info) {
  console.log('create order', info)
  axios.post('/order/create',
    info,
    {
      headers: {'Content-Type': 'application/json'}
    })
    .then(function (response) {
      callback(response.data)
    })
    .catch(function (error) {
      console.log(error)
    })
}

export function payOrder(callback, order) {
  axios.post('/order/pay',
    order,
    {
      headers: {'Content-Type': 'application/json'}
    })
    .then(function (response) {
      callback(response.data)
    })
    .catch(function (error) {
      console.log(error)
    })
}

export function refundOrder(callback, orderId) {
  axios.get(`/order/refund/${orderId}`)
    .then(function (response) {
      callback(response.data)
    })
    .catch(function (error) {
      console.log(error)
    })
}
