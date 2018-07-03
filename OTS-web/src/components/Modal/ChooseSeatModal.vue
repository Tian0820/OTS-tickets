<template>
  <!--<modal-->
  <!--name="choose-seat-modal"-->
  <!--:clickToClose="false"-->
  <!--height=auto>-->

  <el-dialog
    class="modal-wrapper"
    title="选座购票"
    :visible="chooseSeatModal"
    @close="handleClose"
    width="600px"
    margin-top="10vh">


    <choose-seat v-if="currentShow" :currentShow="currentShow" :chosenArea="chosenArea"></choose-seat>


  </el-dialog>


</template>

<script>
  import ChooseSeat from './ChooseSeat.vue'
  import {Dialog, Message} from 'element-ui'
  import {router} from '../../main'
  import {mapState, mapMutations} from 'vuex'

  export default {
    name: 'choose-seat-modal',
    components: {
      elDialog: Dialog,
      Message,
      ChooseSeat
    },

    computed: {
      ...mapState('showPlan', {
        currentShow: state => state.currentShow,
        chosenArea: state => state.chosenArea,
        chooseSeatModal: state => state.chooseSeatModal,
      })
    },
    data() {
      return {}
    },
    methods: {
      ...mapMutations('showPlan', [
        'saveChooseSeatModal'
      ]),
      handleClose() {
        this.saveChooseSeatModal(false)
      },
    }
  }

</script>

<style scoped src="./ChooseSeatModal.css"></style>
