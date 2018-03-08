<template>

  <div class="user-info-wrapper">

    <div class="avatar-wrapper" :style="{ backgroundImage: 'url(' + avatarUrl + ')' }">
    </div>

    <div class="info-wrapper" v-if="user !== null">
      <p :style="{fontWeight: 400}">欢迎，{{user.username}}！</p>
      <p>邮箱：{{user.email}}</p>
      <p>手机：{{user.phone}}</p>
      <p>我的等级：{{user.level}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        我的积分：{{user.point}}</p>
    </div>

  </div>


</template>

<script>
  import {router} from '../../main'
  import {mapState} from 'vuex'

  export default {
    name: 'user-info-brief',
    components: {},
    computed: {
      ...mapState('auth', {
        user: state => state.currentUser
      })
    },
    data() {
      return {
        avatarUrl: 'https://cdn.dribbble.com/users/548267/screenshots/2657798/wagon_v1_dribbble.jpg',
      }
    },
    methods: {}
  }

</script>

<style scoped src="./UserInfoBrief.css"></style>
