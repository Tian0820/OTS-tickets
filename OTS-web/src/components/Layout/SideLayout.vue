<template>
  <div>
    <el-row :gutter="40">
      <el-col :xs="4" :sm="4" :md="6" :lg="6">
        <slot name="side"></slot>
      </el-col>
      <el-col :xs="20" :sm="20" :md="18" :lg="18">
        <slot name="main"></slot>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import {Row, Col} from 'element-ui'

  export default {
    name: '',
    components: {
      elRow: Row,
      elCol: Col,
    },
    data () {
      return {}
    },
    computed: {}
  }
</script>

<style scoped>
</style>
