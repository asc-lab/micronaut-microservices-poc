import Vue from 'vue';
import VueResource from 'vue-resource'
import App from './App.vue';
import BootstrapVue from 'bootstrap-vue'
import router from './router';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';
import 'font-awesome/css/font-awesome.css'
import underscore from 'vue-underscore';
import moment from 'moment';

Vue.config.productionTip = false;
Vue.use(BootstrapVue);
Vue.use(VueResource);
Vue.use(underscore);
Vue.prototype.moment = moment;

new Vue({
    router,
    render: h => h(App)
}).$mount('#app');
