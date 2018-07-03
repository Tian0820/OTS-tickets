<template>
  <div>
    <type-filter v-for="filter in filters" :filter="filter"></type-filter>
    <date-filter :filter="dateFilter"></date-filter>
  </div>
</template>

<script>
  import TypeFilter from './TypeFilter'
  import DateFilter from './DateFilter'

  export default {
    name: 'filter-group',
    components: {
      TypeFilter,
      DateFilter
    },
    props: ['filters', 'dateFilter'],
    data () {
      return {

      }
    },
    methods: {}
  }
</script>

<style scoped src="./FilterGroup.css"></style>
