<template>
    <div class="card">
        <img class="card-img-top" v-bind:src="product.image" alt="Card image cap">
        <div class="card-block">
            <h3 class="card-title">{{product.name}}</h3>
            <p class="card-text">{{product.description}}</p>
        </div>
        <CoverList :covers="product.covers"></CoverList>
        <div class="card-block">
            <button class="btn btn-primary" @click="gobuy">Kup</button>
        </div>
    </div>
</template>

<script>
    import CoverList from "./CoverList";

    export default {
        name: 'ProductCard',
        props: ['product'],
        components: {CoverList},
        methods: {
            gobuy: function () {
                router.push({name: 'product', params: {product: this.product}});
            }
        }
    }
</script>

<style scoped lang="scss">

</style>