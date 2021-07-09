<template>
  <div class="p-d-flex p-flex-column">
    <div class="p-mb-3"><strong>Shopping Cart</strong></div>
    <div class="p-d-flex p-flex-column wrapper">
      <template v-if="allProducts?.length">
        <div class="p-d-flex p-flex-column list-of-products">
          <div
            class="product-item p-mb-2"
            v-for="(product, index) in allProducts"
            :key="product.id"
          >
            <div class="name">{{ product.name }}</div>
            <div>{{ product.quantity }}</div>
            <div class="p-text-right">
              {{ productPrice(product) }}
            </div>
            <div>
              <RemoveFromCart :productIndex="index" />
            </div>
          </div>
        </div>
        <hr class="devider" />
        <div class="p-d-flex p-jc-center total">
          <div class="p-mr-3"><strong>Total:</strong></div>
          <div>{{ total }}</div>
        </div>

        <div class="">
          <Button label="Checkout" class="btn-success" @click="checkout()" />
        </div>
      </template>
      <template v-else>
        <div>Please buy somethings</div>
      </template>
    </div>
  </div>
</template>

<script>
import RemoveFromCart from "./RemoveFromCart.vue";

export default {
  name: "ShoppingCart",
  components: {
    RemoveFromCart,
  },
  created() {},
  data() {
    return {
      allProducts: this.$store.getters["cart/getAllProducts"],
    };
  },
  props: {},
  methods: {
    productPrice(product) {
      const number = product.quantity
      return number * product.price;
    },
    async checkout() {
      let order = {username: this.$store.getters["authentication/getUserName"], orderItems: this.allProducts};
      const opts = {
        method: 'POST',
         headers: {
      'Content-Type': 'application/json'
    },
        body: JSON.stringify(order)
      };
      const url = new URL(process.env.VUE_APP_ORDER_API, process.env.VUE_APP_BACKEND_URI);
      try {
        const data = await fetch(url, opts);
        if (data.status == 200) {
          alert('Your order is submitted, it will be deliver in next minutes!!!');
        } else {
          alert('Your order can not process at this time, please try again!!!');
        }
      } catch (e) {
        alert('Error: Technical issue, please try again!')
      }
    },
  },
  computed: {
    total() {
      const total = this.allProducts.reduce((currentValue, product) => {
        const number =
          product.quantity && product.quantity > 0 ? product.quantity : 1;
        return currentValue + number * product.price;
      }, 0);
      return total;
    },
  },
};
</script>

<style scoped>
.wrapper {
  padding: 1em;
  border-radius: 8px;
  /* background: rgb(241,230,246); */
  border: 1px solid rgb(65, 184, 131);
  padding-right: 20px;
}

.list-of-products {
  max-height: 300px;
  overflow: auto;
  margin-right: -10px;
  padding-right: 10px;
}

.product-item {
  display: grid;
  grid-template-columns: 1fr 30px 80px 20px;
  column-gap: 10px;
}

.product-item > * {
  text-align: right;
}

.product-item > .name {
  text-align: left;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.devider {
  height: 1px;
  width: 100%;
  background: rgb(65, 184, 131);
}
</style>