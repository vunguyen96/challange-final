import Products from './components/Products.vue'
// import ProductLayout from './components/ProductLayout'
import Recommendation from './components/Recommendation.vue'
// import Merchant from './components/Merchant.vue'

import { createRouter, createWebHistory } from 'vue-router';

const routes = [
  {
    path: '/',
    name: 'recommendation', 
    component: Recommendation,
  },
  {
    path: '/merchant/:id',
    name: 'merchant', 
    component: Products,
    props: true
  }
]

const router = createRouter({
    history: createWebHistory(),
    routes: routes
});


export default router

/*
router.beforeEach(checkForLogin())

function checkForLogin() {
  return (to, from, next) => {

    // redirect to login page if not logged in and trying to access a restricted page
    const publicPages = ['/login'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = localStorage.getItem('user');

    if (authRequired && !loggedIn) {
      return next({
        path: '/login',
        query: { returnUrl: to.path }
      });
    }

    next();
  };
}
*/
