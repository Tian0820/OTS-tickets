<template>
  <div>
    <type-filter></type-filter>
    <type-filter></type-filter>
    <type-filter></type-filter>
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
      return {}
    },
    methods: {}
  }
</script>

<style scoped src="./FilterGroup.css"></style>
