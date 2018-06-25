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
    props: ['filters'],
    data () {
      return {

      }
    },
    methods: {}
  }
</script>

<style scoped src="./FilterGroup.css"></style>
