import axios from 'axios'

export function sendVerifyCode(callback, info) {
  axios.post('/user/email-verify',
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
