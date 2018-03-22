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
