<template>
    <div>
        

                <b-carousel-slide v-bind:img-src="imageUrl">
                    <h1>{{productTitle}}</h1>
                    <h2>{{productImageUrl}}</h2>
                    <span v-html="productShortDescription"></span>
                </b-carousel-slide>

        

    </div>
</template>

<script>
    import {HTTP} from "./http/ApiClient";

    export default {
        name: 'ProductHeader',
        props: {
            productCode: String,
            productTitle: String,
            productShortDescription: String,
            productImageUrl: String
        },
        components: { },
        data() {
            return {
                imageUrl: "https://picsum.photos/1024/480/?image=54"
            };
        },
        created: function () {
            //HTTP.get('crm/productHeaders').then(response => {
            //    this.productsHeaders = response.data;
            //});
            HTTP.get('crm/imageset' + this.productImageUrl).then(response => {
                this.x = response;
            });
        }
    }
</script>

<style scoped lang="scss">

</style>