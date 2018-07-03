<template>

  <!--<div class="modal-wrapper" v-if="currentShow">-->
  <!--<el-buttonxxcloseBox"></el-button>-->

  <div>

    <!--<h1>选座购票 <span :style="{color: '#595959', fontSize: '12px'}">（最多选择6个）</span></h1>-->
    <div class="inner-wrapper">

    <div :style="{textAlign: 'center'}">
      <p class="stage-p">舞台</p>
      <div :style="{padding: '20px 100px'}">
        <el-checkbox-group
          v-model="chosenSeats"
          :min="1"
          :max="6">
          <p class="area-1-p">一区 780元</p>
          <el-checkbox class="area-1-box" v-for="(seat, index) in seats" v-if="index < 30"
                       :disabled="seat.available===0"
                       :label="seat"
                       :key="seat.id">&nbsp;
          </el-checkbox>
          <p class="area-2-p">二区 580元</p>
          <el-checkbox class="area-2-box" v-for="(seat, index) in seats" v-if="index >= 30 && index < 70"
                       :disabled="seat.available===0" :label="seat"
                       :key="seat.id">&nbsp;
          </el-checkbox>
          <p class="area-3-p">三区 380元</p>
          <el-checkbox class="area-3-box" v-for="(seat, index) in seats" v-if="index >= 70"
                       :disabled="seat.available===0"
                       :label="seat"
                       :key="seat.id">&nbsp;
          </el-checkbox>
        </el-checkbox-group>
        <!--<el-buttonxxcloseBox"></el-button>-->



          <!--<h1>选座购票 <span :style="{color: '#595959', fontSize: '12px'}">（最多选择6个）</span></h1>-->

        </div>

        <div class="chosen">
          <span  >已选：</span>
          <div v-for="seat in chosenSeats" class="chosen-seat" :class="chosenSeat(seat)">
            <button class="chosen-seat-button" :class="chosenSeat(seat)" type="primary"></button>
            {{calSeatNumber(seat)}}
          </div>
        </div>
        <p class="limit">最多都买6张</p>

        <p  class="price" >
          共 <span :style="{border: 'none', fontSize: '20px', color: '#E9A038', fontWeight: '400'}">{{totalPrice}}</span>
          元
        </p>

        <div class="confirm-button-wrapper">
          <el-button size="medium" type="primary"
                     class="confirm-button" @click="handleConfirm"
                     :disabled="chosenSeats.length === 0"
          >确认选座
          </el-button>
        </div>
      </div>
    </div>
  </div>
  <!--</div>-->

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
//      let seats = []
//      for (let i = (this.chosenArea - 1) * 100; i < (this.chosenArea - 1) * 100 + 100; i++) {
//        seats.push(this.currentShow.seats[i]);
//      }
      console.log(this.currentShow.seats)
      return {
        chosenSeats: [],
        seats: this.currentShow.seats
      }
    },
    computed: {
      ...mapState('auth', {
        user: state => state.currentUser
      }),
      ...mapState('venue', {
        siteUser: state => state.siteUser
      }),
      ...mapState('order', {
        currentOrder: state => state.currentOrder
      }),

      totalPrice: function () {
        let total = 0
        this.chosenSeats.forEach(seat => {
          total += seat.price
        })
        return total
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
      calSeatNumber(seat) {
        let row = Math.floor((seat.number + 1) % 10) === 0 ? Math.floor((seat.number + 1) / 10) : Math.floor((seat.number + 1) / 10) + 1
        let col = Math.floor((seat.number + 1) % 10) === 0 ? 10 : Math.floor((seat.number + 1) % 10)
        return row + '排' + col + '座'
      },
      chosenSeat(seat) {
        switch (seat.area) {
          case '1':
            return 'seat-1'
            break;
          case '2':
            return 'seat-2'
            break;
          case '3':
            return 'seat-3'
            break;
        }
      },
      handleConfirm() {
        this.saveChosenSeats(this.chosenSeats)
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
//              Message.success(success)
              this.$modal.hide('choose-seat-modal')
              router.push({name: 'PayPage', params: {orderId: this.currentOrder.id}})
            },
            onError:
              (error) => {
                Message.error(error)
              }
          })
        } else {
          // 场馆现场卖票
          this.createOrder({
            info: {
              showId: this.currentShow.id,
              userId: this.siteUser === null ? null : this.siteUser.id,
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
        }

      }
    }
  }

</script>

<style scoped src="./ChooseSeatModal.css"></style>
