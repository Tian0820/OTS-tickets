<template>
  <div class="banner-wrapper">

    <el-carousel trigger="click" :autoplay="false">
      <el-carousel-item v-for="item in 4" :key="item">

        <!--<div class="banner">-->
          <div class="img-wrapper" :style="{ backgroundImage: 'url(' + bannerUrl + ')' }">
            <div class="container">
              <h1>OTS</h1>
              <h2>你的专属票务系统</h2>
            </div>
          </div>
        <!--</div>-->
      </el-carousel-item>
    </el-carousel>


  </div>

</template>

<script>
  import {Input, Carousel, CarouselItem} from 'element-ui'

  export default {
    name: 'banner',
    components: {
      elInput: Input,
      elCarousel: Carousel,
      elCarouselItem: CarouselItem
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
