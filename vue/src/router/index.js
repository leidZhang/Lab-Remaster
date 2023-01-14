import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },

  {
    path: '/task1',
    name: 'task1',
    component: () => import('../views/task1.vue')
  },
  {
    path: '/task2',
    name: 'task2',
    component: () => import('../views/task2.vue')
  },
  {
    path: '/task3',
    name: 'task3',
    component: () => import('../views/task3.vue')
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
