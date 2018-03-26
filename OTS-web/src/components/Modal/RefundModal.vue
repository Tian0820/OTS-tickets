<template>
  <modal
    name="refund-modal"
    :clickToClose="true"
    :height="200">

    <div class="modal-wrapper">

      <p>距离演出一个月可全额退款，否则退款减半。演出前一周无法退款。</p>
      <p>确认退款？</p>

      <button @click="handleConfirm">确认</button>
      <button @click="handleClose">取消</button>

    </div>

  </modal>


</template>

<script>
  import {Message} from 'element-ui'
  import {router} from '../../main'
  import {mapState, mapActions} from 'vuex'

  export default {
    name: 'refund-modal',
    components: {
      Message
    },
    data() {
      return {}
    },
    computed: {
      ...mapState('order', {
        currentOrder: state => state.currentOrder
      })
    },
    methods: {
      ...mapActions('order', [
        'refundOrder'
      ]),
      handleConfirm() {
        this.refundOrder({
          orderId: this.currentOrder.id,
          onSuccess: (success) => {
            Message.success(success)
            location.reload();
          },
          onError: (error) => {
            Message.error(error)
          }
        })
      },
      handleClose() {
        this.$modal.hide('refund-modal')
      }
    }
  }

</script>

<style scoped src="./BanUserModal.css"></style>
