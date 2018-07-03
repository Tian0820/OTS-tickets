<template>
  <div class="type-filter">
    <span class="name">{{filter.name}}：</span>

    <span v-for="option in filter.options" :class="classObject(option)" @click="handleClick(option)">{{option}}</span>

  </div>
</template>

<script>


  export default {
    name: 'type-filter',
    components: {
    },
    props: ['filter'],
    data () {
      return {}
    },
    computed: {},
    methods: {
      classObject: function (option) {
        return {
          'option': true,
          'active': option === this.filter.model
        }
      },
      handleClick: function(value) {
        this.filter.model = value
        this.filter.onChange(value)
      }
    }
  }
</script>


<style scoped src="./TypeFilter.css"></style>
