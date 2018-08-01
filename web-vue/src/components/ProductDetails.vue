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
                <p>Wprowadź dane potrzebne do obliczenia ceny.</p>
            </div>
        </div>

        <div class="row">

            <div class="offset-sm-2 col-sm-10">
                <form>
                    <div v-for="a in answers" class="form-group row" :key="a.id">
                        <label class="col-sm-2 col-form-label">{{a.question.text}} </label>

                        <div class="col-sm-6" v-if="a.question.type==='numeric'">
                            <input type="number" class="form-control" v-model.number="a.answer" :disabled="mode=='VIEW' ? true : false">
                        </div>

                        <div class="col-sm-6" v-if="a.question.type==='choice'">
                            <select class="form-control" v-model="a.answer" :disabled="mode=='VIEW' ? true : false">
                                <option v-for="option in a.question.choices" v-bind:value="option.code" :key="option.code">
                                    {{ option.label }}
                                </option>
                            </select>
                        </div>

                    </div>

                    <!-- displays price -->
                    <div class="form-group row" v-if="mode=='VIEW'">
                        <label class="col-sm-2 col-form-label">Cena</label>
                        <div class="col-sm-6">
                            {{price.amountToPay}} PLN
                        </div>
                    </div>

                    <!--<p><pre>data: {{$data }}</pre></p>-->
                    <div class="form-group row" v-if="mode=='EDIT'">
                        <div class="offset-sm-2 col-sm-2">
                            <button type="submit" class="btn btn-primary" v-on:click.stop.prevent="calculatePrice">Oblicz cenę</button>
                        </div>
                        <div class="col-sm-2">
                            <a class="btn btn-secondary" href="#" v-on:click.stop.prevent="returnToList" role="button">Powrót</a>
                        </div>
                    </div>

                    <div class="form-group row" v-if="mode=='VIEW'">
                        <div class="offset-sm-2 col-sm-2">
                            <button type="submit" class="btn btn-primary" v-on:click.stop.prevent="calculatePrice">Kup</button>
                        </div>
                        <div class="col-sm-4">
                            <a class="btn btn-secondary" href="#" v-on:click.stop.prevent="backToEdit" role="button">Zmień parametry</a>
                            <router-link :to="{name: 'products'}">
                                <b-button variant="primary">Powrót</b-button>
                            </router-link>
                        </div>
                    </div>

                </form>
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
                // for (var i = 0; i < this.productDetails.questions.length; i++) {
                //     var answer = {answer: null, question: this.productDetails.questions[i]};
                //     this.answers.push(answer);
                // }
            });
        },
        methods: {
            returnToList: function () {
                //router.push({name: 'products'});
            },
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

                HTTP.post('/price', request).then(response => {
                    console.log(response.data);
                    this.mode = 'VIEW';
                    this.price.amountToPay = response.data.totalPremium;
                });
            }
        }
    }
</script>

<style scoped lang="scss">

</style>