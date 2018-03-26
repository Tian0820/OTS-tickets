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

      <p :style="{border: 'none', fontSize: '12px', color: '#595959'}">
        已选：{{seatNumbers ? seatNumbers.join(';') : ''}}</p>

      <button class="confirm-button" @click="handleConfirm">确认选座</button>

    </div>
  </div>

</template>

<script>
  import {RadioGroup, Radio, Checkbox, CheckboxGroup, Message, Button} from 'element-ui'
  import {router} from '../../main'
  import {mapState, mapMutations, mapActions} from 'vuex'

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
    computed: {
      ...mapState('auth', {
        user: state => state.currentUser
      }),
      ...mapState('venue', {
        siteUser: state => state.siteUser
      }),

      seatNumbers: function () {
        let temp = []
        this.chosenSeats.forEach(seat => {
          let row = Math.floor((seat.number + 1) % 10) === 0 ? Math.floor((seat.number + 1) / 10) : Math.floor((seat.number + 1) / 10) + 1
          let col = Math.floor((seat.number + 1) % 10) === 0 ? 10 : Math.floor((seat.number + 1) % 10)
          temp.push(row + '排' + col + '座')
        })
        return temp
      }

    },
    props: ['currentShow', 'chosenArea'],
    methods: {
      ...mapActions('order', [
        'createOrder'
      ]),
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
        let seatIds = []
        this.chosenSeats.forEach(seat => {
          seatIds.push(seat.id)
        })

        if (this.user !== null) {
          // 用户买票
          this.createOrder({
            info: {
              showId: this.currentShow.id,
              userId: this.user.userId,
              type: '选座',
              state: '未付款',
              price: this.chosenSeats[0].price * this.chosenSeats.length,
              seats: seatIds.join(';')
            },
            onSuccess: (success) => {
              Message.success(success)
              this.$modal.hide('choose-seat-modal')
              this.$modal.show('pay-modal')
            },
            onError:
              (error) => {
                Message.error(error)
              }
          })
        } else {
          // 场馆现场卖票
          console.log('venue!!!')
          this.createOrder({
            info: {
              showId: this.currentShow.id,
              userId: this.siteUser === null ? null : this.siteUser.id,
              type: '分配',
              state: '未付款',
              price: this.chosenSeats[0].price * this.chosenSeats.length,
              seats: seatIds.join(';')
            },
            onSuccess: (success) => {
              Message.success(success)
              this.$modal.hide('choose-seat-modal')
              this.$modal.show('pay-modal')
            },
            onError:
              (error) => {
                Message.error(error)
              }
          })
        }

      }
    }
  }

</script>

<style scoped src="./ChooseSeatModal.css"></style>
