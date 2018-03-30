<template>
  <div ref="lineChart" :style="{width: '100%', height: '300px'}"></div>

</template>

<script>
  import echarts from 'echarts'

  export default {
    name: 'order-weekly-line-chart',
    components: {},
    props: ['x', 'y', 'price'],
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
        let myChart = echarts.init(this.$refs.lineChart)
        // 绘制图表
        myChart.setOption({
          tooltip: {
            trigger: 'axis',
            backgroundColor: 'rgba(255, 255, 255, 0.8)',
            borderWidth: 1,
            borderColor: '#ccc',
            padding: 2,
            textStyle: {
              color: '#000'
            },
          },
          legend: {
            data: ['数量', '价格'],
            show: true,
            bottom: 0
          },
          color: ['#e9a038', '#4e7ede'],
          xAxis: {
            type: 'category',
            boundaryGap: false,
            data: this.x
          },
          yAxis: [{
            name: '数量',
            type: 'value',
            position: 'left'
          }, {
            name: '价格',
            type: 'value',
            position: 'right'
          }],
          series: [{
            yAxisIndex: 0,
            name: '数量',
            type: 'line',
            data: this.y
          },
            {
              yAxisIndex: 1,
              name: '价格',
              type: 'line',
              data: this.price
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
