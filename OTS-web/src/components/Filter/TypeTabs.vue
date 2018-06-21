<template>
  <el-tabs v-model="tab" @tab-click="handleClick" class="type-tabs">
    <el-tab-pane>
      <span slot="label"><i class="icon iconfont icon-concert"></i> 演唱会</span>

    </el-tab-pane>
    <el-tab-pane>
      <span slot="label"><i class="icon iconfont icon-music"></i> 音乐会</span>

    </el-tab-pane>
    <el-tab-pane>
      <span slot="label"><i class="icon iconfont icon-sport"></i> 体育比赛</span>

    </el-tab-pane>
    <el-tab-pane>
      <span slot="label"><i class="icon iconfont icon-drama"></i> 话剧</span>

    </el-tab-pane>
    <el-tab-pane>
      <span slot="label"><i class="icon iconfont icon-theater"></i> 戏剧</span>

    </el-tab-pane>
    <el-tab-pane>
      <span slot="label"><i class="icon iconfont icon-dance"></i> 舞蹈</span>

    </el-tab-pane>
    <el-tab-pane>
      <span slot="label"><i class="icon iconfont icon-paternity"></i> 亲子剧</span>

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
        tab: ''
      }
    },
    props: [],
    methods: {
      handleClick(tab, event) {
        console.log(tab, event);
      }
    },
  }
</script>

<style scoped src="./TypeTabs.css"></style>
