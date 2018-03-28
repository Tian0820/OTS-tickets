<template>
  <div ref="barChart" :style="{width: '100%', height: '300px'}"></div>

</template>

<script>
  import echarts from 'echarts'

  export default {
    name: 'order-num-bar-chart',
    components: {},
    props: ['x', 'y'],
    data() {
      return {}
    },
    watch: {
      // 如果路由有变化，会再次执行该方法
      '$route': 'setEchart',
      y() {
        this.setEchart()
      }
    },
    methods: {
      setEchart() {
        // 基于准备好的dom，初始化echarts实例
        let myChart = echarts.init(this.$refs.barChart)
        // 绘制图表
        myChart.setOption({
          tooltip: {
            backgroundColor: 'rgba(255, 255, 255, 0.8)',
            borderWidth: 1,
            borderColor: '#ccc',
            padding: 2,
            textStyle: {
              color: '#000'
            },
          },
          legend: {
            data: ['数量'],
            show: true,
            bottom: 0
          },
          color: ['#e9a038'],
          xAxis: {
            data: this.x
          },
          yAxis: {
            type: 'value'
          },
          series: [{
            name: '数量',
            type: 'bar',
            barWidth: '40%',
            data: this.y
          }]
        })
      }
    },
    mounted() {
      this.$nextTick(function () {
        this.setEchart()
      })
    }
  }
</script>
