<template>
  <modal
    name="choose-seat-modal"
    :clickToClose="false"
    height=auto>

    <choose-seat v-if="currentShow" :currentShow="currentShow" :chosenArea="chosenArea"></choose-seat>


  </modal>


</template>

<script>
  import ChooseSeat from './ChooseSeat.vue'
  import {Message} from 'element-ui'
  import {router} from '../../main'
  import {mapState, mapMutations} from 'vuex'

  export default {
    name: 'choose-seat-modal',
    components: {
      Message,
      ChooseSeat
    },
    computed: {
      ...mapState('showPlan', {
        currentShow: state => state.currentShow,
        chosenArea: state => state.chosenArea
      })
    },
    data() {
      return {}
    },
    methods: {}
  }

</script>

<style scoped src="./ChooseSeatModal.css"></style>
