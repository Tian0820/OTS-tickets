import axios from 'axios'

export function signIn(callback, info) {
  axios.post('/manager/sign-in',
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

export function fetchCurrentManager(callback, name) {
  axios.get('/manager/me', {
    params: {
      name: name
    }
  })
    .then(function (response) {
      callback(response.data)
    })
    .catch(function (error) {
      console.log(error)
    })
}

export function fetchApprovals(callback) {
  axios.get('/manager/approvals')
    .then(function (response) {
      callback(response.data)
    })
    .catch(function (error) {
      console.log(error)
    })
}

export function managerApprove(callback, info) {
  axios.post('/manager/approve',
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

export function fetchAllUsers(callback) {
  axios.get('/manager/users')
    .then(function (response) {
      callback(response.data)
    })
    .catch(function (error) {
      console.log(error)
    })
}

export function banUser(callback, userId) {
  axios.get(`/manager/ban-user/${userId}`)
    .then(function (response) {
      callback(response.data)
    })
    .catch(function (error) {
      console.log(error)
    })
}

export function fetchVenueStatistics(callback) {
  axios.get('/manager/venue-statistics')
    .then(function (response) {
      callback(response.data)
    })
    .catch(function (error) {
      console.log(error)
    })
}
