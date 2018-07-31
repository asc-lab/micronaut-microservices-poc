<template>
    <div>
        <ProductDetails product=product></ProductDetails>
    </div>
</template>

<script>
    import ProductDetails from "../components/ProductDetails";

    export default {
        name: "ProductDetailsView",
        components: {
            ProductDetails
        },
        props: ['product']
    }
</script>

<style scoped>

</style>