<template>
    <b-table striped hover :items="policies" :fields="fields"></b-table>
</template>

<script>
    import {HTTP} from "./http/ApiClient";

    export default {
        name: "PolicyList",
        data() {
            return {
                fields: [
                    {key: 'number'},
                    {key: 'dateFrom'},
                    {key: 'dateTo'},
                    {key: 'policyHolder'}

                ],
                policies: []
            }
        },
        created: function () {
            HTTP.get('policies').then(response => {
                this.policies = response.data.policies;
            });
        }
    }
</script>

<style scoped>

</style>