<template>
  <div>
    <navigator>

    </navigator>

    <div class="body-wrapper">
      <slot>

      </slot>
    </div>

    <my-footer></my-footer>

  </div>
</template>

<script>
  import Navigator from './Navigator.vue'
  import Footer from './Footer.vue'

  export default {
    name: '',
    components: {
      Navigator,
      myFooter: Footer
    },
    data () {
      return {}
    },
    computed: {}
  }
</script>

<style scoped>
  .body-wrapper {
    display: inline-block;
    margin-top: 60px;
  }
</style>
