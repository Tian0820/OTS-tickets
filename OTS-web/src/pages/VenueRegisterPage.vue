<template>
  <div class="body-wrapper">
    <layout>
      <div class="container">
        <venue-register></venue-register>
      </div>
    </layout>
  </div>

</template>

<script>
  import Layout from '../components/Layout/Layout.vue'
  import VenueRegister from '../components/Auth/VenueRegister.vue'

  export default {
    name: 'venue-register-page',
    components: {
      Layout,
      VenueRegister
    },
    data() {
      return {}
    },
    methods: {}
  }

</script>

<style scoped src="./VenueRegisterPage.css"></style>
