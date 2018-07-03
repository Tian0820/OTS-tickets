<template>

  <div class="my-order-wrapper">
    <div class="my-order-header">
      <div-header :header="'我的订单'"></div-header>

      <div class="order-filter">
        <span v-for="type in types"
              :class="['tag', `tag-${type.color}`, {'tag-active': type.name === orderFilter}]"
              @click="handleTagClick(type.name)"
        >{{type.name}} {{type.num}}</span>
      </div>
    </div>

    <div v-if="orders.length === 0" class="blank">
      抱歉，未找到符合条件的订单！
    </div>

    <single-order v-for="item in orders.slice((page-1)*pageSize, page*pageSize)" :order="item" :key="item.id"></single-order>
    <div v-if="orders.length > 0" class="pagination">
      <el-pagination
        background
        layout="prev, pager, next"
        :total="orders.length"
        :page-size="pageSize"
        :current-page="page"
        @current-change="handlePageChange"
      ></el-pagination>
    </div>

  </div>


</template>

<script>
  import DivHeader from '../Util/DivHeader.vue'
  import SingleOrder from '../Order/SingleOrder.vue'
  import {Tag, Pagination} from 'element-ui'
  import {router} from '../../main'
  import {mapState, mapMutations} from 'vuex'
  import {ORDER_TYPE} from '../../constant'

  export default {
    name: 'my-user-order',
    components: {
      elTag: Tag,
      elPagination: Pagination,
      DivHeader,
      SingleOrder
    },
    props: ['userOrders'],
    computed: {
      ...mapState('auth', {
        orderFilter: state => state.orderFilter
      }),
      orders: function () {
        if (this.orderFilter === '全部') {
          return this.userOrders
        } else {
          return this.userOrders.filter(order => order.state === this.orderFilter)
        }
      },
    },
    data() {
      let types = ORDER_TYPE

      for (let key in types) {
        if (types.hasOwnProperty(key)) {
          types[key].num = 0;
        }
      }

      this.userOrders.forEach(order => {
        types[order.state].num++
      })

      types['全部'].num = this.userOrders.length

      return {
        types,
        pageSize: 4,
        page: 1
      }
    },
    methods: {
      ...mapMutations('auth', [
        'saveOrderFilter'
      ]),
      handleTagClick(name) {
        this.saveOrderFilter(name)
        this.page = 1
      },
      handlePageChange(page) {
          this.page = page
      }

    }
  }

</script>

<style scoped src="./MyOrder.css"></style>
