<template>
  <div class="show-search">
    <el-input placeholder="请输入演出" :value="inputValue" @change="handleValueChange">

      <el-select v-if="isSelect" :value="selectValue" slot="prepend" placeholder="地点" class="select"
                 @change="handleSelectChange">
        <el-option v-for="city in cities" :label="city" :value="city"></el-option>
      </el-select>

      <el-button slot="append" icon="el-icon-search" class="search" @click="hendleSearchClick">搜索</el-button>
    </el-input>
  </div>
</template>

<script>
  import {Input, Select, Button, Option} from 'element-ui'
  import {CITY} from '../../constant'

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
//        select: '全部',
        cities: CITY
      }
    },
    props: ['isSelect', 'inputValue', 'onInputChange', 'selectValue', 'onSelectChange', 'onSearchClick'],
    methods: {
      handleValueChange (value) {
        this.onInputChange(value)
      },
      handleSelectChange (value) {
        this.onSelectChange(value)
      },
      hendleSearchClick () {
          this.onSearchClick()
      }
    },
  }
</script>

<style scoped src="./ShowSearch.css"></style>
