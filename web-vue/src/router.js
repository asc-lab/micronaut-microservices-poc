import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/HomeView.vue'

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            name: 'home',
            component: Home
        },
        {
            path: '/about',
            name: 'about',
            // route level code-splitting
            // this generates a separate chunk (about.[hash].js) for this route
            // which is lazy-loaded when the route is visited.
            component: () => import(/* webpackChunkName: "about" */ './views/AboutView.vue')
        },
        {
            path: '/products',
            name: 'products',
            component: () => import('./views/ProductsView.vue')
        },
        {
            path: '/products/:productCode',
            name: 'product',
            props: true,
            component: () => import('./views/ProductDetailsView.vue')
        }
    ]
})
