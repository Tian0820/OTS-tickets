<template>

  <el-col :xs="24" :sm="24" :md="12" :lg="12">

    <div class="single-user-wrapper">

      <div class="img-wrapper" :style="{ backgroundImage: 'url(' + avatarUrl + ')' }"></div>

      <div class="info-wrapper">
        <p>用户名：{{user.username}}</p><br/>
        <p>邮箱：{{user.email}}</p><br/>
        <p>等级：{{user.level}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;积分：{{user.point}}</p><br/>
        <button @click="handleBan">注销</button>

      </div>

    </div>
  </el-col>


</template>

<script>
  import {Col} from 'element-ui'
  import {router} from '../../main'
  import {mapState, mapActions, mapMutations} from 'vuex'

  export default {
    name: 'single-user',
    components: {
      elCol: Col
    },
    computed: {},
    props: ['user'],
    data() {
      return {
        avatarUrl: 'https://cdn.dribbble.com/users/548267/screenshots/2657798/wagon_v1_dribbble.jpg',
      }
    },
    methods: {
      ...mapMutations('manager', [
        'saveUserToBan'
      ]),
      handleBan() {
        this.saveUserToBan(this.user.id)
        this.$modal.show('ban-user-modal')
      }
    }
  }

</script>

<style scoped src="./SingleUser.css"></style>
