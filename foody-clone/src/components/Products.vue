<template>
  <div class="p-d-flex p-jc-center">
    <div v-if="merchant" class="p-grid pricing-table-price">
      <img id="image" :src="merchant.image" alt="Image" class="p-m-auto" />
      <span id="merchant-name" class="p-m-4">{{ merchant.name }}</span>
      <div v-for="product in merchant.foods" :key="product.id" class="p-col">
        <ProductItem :product="product"></ProductItem>
      </div>
    </div>
    <div v-else>loading ...</div>
    <ShoppingCart class="shopping-cart p-col-3" />
  </div>
</template>

<script>
import ProductItem from "./ProductItem.vue";
import ShoppingCart from "./ShoppingCart.vue";

export default {
  name: "Products",
  props: ["id"],
  components: {
    ProductItem,
    ShoppingCart,
  },
  data() {
    return {
      boughtProducts: this.$store.getters["cart/getAllProducts"],
      merchant: undefined,
    };
  },
  created() {
    // fetch the data when the view is created and the data is
    // already being observed
    this.fetchData();
  },
  methods: {
    async fetchData() {
      fetch(new URL(process.env.VUE_APP_MERCHANT_API + '/' + this.id, process.env.VUE_APP_BACKEND_URI))
        .then((data) => data.json())
        .then((json) => (this.merchant = json));
      fetch(new URL(process.env.VUE_APP_FOOD_API + '/' + this.id, process.env.VUE_APP_BACKEND_URI))
        .then((data) => data.json())
        .then((json) => (this.merchant = { ...this.merchant, foods: json }));
    },
  },
  computed: {},
};
</script>

<style scoped>
#image {
  max-width: 20rem;
  max-height: 25rem;
}
#merchant-name {
  font-size: 2rem;
  color: burlywood;
  width: 100%;
}
</style>
