<template>
  <div>
    <type-filter v-for="filter in filters" :filter="filter"></type-filter>

  </div>
</template>

<script>
  import TypeFilter from './TypeFilter'

  export default {
    name: 'filter-group',
    components: {
      TypeFilter
    },
    data () {
      return {
        filters: [
          {
            name: '地点',
            options: ['全部', '上海', '南京'],
            model: '全部'
          },
          {
            name: '类型',
            options: ['全部', '演唱会', '音乐会', '体育比赛', '话剧', '戏剧', '舞蹈', '亲子剧'],
            model: '全部'
          }
        ]
      }
    },
    methods: {}
  }
</script>

<style scoped src="./FilterGroup.css"></style>
