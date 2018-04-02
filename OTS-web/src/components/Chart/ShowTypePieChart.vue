<template>
  <div ref="pieChart" :style="{width: '100%', height: '300px'}"></div>

</template>

<script>
  import echarts from 'echarts'

  export default {
    name: 'show-type-pie-chart',
    components: {},
    props: ['x', 'y'],
    data() {
      let chartData = []
      this.y.forEach((data, index) => {
        chartData.push({
          value: this.y[index],
          name: this.x[index]
        })
      })
      return {
        chartData: chartData
      }
    },
    watch: {
      // 如果路由有变化，会再次执行该方法
      '$route': 'setEchart',
      y() {
        this.setEchart()
      },
    },
    methods: {
      setEchart() {
        console.log(this.chartData)
        // 基于准备好的dom，初始化echarts实例
        let myChart = echarts.init(this.$refs.pieChart)
        // 绘制图表
        myChart.setOption({
          tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
          },
          legend: {
            orient: 'vertical',
            x: 'left',
            data: this.x
          },
          series: [
            {
              name: '演出数量',
              type: 'pie',
              radius: ['50%', '70%'],
              avoidLabelOverlap: false,
              label: {
                normal: {
                  show: false,
                  position: 'center'
                },
                emphasis: {
                  show: true,
                  textStyle: {
                    fontSize: '30',
                    fontWeight: 'bold'
                  }
                }
              },
              labelLine: {
                normal: {
                  show: false
                }
              },
              data: this.chartData
            }
          ],
          color: ['#E3645A', '#F48984', '#FDB8A1', '#F7CC9B', '#F8D76E', '#FEE9A5', '#F0E0BC', '#D1CCC6', '#B6D7B3', '#BEE1DA',
            '#A7DAD8', '#92BCC3', '#93A9BD', '#B9CDDC', '#BABBDE', '#928BA9', '#CA9ECE', '#EFCEED', '#FECEDC', '#FAA5B3'],
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
