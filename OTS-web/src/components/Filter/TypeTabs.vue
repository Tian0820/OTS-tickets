<template>
  <el-tabs :value="value" @tab-click="handleClick" class="type-tabs">

    <el-tab-pane v-for="type in types" :name="type.name">
      <span slot="label"><i :class="['icon', 'iconfont', `icon-${type.icon}`]"></i> {{type.name}}</span>
    </el-tab-pane>

  </el-tabs>
</template>

<script>
  import {Tabs, TabPane} from 'element-ui'

  export default {
    name: 'type-tabs',
    components: {
      elTabs: Tabs,
      elTabPane: TabPane
    },
    data() {
      return {
      }
    },
    props: ['types', 'value', 'tabClick'],
    methods: {
      handleClick(tab, event) {
        this.tabClick(tab.name)
      }
    },
  }
</script>

<style scoped src="./TypeTabs.css"></style>
