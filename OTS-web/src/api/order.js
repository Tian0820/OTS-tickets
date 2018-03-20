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
