<template>
  <div class="body-wrapper">
    <layout>

      <div class="container">

        <order-pay v-if="currentOrder" :currentOrder="currentOrder" :userCoupons="userCoupons"></order-pay>

      </div>

    </layout>
  </div>

</template>

<script>
  import DivHeader from '../components/Util/DivHeader.vue'
  import Layout from '../components/Layout/Layout.vue'
  import OrderPay from '../components/OrderPay/OrderPay.vue'
  import {Message} from 'element-ui'
  import {store} from '../main'
  import {mapActions, mapState} from 'vuex'

  export default {
    name: 'pay-page',
    components: {
      DivHeader,
      Layout,
      OrderPay,
      Message,
    },
    data() {
      return {}
    },
    computed: {
      ...mapState('order', {
        currentOrder: state => state.currentOrder
      }),
      ...mapState('auth', {
        userCoupons: state => state.userCoupons
      }),
    },
    methods: {
      ...mapActions('auth', [
        'refreshUser'
      ]),
    },
    beforeRouteEnter(to, from, next) {
      store.dispatch('auth/refreshUser', {
        onSuccess: (success) => {
          store.dispatch('order/fetchOrder', to.params.orderId)
          store.dispatch('auth/getUserCoupons')
        },
        onError: (error) => {
//          Message.error('user not login')
          store.dispatch('venue/refreshVenue', {
            onSuccess: (success) => {
              store.dispatch('venue/fetchVenueShowPlans')
            },
            onError: (error) => {
//              Message.error('venue not login')
              store.dispatch('manager/refreshManager', {
                onSuccess: (success) => {
                  store.dispatch('showPlan/fetchAllShowPlans', 1)
                },
                onError: (error) => {
                  store.dispatch('showPlan/fetchAllShowPlans', 1)
//              Message.error('venue not login')
                }
              })
            }
          })

        }
      })
      next(true)
    }
  }
</script>

<style scoped src="./PayPage.css"></style>
