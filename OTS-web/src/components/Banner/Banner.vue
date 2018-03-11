<template>
  <div class="banner-wrapper">
    <div class="img-wrapper" :style="{ backgroundImage: 'url(' + bannerUrl + ')' }">

      <h1>OTS</h1>
      <h2>你的专属票务系统</h2>

    </div>
  </div>

</template>

<script>
  import { Input } from 'element-ui'

  export default {
    name: 'banner',
    components: {
      elInput: Input
    },
    data () {
      let name = 'banner.jpeg'
      return {
        bannerUrl: require('../../assets/img/' + name),
        input: ''
      }
    },
    methods: {}
  }
</script>

<style scoped src="./Banner.css"></style>
