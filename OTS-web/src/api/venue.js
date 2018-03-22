import axios from 'axios'

export function venueRegister(callback, info) {
  axios.post('/venue/sign-up',
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
  axios.post('/venue/sign-in',
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

export function fetchCurrentVenue(callback, currentVenue) {
  axios.get('/venue/me', {
    params: {
      venueCode: currentVenue
    }
  })
    .then(function (response) {
      callback(response.data)
    })
    .catch(function (error) {
      console.log(error)
    })
}

export function editVenueInfo(callback, info) {
  axios.post('/venue/info',
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

export function editVenuePassword(callback, info) {
  axios.post('/venue/password',
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

export function uploadShowPlan(callback, info) {
  axios.post('/venue/show-plan',
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

export function fetchVenueShowPlans(callback, code) {
  axios.get(`/venue/${code}/show-plans`)
    .then(function (response) {
      callback(response.data)
    })
    .catch(function (error) {
      console.log(error)
    })
}

export function fetchVenueOrders(callback, code) {
  axios.get(`/venue/${code}/orders`)
    .then(function (response) {
      console.log('orders', response.data)
      callback(response.data)
    })
    .catch(function (error) {
      console.log(error)
    })
}

export function checkTicket(callback, orderId) {
  axios.get('/venue/check-ticket', {
    params: {
      orderId: orderId
    }
  })
    .then(function (response) {
      console.log('orders', response.data)
      callback(response.data)
    })
    .catch(function (error) {
      console.log(error)
    })
}
