import axios from 'axios'

export function fetchAllShowPlans(callback, size, page) {
  axios.get(`/show-plan?size=${size}&page=${page-1}`)
    .then(function (response) {
      console.log('show plans', response.data)
      callback(response.data)
    })
    .catch(function (error) {
      console.log(error)
    })
}

export function fetchShowPlanById(callback, id) {
  axios.get(`/show-plan/${id}`)
    .then(function (response) {
      console.log('current show plan', response.data)
      callback(response.data)
    })
    .catch(function (error) {
      console.log(error)
    })
}
