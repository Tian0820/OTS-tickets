<template>
  <div class="show-search">
    <el-input placeholder="请输入演出" v-model="input">

      <el-select v-model="select" slot="prepend" placeholder="地点" class="select">
        <el-option label="全部" value="全部"></el-option>
        <el-option label="上海" value="上海"></el-option>
        <el-option label="南京" value="南京"></el-option>
      </el-select>

      <el-button slot="append" icon="el-icon-search" class="search">搜索</el-button>
    </el-input>
  </div>
</template>

<script>
  import {Input, Select, Button, Option} from 'element-ui'

  export default {
    name: 'show-search',
    components: {
      elInput: Input,
      elSelect: Select,
      elButton: Button,
      elOption: Option
    },
    data() {
      return {
        select: '全部',
        input: '',
      }
    },
    props: [],
    methods: {},
  }
</script>

<style scoped src="./ShowSearch.css"></style>
