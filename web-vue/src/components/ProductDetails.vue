<template>
    <div class="container">

        <div class="row">
            <div class="col-sm-4">
                <img class="card-img-top" v-bind:src="productDetails.image" v-bind:alt="productDetails.name">
            </div>
            <div class="col-sm-8">
                <h1 class="display-3">{{productDetails.name}}</h1>
                <p class="lead">{{productDetails.description}}.</p>
                <hr class="my-4">
                <p>Enter the data needed to calculate the price</p>

                <div class="row">

                    <div class="col-sm-12">
                        <form>
                            <div v-for="a in answers" class="form-group row" :key="a.id">
                                <label class="col-sm-3 col-form-label">{{a.question.text}} </label>

                                <div class="col-sm-9" v-if="a.question.type==='numeric'">
                                    <input type="number" class="form-control" v-model="a.answer" :disabled="mode=='VIEW' ? true : false"/>
                                </div>

                                <div class="col-sm-9" v-if="a.question.type==='choice'">
                                    <select class="form-control" v-model="a.answer" :disabled="mode=='VIEW' ? true : false">
                                        <option v-for="option in a.question.choices" v-bind:value="option.code" :key="option.code">
                                            {{ option.label }}
                                        </option>
                                    </select>
                                </div>

                            </div>

                            <!-- displays price -->
                            <div class="form-group row" v-if="mode=='VIEW'">
                                <label class="col-sm-2 col-form-label">Price</label>
                                <div class="col-sm-6">
                                    {{price.amountToPay}} PLN
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-12 margin-top-10" v-if="mode === 'EDIT'">
                <div class="d-flex flex-row-reverse">
                    <div class="p-2">
                        <button type="submit" class="btn btn-primary" v-on:click.stop.prevent="calculatePrice">Calculate price</button>
                    </div>
                    <div class="p-2">
                        <router-link :to="{name: 'products'}">
                            <b-button variant="secondary">Back</b-button>
                        </router-link>
                    </div>
                </div>

                <div class="d-flex flex-row-reverse" v-if="mode === 'VIEW'">
                    <div class="p-2">
                        <button type="submit" class="btn btn-primary" v-on:click.stop.prevent="calculatePrice">Buy</button>
                    </div>
                    <div class="p-2">
                        <a class="btn btn-secondary" href="#" v-on:click.stop.prevent="backToEdit" role="button">Change parameters</a>
                    </div>
                    <div class="p-2">
                        <router-link :to="{name: 'products'}">
                            <b-button variant="secondary">Back</b-button>
                        </router-link>
                    </div>
                </div>
            </div>
        </div>

    </div>
</template>

<script>
    import {HTTP} from "./http/ApiClient";

    export default {
        name: 'ProductDetails',
        props: {
            productCode: String
        },
        data() {
            return {
                productDetails: {},
                answers: [],
                mode: 'EDIT',
                price: {
                    amountToPay: null
                }
            }
        },
        created: function () {
            console.log(this.productCode);
            HTTP.get('products/' + this.productCode).then(response => {
                this.productDetails = response.data;
                console.log(this.productDetails);
                for (let i = 0; i < this.productDetails.questions.length; i++) {
                    this.answers.push({
                        answer: null,
                        question: this.productDetails.questions[i]
                    });
                }
            });
        },
        methods: {
            backToEdit: function () {
                this.mode = 'EDIT';
            },
            calculatePrice: function () {
                const request = {
                    'productCode': this.productDetails.code,
                    'selectedCovers': [],
                    'answers': []
                };

                for (let i = 0; i < this.productDetails.covers.length; i++) {
                    request.selectedCovers.push(this.productDetails.covers[i].code);
                }

                for (let j = 0; j < this.answers.length; j++) {
                    request.answers.push({'questionCode': this.answers[j].question.code, 'answer': this.answers[j].answer});
                }

                HTTP.post('/pricing/price', request).then(response => {
                    console.log(response.data);
                    this.mode = 'VIEW';
                    this.price.amountToPay = response.data.totalPremium;
                });
            }
        }
    }
</script>

<style scoped lang="scss">
    .margin-top-10 {
        margin-top: 10px;
    }
</style>