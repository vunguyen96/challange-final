<template>
  <div class="product-container pricing-table-price">
    <img :src="product.image" class="product-image" />
    <div class="product-name">{{ product.name }}</div>
    <div class="product-price">${{ product.price }}</div>
    <div class="product-add-cart p-mt-3">
      <AddToCart :product="product" />
    </div>
  </div>
</template>
<script>
import productService from "../assets/js/product-service";
import AddToCart from "./AddToCart.vue";

export default {
  name: "ProductItem",
  components: {
    AddToCart,
  },
  props: {
    product: Object,
  },
  data() {
    return {};
  },
  methods: {
    getImgUrl(product) {
      return productService.getImgUrl(product);
    },
  },
};
</script>
<style>
@import "../assets/css/styles.css";

.product-container {
  max-width: 100%;
  background-color: white;
  text-align: center;
  font-size: 18px;
  padding: 20px 15px;
  height: 100%;
  min-width: 280px;
}
.product-container .product-image {
  display: block;
  margin-left: auto;
  margin-right: auto;
  padding: 15px 0px;
  height: 120px;
  max-width: 100%;
}
.product-container .product-name {
  font-size: 22px;
  font-weight: bold;
}
.product-container .product-price {
  font-size: 20px;
  color: rgb(36, 196, 134);
}
</style>