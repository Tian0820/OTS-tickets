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

export function signIn(callback, info) {
  axios.post('/user/sign-in',
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

export function fetchCurrentUser(callback, email) {
  axios.get('/user/me', {
    params: {
      email: email
    }
  })
    .then(function (response) {
      callback(response.data)
    })
    .catch(function (error) {
      console.log(error)
    })
}

export function editUserInfo(callback, userInfo) {
  axios.post('/user/info',
    userInfo,
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

export function editUserPassword(callback, info) {
  axios.post('/user/password',
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


export function exchangeCoupon(callback, info) {
  axios.post('/user/exchange-coupon',
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

export function getUserOrders(callback, userId) {
  axios.get(`/user/${userId}/orders`)
    .then(function (response) {
      callback(response.data)
    })
    .catch(function (error) {
      console.log(error)
    })
}

export function getUserCoupons(callback, userId) {
  axios.get(`/user/${userId}/coupons`)
    .then(function (response) {
      callback(response.data)
    })
    .catch(function (error) {
      console.log(error)
    })
}
