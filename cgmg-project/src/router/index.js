import { createRouter, createWebHistory } from 'vue-router'
import MainPage from '../views/MainPage.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'main',
      component: MainPage
    },
    {
      path: '/exercise',
      name: 'exercise',
      component: () => import('../views/ExercisePage.vue')
    },
    {
      path: '/community',
      name: 'community',
      component: () => import('../views/CommunityPage.vue')
    },
    {
      path: '/guide',
      name: 'guide',
      component: () => import('../views/GuidePage.vue')
    },
    {
      path: '/rank',
      name: 'rank',
      component: () => import('../views/RankPage.vue')
    },
  ]
})

export default router
