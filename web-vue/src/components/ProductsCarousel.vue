<template>
    <div>
        <b-carousel id="carousel1"
                style="text-shadow: 1px 1px 2px #333;"
                controls
                indicators
                background="#ababab"
                :interval="4000"
                img-width="1024"
                img-height="480"
                v-model="productsHeaders.length">

                <ProductHeader 
                    v-for="product in productsHeaders" :key="product.code"
                    :productCode="product.code" 
                    :productTitle="product.title" 
                    :productShortDescription="product.shortDescription"
                    :productImageUrl="product.mainPictureUrl"
                    >
                </ProductHeader>

        </b-carousel>

    </div>
</template>

<script>
    import {HTTP} from "./http/ApiClient";
    import ProductHeader from "./ProductHeader"

    export default {
        name: 'ProductsCarousel',
        components: { ProductHeader },
        data() {
            return {
                productsHeaders: [],
                selectedProductIndex: 0
            };
        },
        created: function () {
            HTTP.get('cms/productHeaders').then(response => {
                this.productsHeaders = response.data;
            });
        }
    }
</script>

<style scoped lang="scss">

</style>