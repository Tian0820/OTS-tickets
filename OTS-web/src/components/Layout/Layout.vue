<template>
  <div>
    <navigator>

    </navigator>

    <div class="body-wrapper">
      <slot>

      </slot>
    </div>



    <my-footer></my-footer>

    <div>

      <exchange-coupon-modal></exchange-coupon-modal>
      <choose-seat-modal></choose-seat-modal>
      <buy-tickets-modal></buy-tickets-modal>
      <venue-code-modal></venue-code-modal>
      <user-account-modal></user-account-modal>
      <ban-user-modal></ban-user-modal>
      <refund-modal></refund-modal>
    </div>
  </div>
</template>

<script>
  import Navigator from './Navigator.vue'
  import Footer from './Footer.vue'

  import ExchangeCouponModal from '../Modal/ExchangeCouponModal.vue'
  import ChooseSeatModal from '../Modal/ChooseSeatModal.vue'
  import BuyTicketsModal from '../Modal/BuyTicketsModal.vue'
  import VenueCodeModal from '../Modal/VenueCodeModal.vue'
  import UserAccountModal from '../Modal/UserAccountModal.vue'
  import BanUserModal from '../Modal/BanUserModal.vue'
  import RefundModal from '../Modal/RefundModal.vue'

  export default {
    name: '',
    components: {
      Navigator,
      myFooter: Footer,
      
      ExchangeCouponModal,
      ChooseSeatModal,
      BuyTicketsModal,
      VenueCodeModal,
      UserAccountModal,
      BanUserModal,
      RefundModal,
    },
    data () {
      return {}
    },
    computed: {}
  }
</script>

<style scoped>
  .body-wrapper {
    display: inline-block;
    margin-top: 60px;
  }
</style>
