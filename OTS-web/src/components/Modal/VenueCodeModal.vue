<template>
  <modal
    name="venue-code-modal"
    :clickToClose="false"
    :height="350">

    <div class="modal-wrapper">

      <el-button
        icon="el-icon-close"
        @click="closeBox"></el-button>

      <div :style="{padding: '20px'}">
        <h1>场馆编码</h1>

        <div :style="{textAlign: 'center', marginTop: '40px'}">
          <p class="need-to-pay">请保管本场馆编号用于登录操作：
            <br/>
            <span :style="{fontSize: '24px', color: '#E9A038'}">{{code}}</span>
          </p>
        </div>


        <button class="confirm-button" @click="handleConfirm">确认</button>

      </div>
    </div>

  </modal>


</template>

<script>
  import {Button, Message} from 'element-ui'
  import {router} from '../../main'
  import {mapState, mapActions} from 'vuex'

  export default {
    name: 'venue-code-modal',
    components: {
      elButton: Button,
      Message
    },
    data() {
      return {}
    },
    computed: {
      ...mapState('venue', {
        code: state => state.venueCode
      })
    },
    methods: {
      handleConfirm() {
        this.$modal.hide('venue-code-modal')
        router.push({name: 'LoginPage'})
      },
      closeBox() {
        this.$modal.hide('venue-code-modal')
        router.push({name: 'LoginPage'})
      }
    }
  }

</script>
