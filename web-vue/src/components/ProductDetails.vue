<template>
    <div class="container">

        <div class="row">
            <div class="col-sm-4">
                <img class="card-img-top" v-bind:src="product.image" alt="Card image cap">
            </div>
            <div class="col-sm-8">
                <h1 class="display-3">{{product.name}}</h1>
                <p class="lead">{{product.description}}.</p>
                <hr class="my-4">
                <p>Wprowadz dane potrzebne do obliczenia ceny.</p>
            </div>
        </div>
        <div class="row">

            <div class="offset-sm-2 col-sm-10">
                <form>
                    <div v-for="(a,index) in answers" class="form-group row">
                        <label class="col-sm-2 col-form-label">{{a.question.text}} </label>

                        <div class="col-sm-6" v-if="a.question.type==='numeric'">
                            <input type="numeric" class="form-control" v-model.number="a.answer" :disabled="mode=='VIEW' ? true : false">
                        </div>

                        <div class="col-sm-6" v-if="a.question.type==='choice'">
                            <select class="form-control" v-model="a.answer" :disabled="mode=='VIEW' ? true : false">
                                <option v-for="option in a.question.choices" v-bind:value="option.code">
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
                            <a class="btn btn-secondary" href="#" v-on:click.stop.prevent="golist" role="button">Powrót</a>
                        </div>
                    </div>

                    <div class="form-group row" v-if="mode=='VIEW'">
                        <div class="offset-sm-2 col-sm-2">
                            <button type="submit" class="btn btn-primary" v-on:click.stop.prevent="calculatePrice">Kup</button>
                        </div>
                        <div class="col-sm-4">
                            <a class="btn btn-secondary" href="#" v-on:click.stop.prevent="backToEdit" role="button">Zmień parametry</a>
                            <a class="btn btn-secondary" href="#" v-on:click.stop.prevent="golist" role="button">Powrót</a>
                        </div>
                    </div>

                </form>
            </div>

        </div>

    </div>
</template>

<script>
    export default {
        name: 'ProductDetails',
        props: ['product'],
        reated: function () {

        },
        mounted: function () {
            console.log(this.product);

            for (var i = 0; i < this.product.questions.length; i++) {
                var answer = {answer: null, question: this.product.questions[i]};
                this.answers.push(answer);
            }
        },
        data: function () {
            return {
                answers: [],
                mode: 'EDIT',
                price: {
                    amountToPay: null
                }
            };
        },
        methods: {
            golist: function () {
                router.push({name: 'products'});
            },
            backToEdit: function () {
                this.mode = 'EDIT';
            },
            calculatePrice: function () {
                //create request and call api
                var request = {
                    'productCode': this.product.code,
                    'selectedCovers': [],
                    'answers': []
                };

                for (var i = 0; i < this.product.covers.length; i++) {
                    request.selectedCovers.push(this.product.covers[i].code);
                }

                for (var i = 0; i < this.answers.length; i++) {
                    request.answers.push({'questionCode': this.answers[i].question.code, 'answer': this.answers[i].answer});
                }

                //call api
                this.$http.post('/api/price', request).then(
                    response => {
                        console.log(response.body);
                        this.mode = 'VIEW';
                        this.price.amountToPay = response.body.totalPremium;
                    },
                    response => {
                        console.error('something went wrong when calling api')
                    }
                );
            }
        }
    }
</script>

<style scoped lang="scss">

</style>