<template>
  <div class="banner-wrapper">

    <el-carousel trigger="click" :autoplay="true">
      <el-carousel-item v-for="index in urlIndex" :key="index">

        <!--<div class="banner">-->
        <div class="img-wrapper"
             :style="{ backgroundImage: 'url(' + require('../../assets/img/' + index + 'banner.jpeg') + ')' }"
             @click="goToShowDetails(index)">
          <!--<div class="container">-->
          <!--<h1>OTS</h1>-->
          <!--<h2>你的专属票务系统</h2>-->
          <!--</div>-->
        </div>
        <!--</div>-->
      </el-carousel-item>
    </el-carousel>


  </div>

</template>

<script>
  import {Input, Carousel, CarouselItem} from 'element-ui'
  import {router} from '../../main'

  export default {
    name: 'banner',
    components: {
      elInput: Input,
      elCarousel: Carousel,
      elCarouselItem: CarouselItem
    },
    data() {
      let name = '0banner.jpeg'
      return {
        urlIndex: [0, 10, 11, 21, 22],
        name: '0banner.jpeg',
//        bannerUrl: require('../../assets/img/' + name),
        input: ''
      }
    },
    methods: {
      goToShowDetails(index) {
        console.log(index)
        if (index !== 0) {
          router.push({name: 'ShowDetailPage', params: {showId: index}})
        }
      }
    }
  }
</script>

<style scoped src="./Banner.css"></style>
