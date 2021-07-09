<template>
    <div class="">
       <Button label="Add to Cart" icon="pi pi-shopping-cart" iconPos="left" @click="addToCart(product)"/>
    </div>
</template>
<script>

export default {
    name : "AddToCart",
    props : {
        product : Object
    },
    data(){
        return {
        }
    },
    methods: {
        addToCart(product) {
            this.$store.dispatch('cart/addProduct', product);
        }
    }
}
</script>