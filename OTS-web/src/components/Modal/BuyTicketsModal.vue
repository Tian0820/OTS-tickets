<template>
  <modal
    name="buy-tickets-modal"
    :clickToClose="false"
    :height="300">

    <div class="modal-wrapper">
      <el-button
        icon="el-icon-close"
        @click="closeBox"></el-button>

      <div :style="{padding: '20px'}">

        <h1>立即购买 <span :style="{color: '#595959', fontSize: '12px'}">（最多购买20张）</span></h1>

        <div :style="{textAlign: 'center', marginTop: '30px'}">

          <el-input-number v-model="number" @change="" :min="1" :max="20"
                           label="购买数量"></el-input-number>
        </div>

        <button class="confirm-button" @click="handleConfirm">确认购买</button>

      </div>
    </div>

  </modal>


</template>

<script>
  import {InputNumber, Message, Button} from 'element-ui'
  import {router} from '../../main'
  import {mapState, mapActions} from 'vuex'

  export default {
    name: 'buy-tickets-modal',
    components: {
      elInputNumber: InputNumber,
      elButton: Button,
      Message
    },
    data() {
      return {
        number: 1,
      }
    },
    computed: {
      ...mapState('auth', {
        user: state => state.currentUser
      }),
      ...mapState('showPlan', {
        currentShow: state => state.currentShow,
        chosenArea: state => state.chosenArea
      }),
      price: function () {
        return this.currentShow.seats[(this.chosenArea - 1) * 100].price
      }
    },
    methods: {
      ...mapActions('order', [
        'createOrder'
      ]),
      closeBox() {
        this.chosenSeats = []
        this.$modal.hide('buy-tickets-modal')
      },
      handleConfirm() {
        let seats = []
        let price = []
        for (let i = 0; i < this.number; i++) {
          seats.push(0)
        }
        this.createOrder({
          info: {
            showId: this.currentShow.id,
            userId: this.user.userId,
            type: '分配',
            state: '未付款',
            price: this.price * this.number,
            seats: seats.join(';')
          },
          onSuccess: (success) => {
            Message.success(success)
            this.$modal.hide('buy-tickets-modal')
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

</script>

<style scoped src="./BuyTicketsModal.css"></style>
