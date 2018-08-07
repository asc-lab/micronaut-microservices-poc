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
                    {
                        key: 'dateFrom',
                        formatter: (value) => {
                            if (!value)
                                return "";
                            return value[2] + "-" + value[1] + "-" + value[0];
                        }
                    },
                    {
                        key: 'dateTo',
                        formatter: (value) => {
                            if (!value)
                                return "";
                            return value[2] + "-" + value[1] + "-" + value[0];
                        }
                    },
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