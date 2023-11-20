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
      component: () => import('../views/ExercisePage.vue'),
    },
    {
      path: '/exercise/:bodyPart/:exercise',
      name: 'ExerciseDetail',
      component: () => import('../components/exercise/ExerciseDetailComp.vue'),
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
    {
      path: "/login",
      name: "login",
      component: () => import("../views/LoginPage.vue"),
    },
    {
      path: "/regist",
      name: "regist",
      component: () => import("../views/RegistPage.vue"),
    },
    {
      path: "/profile",
      name: "profile",
      component: () => import("../views/ProfilePage.vue"),
    },
    {
      path: "/otherProfile/:userId",
      name: "otherProfile",
      component: () => import("../views/OtherProfilePage.vue"),
    },
    {
      path: "/search/:word",
      name: "search",
      component: () => import("../views/SearchPage.vue"),
    },
    {
      path: '/community',
      name: 'community',
      component: () => import('../views/CommunityPage.vue'),
    },
    {
      path: "/community/create",
      name: "communityCreate",
      component: () => import('../components/community/BoardCreate.vue'),
    },
    {
      path: "/community/update/:id",
      name: "communityUpdate",
      component: () => import('../components/community/BoardUpdate.vue'),
    },
    {
      path: "/community/:id",
      name: "boardDetail",
      component: () => import('../components/community/BoardDetail.vue'),
    },
  ]
})

export default router
