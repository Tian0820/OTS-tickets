<template>
  <div class="menu">
  <el-menu
    :default-active="defaultIndex"
    @select="handleSelect">

    <el-menu-item v-for="item in items" :index="item.index">
      <i :class="['icon', 'iconfont', `icon-${item.icon}`]"></i>
      <span slot="title">{{item.name}}</span>
    </el-menu-item>

  </el-menu>
  </div>
</template>

<script>
  import {Menu, MenuItem, Submenu} from 'element-ui'



  export default {
    name: 'my-footer',
    components: {
      elMenu: Menu,
      elMenuItem: MenuItem,
      elSubmenu: Submenu
    },
    props: ['defaultIndex', 'items', 'onSelect'],
    data() {
      return {
      }
    },
    methods: {
      handleSelect(key, keyPath) {

        this.onSelect(key)
      }
    }
  }
</script>

<style scoped src="./Menu.css"></style>
