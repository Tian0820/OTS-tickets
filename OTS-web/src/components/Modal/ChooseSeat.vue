<template>

    <div class="modal-wrapper" v-if="currentShow">
      <el-button
        icon="el-icon-close"
        @click="closeBox"></el-button>

      <div :style="{padding: '20px'}">

        <h1>选座购票 <span :style="{color: '#595959', fontSize: '12px'}">（最多选择6个）</span></h1>

        <div :style="{textAlign: 'center', marginTop: '30px'}">
          <p>舞台</p>
          <div :style="{padding: '20px 100px'}">
            <el-checkbox-group
              v-model="chosenSeats"
              :min="1"
              :max="6">
              <el-checkbox v-for="seat in seats" :disabled="seat.available===0" :label="seat" :key="seat.id">&nbsp;
              </el-checkbox>
            </el-checkbox-group>
          </div>
        </div>

        <button class="confirm-button" @click="handleConfirm">确认选座</button>

      </div>
    </div>

</template>

<script>
  import {RadioGroup, Radio, Checkbox, CheckboxGroup, Message, Button} from 'element-ui'
  import {router} from '../../main'
  import {mapState, mapMutations} from 'vuex'

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
      for (let i = (this.chosenArea - 1) * 100; i < (this.chosenArea - 1) * 100 + 100; i++) {
        seats.push(this.currentShow.seats[i]);
      }
      console.log('modal', seats)
      console.log('area', this.chosenArea)
      return {
        chosenSeats: [],
        seats: seats
      }
    },
    props: ['currentShow', 'chosenArea'],
    methods: {
      ...mapMutations('showPlan', [
        'saveChosenSeats'
      ]),
      closeBox() {
        this.chosenSeats = []
        this.$modal.hide('choose-seat-modal')
      },
      handleConfirm() {
        this.saveChosenSeats(this.chosenSeats)
        console.log(this.chosenSeats)
        Message.success('选择成功！')


        this.$modal.hide('choose-seat-modal')
        this.$modal.show('pay-modal')


      }
    }
  }

</script>

<style scoped src="./ChooseSeatModal.css"></style>
