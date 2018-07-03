<template>
  <div class="type-filter">
    {{filter}}

    <span class="name">{{filter.name}}：</span>

    <span v-for="option in filter.options" :class="classObject(option)" @click="handleClick(option)">{{option}}</span>


    <span>
      <el-date-picker
        :popper-class="'date-picker'"
        size="mini"
        type="daterange"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        v-model="datePicker"
        value-format="yyyy-MM-dd"
        :picker-options="pickerOptions"
        @change="handleChangeDate"
      >
      </el-date-picker>
    </span>
  </div>

</template>

<script>
  import {DatePicker} from 'element-ui'

  export default {
    name: 'date-filter',
    components: {
      elDatePicker: DatePicker
    },
    props: ['filter'],
    data () {
      return {
        datePicker: '',
        pickerOptions: {
          disabledDate(date) {
            return date.getTime() < Date.now();
          },

        }
      }
    },
    computed: {},
    methods: {
      classObject (option) {
        return {
          'option': true,
          'active': option === this.filter.model[0]
        }
      },
      handleClick (value) {
        this.filter.model = value
        this.datePicker = ''
        this.filter.onChange(value, false)
      },
      handleChangeDate (value) {
        console.log(value)
        this.filter.model = ['datepicker', value]
        this.filter.onChange(['datepicker', value], true)
      }
    }
  }
</script>


<style scoped src="./TypeFilter.css"></style>
