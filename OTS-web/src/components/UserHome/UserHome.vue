<template>

  <div class="user-home-wrapper">
    <user-info-brief></user-info-brief>
    <my-order></my-order>
  </div>


</template>

<script>
  import {router} from '../../main'
  import UserInfoBrief from '../Admin/UserInfoBrief.vue'
  import MyOrder from '../UserHome/MyOrder.vue'

  export default {
    name: 'user-home',
    components: {
      UserInfoBrief,
      MyOrder
    },
    data() {
      return {}
    },
    methods: {}
  }

</script>

<style scoped src="./UserHome.css"></style>
