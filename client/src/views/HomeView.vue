<script setup lang="ts">
import { Account, User } from '@/app/api'
import { Permission } from '@/app/models-out'
import { convertVexipUser } from '@/common/utils/vexip-util'
import router from '@/router'
import { urls } from '@/router/urls'
import { useConfigStore } from '@/stores/configStore'
import {
  BarsProgress as IBarsProgress,
  CircleInfo as ICircleInfo,
  Comment as IComment,
  CommentDotsR as ICommentDotsR,
  FilePen as IFilePen,
  Gear as IGear,
  Landmark as ILandmark,
  Message as IMessage,
  MoneyBillTransfer as IMoneyBillTransfer,
  RightFromBracket as IRightFromBracket,
  TriangleExclamation as ITriangleExclamation,
  UserPen as IUserPen,
  Wallet as IWallet
} from '@vexip-ui/icons'
import { useRequest } from 'alova'
import type { LayoutHeaderAction, MenuOptions } from 'vexip-ui'
import { Confirm, Layout as VLayout } from 'vexip-ui'

const configStore = useConfigStore()

const menus: MenuOptions[] = [
  {
    label: urls.card,
    name: '卡包',
    icon: IWallet
  },
  {
    label: urls.transfer,
    name: '转账',
    icon: IMoneyBillTransfer
  },
  {
    label: urls.loan,
    name: '贷款',
    icon: ILandmark
  },
  {
    label: urls.comment,
    name: '功能提议',
    icon: IComment
  },
  {
    label: urls.about,
    name: '关于我们',
    icon: ICircleInfo
  }
]

const actions: LayoutHeaderAction[] = [
  {
    label: urls.message,
    icon: ICommentDotsR,
    name: '你的消息'
  },
  {
    label: urls.settings,
    icon: IGear,
    name: '帐号设置',
    divided: true
  },
  {
    label: 'logout',
    name: '退出登录',
    icon: IRightFromBracket,
    meta: {
      logout: async () => {
        const isConfirm = await Confirm.open({
          title: '退出登录',
          content: '是否退出登录? 退出登录将清除登录状态, 但会保存你的配置文件在本地, 详细请阅读帮助文档',
          confirmType: 'warning',
          icon: ITriangleExclamation,
          iconProps: { scale: 3 }
        })
        if (isConfirm) logout()
      }
    }
  }
]

const {
  loading,
  data: user,
  onSuccess: onRetriveSuccess
} = useRequest(User.$retrive(), {
  initialData: User.$new()
})
onRetriveSuccess(async ({ data }) => {
  if (data.permission == Permission.ADMIN) {
    menus.push({
      label: '',
      name: '管理面板',
      icon: IBarsProgress,
      children: [
        {
          label: urls.manageUser,
          name: '用户信息',
          icon: IUserPen
        },
        {
          label: urls.manageLoan,
          name: '贷款审核',
          icon: IFilePen
        },
        {
          label: urls.manageComment,
          name: '评论管理',
          icon: IMessage
        },
        {
          label: urls.manageTransfer,
          name: '订单信息',
          icon: IMoneyBillTransfer
        }
      ]
    })
  }
  if (data.isIdentify || data.id == -1) return
  const isConfirm = await Confirm.open({
    title: '实名认证',
    content: '我们发现您还没有实名认证, 在注册之后没有实名认证的情况下, 账号会在30天后自动注销, 请尽快实名认证',
    confirmText: '去认证',
    confirmType: 'warning',
    icon: ITriangleExclamation,
    maskClose: false
  })
  if (isConfirm) router.push(urls.settings)
})
const { send: logout, onSuccess: onLogoutSuccess } = useRequest(Account.$logout(), {
  immediate: false
})
onLogoutSuccess(() => router.go(0))
</script>

<template>
  <VLayout
    v-if="!loading"
    logo="/favicon.ico"
    sign-name="INSANE-BANK"
    v-model:color="configStore.color"
    v-model:dark-mode="configStore.darkMode"
    v-model:reduced="configStore.menuReduced"
    @menu-select="$router.push"
    @user-action="
      (label, meta) => {
        if (meta.logout) return meta.logout()
        $router.push(label)
      }
    "
    :user="convertVexipUser(user)"
    :actions="actions"
    :menus="menus"
    fixed-main
    fit-window
  >
    <template #main>
      <div :class="`w-full h-full ${configStore.darkMode ? 'bg-[#121212]' : 'bg-[#ffffff]'}`">
        <RouterView v-slot="{ Component }">
          <Transition name="vxp-fade" mode="out-in">
            <component :is="Component" />
          </Transition>
        </RouterView>
      </div>
    </template>
  </VLayout>
</template>

<style scoped></style>
