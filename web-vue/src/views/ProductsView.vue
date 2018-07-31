<template>
    <div>
        <ProductList msg="Welcome to Your Vue.js App"/>
    </div>
</template>

<script>
    import ProductList from "../components/ProductList";

    export default {
        name: 'products',
        components: {
            ProductList
        }
    }
</script>
