<template>
  <modal
    name="choose-seat-modal"
    :clickToClose="false"
    :height="500">

    <div class="modal-wrapper">
      <el-button
        icon="el-icon-close"
        @click="closeBox"></el-button>

      <div :style="{padding: '20px'}">

        <h1>选座购票 <span :style="{color: '#595959', fontSize: '12px'}">（最多选择6个）</span></h1>

        <div :style="{textAlign: 'center', marginTop: '30px'}">
          <p>舞台</p>
          <el-checkbox-group
            v-model="chosenSeats"
            :min="1"
            :max="6">
            <el-checkbox v-for="seat in seats" :disabled="seat.available===0" :label="seat" :key="seat.id">&nbsp;
            </el-checkbox>
          </el-checkbox-group>
        </div>

        <button class="confirm-button" @click="handleConfirm">确认选座</button>

      </div>
    </div>

  </modal>


</template>

<script>
  import {RadioGroup, Radio, Checkbox, CheckboxGroup, Message, Button} from 'element-ui'
  import {router} from '../../main'
  import {mapState} from 'vuex'

  export default {
    name: 'choose-seat-modal',
    components: {
      elCheckbox: Checkbox,
      elCheckboxGroup: CheckboxGroup,
      elRadioGroup: RadioGroup,
      elRadio: Radio,
      elButton: Button,
      Message
    },
    data() {
      let seats = []
      for (let i = this.chosenArea * 100; i < this.chosenArea * 100 + 100; i++) {
        seats.push(this.currentShow.seats[i]);
      }
      return {
        chosenSeats: [],
        seats: seats
      }
    },
//    computed: {
//      ...mapState('showPlan', {
//        currentShow: state => state.currentShow,
//        chosenArea: state => state.chosenArea
//      })
//    },
    props: ['currentShow', 'chosenArea'],
    methods: {
      closeBox() {
        this.chosenSeats = []
        this.$modal.hide('choose-seat-modal')
      },
      handleConfirm() {
        Message.success('选择成功！')
        this.$modal.hide('choose-seat-modal')
      }
    }
  }

</script>

<style scoped src="./ChooseSeatModal.css"></style>
