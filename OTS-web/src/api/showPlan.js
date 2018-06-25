import axios from 'axios'

export function fetchAllShowPlans(callback, size, page) {
  axios.get(`/show-plan?size=${size}&page=${page-1}`)
    .then(function (response) {
      callback(response.data)
    })
    .catch(function (error) {
      console.log(error)
    })
}

export function fetchTypeShowPlans(callback, type, size, page) {
  axios.get(`/search?type=${type}&size=${size}&page=${page-1}`)
    .then(function (response) {
      callback(response.data)
    })
    .catch(function (error) {
      console.log(error)
    })
}

export function fetchShowPlanById(callback, id) {
  axios.get(`/show-plan/${id}`)
    .then(function (response) {
      callback(response.data)
    })
    .catch(function (error) {
      console.log(error)
    })
}


export function fetchSearchShowPlans(callback, keyword, city, type, size, page) {
  let url = `/search?keyword=${keyword}&city=${city}&type=${type}&size=${size}&page=${page-1}`;
  console.log(url)
  axios.get(url)
    .then(function (response) {
      console.log('search show plans', response.data)
      callback(response.data)
    })
    .catch(function (error) {
      console.log(error)
    })
}
