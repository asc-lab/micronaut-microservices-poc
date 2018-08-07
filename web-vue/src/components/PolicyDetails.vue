<template>
    <div>
        <h3>Policy details: {{ policy.number }}</h3>
        <div>
            <div class="row">
                <span><strong>From:</strong> {{ policy.dateFrom }}</span>
            </div>
            <div class="row">
                <span><strong>To:</strong> {{ policy.dateTo }}</span>
            </div>
            <div class="row">
                <span><strong>Policy holder:</strong> {{ policy.policyHolder }}</span>
            </div>
            <div class="row">
                <span><strong>Total premium:</strong> {{ policy.totalPremium }} EUR</span>
            </div>
            <div class="row">
                <span><strong>Product code:</strong> {{ policy.productCode }}</span>
            </div>
            <div class="row">
                <span><strong>Account number:</strong> {{ policy.accountNumber }}</span>
            </div>
            <div class="row">
                <span><strong>Covers:</strong> {{ policy.covers | join }}</span>
            </div>
        </div>
    </div>
</template>

<script>
    import {HTTP} from "./http/ApiClient";

    export default {
        name: "PolicyDetails",
        props: {
            policyNumber: String
        },
        data() {
            return {
                policy: {}
            }
        },
        created: function () {
            HTTP.get("policies/" + this.policyNumber).then(response => {
                this.policy = response.data.policy;
            })
        },
        filters: {
            join: function(value) {
                if(!value)
                    return '';

                return value.join(', ');
            }
        }
    }
</script>

<style scoped>

</style>