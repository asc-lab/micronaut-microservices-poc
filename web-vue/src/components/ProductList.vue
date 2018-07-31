<template>
    <div class="row">
        <div class="col-sm-4" v-for="product in products">
            <ProductCard :product="product"></ProductCard>
        </div>
    </div>
</template>

<script>
    import ProductCard from "./ProductCard";
    import {HTTP} from "./http/ApiClient";

    export default {
        name: 'ProductList',
        components: {ProductCard},
        data() {
            return {
                products: []
            };
        },
        created: function () {
            HTTP.get('/api/products').then(response => {
                    this.products = response.data;
                },
                () => {
                    console.error('something went wrong when calling api')
                }
            );
        }
    }
</script>

<style scoped lang="scss">

</style>