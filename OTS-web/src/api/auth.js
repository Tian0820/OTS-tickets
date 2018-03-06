import axios from 'axios'

export function sendVerifyCode(callback, info) {
  axios.post('/user/send-email',
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

export function fetchVerifyResult(callback, info) {
  axios.get('/user/email-verify', {
    params: {
      email: info.email,
      code: info.code
    }
  })
    .then(function (response) {
      callback(response.data)
    })
    .catch(function (error) {
      console.log(error)
    })
}

export function userRegister(callback, info) {
  axios.post('/user/sign-up',
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
