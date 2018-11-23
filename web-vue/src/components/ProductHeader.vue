<template>
    <div>
                <b-carousel-slide v-bind:img-src="imageUrl">
                    <h1>{{productTitle}}</h1>
                    <span v-html="productShortDescription"></span>
                    <router-link :to="{name: 'product', params: { productCode: productCode }}">
                        <b-button type="submit" variant="primary">Buy</b-button>
                    </router-link>
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
        computed: {
            image: function() {
                if (imageUrl) return imageUrl;

                return defaultImageUrl;
            }
        },
        components: { },
        data() {
            return {
                defaultImageUrl: "https://picsum.photos/1024/480/?image=54",
                imageUrl: null
            };
        },
        created: function () {
            HTTP.get('crm/imageset' + this.productImageUrl, { responseType: 'blob' }).then(response => {
                this.x = response;
                console.log(response);
                var url = window.URL.createObjectURL(response.data);
                console.log(url);
                this.imageUrl = url;
            });
        }
    }
</script>

<style scoped lang="scss">

</style>