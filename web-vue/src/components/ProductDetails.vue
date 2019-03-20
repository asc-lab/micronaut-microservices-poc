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

                        <form @submit.prevent="submitForm">
                            <div class="form-group row">
                                <label class="col-sm-3 col-form-label">Policy from </label>
                                <div class="col-sm-9">
                                    <b-form-input v-model="policyFrom"
                                                  type="date"
                                                  id="policyFrom"
                                                  name="policyFrom"
                                                  :disabled="'VIEW' === mode"
                                                  required
                                                  placeholder="Policy from"></b-form-input>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label class="col-sm-3 col-form-label">Policy to </label>
                                <div class="col-sm-9">
                                    <b-form-input v-model="policyTo"
                                                  type="date"
                                                  id="policyTo"
                                                  name="policyTo"
                                                  required
                                                  :disabled="'VIEW' === mode"
                                                  placeholder="Policy to"></b-form-input>
                                </div>
                            </div>

                            <div v-for="a in answers" class="form-group row" :key="a.id">
                                <label class="col-sm-3 col-form-label">{{a.question.text}} </label>

                                <div class="col-sm-9" v-if="a.question.type==='numeric'">
                                    <b-form-input v-model="a.answer"
                                                  class="form-control"
                                                  type="number"
                                                  :disabled="'VIEW' === mode"
                                                  min="0"
                                                  required></b-form-input>
                                </div>

                                <div class="col-sm-9" v-if="a.question.type==='choice'">
                                    <select required class="form-control" v-model="a.answer" :disabled="'VIEW' === mode">
                                        <option v-for="option in a.question.choices" v-bind:value="option.code"
                                                :key="option.code">
                                            {{ option.label }}
                                        </option>
                                    </select>
                                </div>

                            </div>

                            <div v-if="errors.length">
                                <strong>Please correct the following error(s):</strong>
                                <p class="error" v-for="error in errors" :key="error">{{ error }}</p>
                            </div>

                            <!-- displays price -->
                            <div class="form-group row" v-if="'VIEW' === mode">
                                <label class="col-sm-3 col-form-label">Price</label>
                                <div class="col-sm-9">
                                    <span class="float-left">
                                        <strong>{{price.amountToPay}} EUR</strong>
                                    </span>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-sm-12 margin-top-10">
                                    <div class="d-flex flex-row-reverse" v-if="'EDIT' === mode">
                                        <div class="p-2">
                                            <b-button type="submit" variant="primary">Calculate price</b-button>
                                        </div>
                                        <div class="p-2">
                                            <router-link :to="{name: 'products'}">
                                                <b-button variant="secondary">Back</b-button>
                                            </router-link>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row-reverse" v-if="'VIEW' === mode">
                                        <div class="p-2">
                                            <button type="submit" class="btn btn-primary" v-on:click.stop.prevent="buyOffer">Buy</button>
                                        </div>
                                        <div class="p-2">
                                            <a class="btn btn-secondary" href="#" v-on:click.stop.prevent="backToEdit" role="button">Change
                                                parameters</a>
                                        </div>
                                        <div class="p-2">
                                            <router-link :to="{name: 'products'}">
                                                <b-button variant="secondary">Back</b-button>
                                            </router-link>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </form>

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
                errors: [],
                productDetails: {},
                answers: [],
                mode: 'EDIT',
                price: {
                    amountToPay: null
                },
                policyFrom: '',
                policyTo: '',
                offerNumber: ''
            }
        },
        created: function () {
            HTTP.get('products/' + this.productCode).then(response => {
                this.productDetails = response.data;
                if (!this.productDetails.questions)
                    return;

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
            createRequest: function () {
                const request = {
                    'productCode': this.productDetails.code,
                    'policyFrom': this.policyFrom,
                    'policyTo': this.policyTo,
                    'selectedCovers': [],
                    'answers': []
                };

                for (let i = 0; i < this.productDetails.covers.length; i++) {
                    request.selectedCovers.push(this.productDetails.covers[i].code);
                }

                for (let j = 0; j < this.answers.length; j++) {
                    request.answers.push({
                        'questionCode': this.answers[j].question.code,
                        'type': this.answers[j].question.type,
                        'answer': this.answers[j].answer
                    });
                }

                console.log(request);
                return request;
            },
            submitForm: function (e) {
                const isValid = this.validate();
                if (isValid)
                    this.calculatePrice();
                e.preventDefault();
            },
            validate() {
                this.errors = [];

                if(new Date(this.policyFrom) < new Date()) {
                    this.errors.push('Policy from should be in future');
                }
                if(new Date(this.policyTo) < new Date()) {
                    this.errors.push('Policy to should be in future');
                }

                return this.errors.length === 0;
            },
            calculatePrice: function () {
                HTTP.post('offers', this.createRequest()).then(response => {
                    this.mode = 'VIEW';
                    this.price.amountToPay = response.data.totalPrice;
                    this.offerNumber = response.data.offerNumber;
                }, () => {
                    alert('Bad Things Happened!');
                });
            },
            buyOffer: function () {
                this.$router.push({name: 'createPolicy', params: {offerNumber: this.offerNumber}});
            }
        }
    }
</script>

<style scoped lang="scss">
    .margin-top-10 {
        margin-top: 10px;
    }

    .error {
        border: red 2px solid;
        width: 300px;
        margin: 5px auto;
        background: red;
        color: white;
    }
</style>