<template>
    <b-table bordered striped hover
             :items="policies"
             :fields="fields"
             @row-clicked="showDetails">
    </b-table>
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
        },
        methods: {
            showDetails(record) {
                this.$router.push({ name: 'policyDetails', params: { policyNumber: record.number }});
            }
        }
    }
</script>