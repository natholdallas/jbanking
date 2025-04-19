import { isLogin, type VerifyFunc } from '@/common/permission'
import { createRouter, createWebHistory, type NavigationGuardWithThis } from 'vue-router'
import { urls } from './urls'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: urls.home,
      name: urls.home,
      redirect: urls.card,
      beforeEnter: routeCtl({ auth: [isLogin], backPath: urls.entrance }),
      component: () => import('@/views/HomeView.vue'),
      children: [
        {
          path: urls.message,
          name: urls.message,
          component: () => import('@/views/user/MessageView.vue')
        },
        {
          path: urls.settings,
          name: urls.settings,
          component: () => import('@/views/user/SettingsView.vue')
        },
        {
          path: urls.card,
          name: urls.card,
          component: () => import('@/views/user/CardView.vue')
        },
        {
          path: urls.comment,
          name: urls.comment,
          component: () => import('@/views/user/CommentView.vue')
        },
        {
          path: urls.loan,
          name: urls.loan,
          component: () => import('@/views/user/LoanView.vue')
        },
        {
          path: urls.transfer,
          name: urls.transfer,
          component: () => import('@/views/user/TransferView.vue')
        },
        {
          path: urls.about,
          name: urls.about,
          component: () => import('@/views/AboutView.vue')
        },
        {
          path: urls.manageUser,
          name: urls.manageUser,
          component: () => import('@/views/admin/ManageUserView.vue')
        },
        {
          path: urls.manageLoan,
          name: urls.manageLoan,
          component: () => import('@/views/admin/ManageLoanView.vue')
        },
        {
          path: urls.manageComment,
          name: urls.manageComment,
          component: () => import('@/views/admin/ManageCommentView.vue')
        },
        {
          path: urls.manageTransfer,
          name: urls.manageTransfer,
          component: () => import('@/views/admin/ManageTransferView.vue')
        }
      ]
    },
    {
      beforeEnter: routeCtl({ auth: [isLogin], nextPath: urls.home }),
      path: urls.entrance,
      component: () => import('@/views/EntranceView.vue')
    }
  ]
})

type RouteCTL = {
  auth?: VerifyFunc[]
  backPath?: string
  nextPath?: string
}

type Nav = NavigationGuardWithThis<undefined>

function routeCtl({ auth, backPath, nextPath }: RouteCTL = {}): Nav | Nav[] {
  return async (to, from, next) => {
    if (!auth) return next()
    const $auth = auth.some((e) => e())
    const path = $auth ? nextPath : backPath
    if (!path) return next()
    to = router.resolve(path)
    return next(to)
  }
}

export default router
