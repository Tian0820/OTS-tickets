<template>
  <modal
    name="user-account-modal"
    :clickToClose="false"
    :height="350">

    <div class="modal-wrapper">

      <el-button
        icon="el-icon-close"
        @click="closeBox"></el-button>

      <div :style="{padding: '20px'}">
        <h1>会员账号</h1>

        <div :style="{textAlign: 'center', marginTop: '40px'}">
          <el-input v-model="email" placeholder="会员邮箱账号"></el-input>
        </div>

        <button class="confirm-button" @click="goOn">继续</button>

        <button class="confirm-button"
                @click="notMember"
                :style="{marginRight: '10px'}">
          不是会员
        </button>


      </div>
    </div>

  </modal>


</template>

<script>
  import {Button, Message, Input} from 'element-ui'
  import {router} from '../../main'
  import {mapState, mapActions, mapMutations} from 'vuex'

  export default {
    name: 'user-account-modal',
    components: {
      elButton: Button,
      elInput: Input,
      Message
    },
    data() {
      return {
        email: ''
      }
    },
    computed: {
      ...mapState('auth', {
        user: state => state.currentUser
      }),
      ...mapState('venue', {
        siteUser: state => state.siteUser
      })
    },
    methods: {
      ...mapActions('venue', [
        'fetchUserByEmail'
      ]),
      notMember() {
        this.fetchUserByEmail({
          email: 'not member',
        })
        this.$modal.hide('user-account-modal')
        this.$modal.show('choose-seat-modal')
        this.email = ''
      },
      goOn() {
        this.fetchUserByEmail({
          email: this.email,
          onSuccess: () => {
            this.$modal.hide('user-account-modal')
            this.$modal.show('choose-seat-modal')
            this.email = ''
          },
          onError: (error) => {
            Message.error(error)
          }
        })
      },
      closeBox() {
        this.$modal.hide('user-account-modal')
        this.email = ''
      }
    }
  }

</script>

