<template>
  <el-dialog
    class="modal-wrapper"
    title="确认退款？"
    :visible="refundModal"
    @close="handleClose"
    width="50%">

    <!--<modal-->
    <!--name="refund-modal"-->
    <!--:clickToClose="true"-->
    <!--:height="200">-->


    <p>距离演出一个月可全额退款，否则退款减半。演出前一周无法退款。</p>
    <p>确认退款？</p>

    <span slot="footer" class="dialog-footer">
      <el-button size="medium" @click="handleClose">取 消</el-button>
      <el-button size="medium" type="primary" @click="handleConfirm">确 定</el-button>
    </span>
    <!--</modal>-->
  </el-dialog>


</template>

<script>
  import {Dialog, Button, Message} from 'element-ui'
  import {router} from '../../main'
  import {mapState, mapActions, mapMutations} from 'vuex'

  export default {
    name: 'refund-modal',
    components: {
      elButton: Button,
      elDialog: Dialog,
      Message
    },
    data() {
      return {}
    },
    computed: {
      ...mapState('order', {
        currentOrder: state => state.currentOrder,
        refundModal: state => state.refundModal
      })
    },
    methods: {
      ...mapMutations('order', [
        'saveRefundModal'
      ]),
      ...mapActions('order', [
        'refundOrder'
      ]),
      handleConfirm() {
        this.refundOrder({
          orderId: this.currentOrder.id,
          onSuccess: (success) => {
            Message.success(success)
//            location.reload();
            this.saveRefundModal(false)
          },
          onError: (error) => {
            Message.error(error)
          }
        })
      },
      handleClose() {
        this.saveRefundModal(false)
//        this.$modal.hide('refund-modal')
      }
    }
  }

</script>

<style scoped src="./RefundModal.css"></style>
