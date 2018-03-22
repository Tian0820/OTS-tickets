<template>

  <div class="user-banning-wrapper">
    <div-header :header="'用户注销'"></div-header>

    <el-row :gutter="10">
      <single-user v-for="item in allUsers" v-if="item.activate === 1" :key="item.id" :user="item"></single-user>
    </el-row>

  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import SingleUser from './SingleUser.vue'
  import {Row} from 'element-ui'
  import {router} from '../../main'
  import {mapState} from 'vuex'

  export default {
    name: 'manager-approval',
    components: {
      DivHeader,
      SingleUser,
      elRow: Row
    },
    computed: {
      ...mapState('manager', {
        manager: state => state.currentManager,
        allUsers: state => state.allUsers
      })
    },
    data() {
      return {}
    },
    methods: {}
  }

</script>

<style scoped src="./UserBanning.css"></style>
