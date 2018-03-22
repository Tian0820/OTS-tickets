<template>
  <modal
    name="ban-user-modal"
    :clickToClose="true"
    :height="200">

    <div class="modal-wrapper" v-if="userId">

      <p>注销用户为不可逆操作</p>
      <p>确认注销？</p>

      <button @click="handleBan">确认</button>
      <button @click="handleClose">取消</button>

    </div>

  </modal>


</template>

<script>
  import {Message} from 'element-ui'
  import {router} from '../../main'
  import {mapState, mapActions} from 'vuex'

  export default {
    name: 'ban-user-modal',
    components: {
      Message
    },
    data() {
      return {}
    },
    computed: {
      ...mapState('manager', {
        userId: state => state.userToBan
      })
    },
    methods: {
      ...mapActions('manager', [
        'banUser'
      ]),
      handleBan() {
        console.log(this.userId)
        this.banUser({
          userId: this.userId,
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
        this.$modal.hide('ban-user-modal')
      }
    }
  }

</script>

<style scoped src="./BanUserModal.css"></style>
