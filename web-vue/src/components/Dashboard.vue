<template>
    <div>

        <div class="container-fluid">
        <div class="row">
              <TotalSalesCard title='Total sales' :amount='totalAmount' icon='bitcoin'/>

              <TotalSalesCard title='Num. policies' :amount='totalPolicies' icon='book'/>

              <TotalSalesCard v-for="product in products" 
                :key="product.code" 
                :title='product.name' 
                :amount='totalAmounts[product.code]'
                :policiesCount='totalCounts[product.code]'
                :icon='product.icon'/>
        </div>
    </div>

    <div class="container-fluid">
        <div class="row">
            <div>&nbsp;</div>
        </div>
    </div>

    <div class="container-fluid">
        <div class="card-columns cols-2">
            <SalesTrendsLines :salesTrendsData="salesTrends"/>
            <SalesDistribution :productTotals="totalAmounts"/>
            <SalesAgents :agentTotals="agentsTotalAmounts"/>
        </div>
    </div>

    </div>
</template>

<script>
    import {HTTP} from "./http/ApiClient";
    import SalesAgents from "./SalesAgents";
    import SalesTrendsLines from "./SalesTrendsLines"
    import SalesDistribution from "./SalesDistribution"
    import TotalSalesCard from './TotalSalesCard'
    
    export default {
        name: 'Dashboard',
        props: {
            msg: String
        },
        components: {
            SalesAgents, SalesTrendsLines, SalesDistribution, TotalSalesCard
        },
        data() {
            return {
                products: [],
                totalAmounts: {},
                totalCounts: {},
                totalAmount: 0,
                totalPolicies: 0,
                agentsTotalAmounts: {},
                agentsTotalCounts: {},
                salesTrends: []
            };
        },
        created: function() {
          HTTP.get('products').then(response => {
            this.products = response.data;
            this.fetchTotalSales();
            this.fetchAgentsSales();
            this.fetchSalesTrends();
          });
        },
        methods: {
          fetchTotalSales() {
            HTTP
              .post('dashboard/totalsales', { "saleDateFrom"	: "2018-01-01", "saleDateTo"	: "2020-12-31"})
              .then(response => {
                this.totalAmount = response.data.total.premiumAmount;
                this.totalPolicies = response.data.total.policiesCount;
                var amounts = {};
                var counts = {};
                for (var prop in response.data.perProductTotal) {
                  amounts[prop] = response.data.perProductTotal[prop].premiumAmount;
                  counts[prop] = response.data.perProductTotal[prop].policiesCount;
                }
                this.totalAmounts = amounts;
                this.totalCounts = counts;
              });  
          },
          fetchAgentsSales() {
            HTTP
              .post('dashboard/agentssales', { "saleDateFrom"	: "2018-01-01", "saleDateTo"	: "2020-12-31"})
              .then(response => {
                var amounts = {};
                var counts = {};
                Object.getOwnPropertyNames(response.data.perAgentTotal).forEach(val => {
                  amounts[val] = response.data.perAgentTotal[val].premiumAmount;
                  counts[val] = response.data.perAgentTotal[val].policiesCount;
                });

                this.agentsTotalAmounts = amounts;
                this.agentsTotalCounts = counts;
              });
          },
          fetchSalesTrends() {
            HTTP
              .post('dashboard/trends', { "saleDateFrom"	: "2018-01-01", "saleDateTo"	: "2020-12-31", "aggregationUnitCode" : 'MONTH'})
              .then(response => {
                this.salesTrends = response.data.periodSales;
              });
          }
        }
    } 
</script>

<style scoped>
    h3 {
        margin: 40px 0 0;
    }

    ul {
        list-style-type: none;
        padding: 0;
    }

    li {
        display: inline-block;
        margin: 0 10px;
    }

    a {
        color: #42b983;
    }

    .arch-image {
        width: 100%;
    }


@media (min-width: 576px) {
  .card-columns.cols-2 {
    -webkit-column-count: 2;
    -moz-column-count: 2;
    column-count: 2;
  }


.card-header-actions {
  display: inline-block;
  float: right;
  margin-right: -0.25rem;
}

*[dir="rtl"] .card-header-actions {
  float: left;
  margin-right: auto;
  margin-left: -0.25rem;
}

.card-header-action {
  padding: 0 0.25rem;
  color: #73818f;
}

.card-header-action:hover {
  color: #23282c;
  text-decoration: none;
}
}
</style>
