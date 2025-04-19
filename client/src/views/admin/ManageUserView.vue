<script setup lang="ts">
import { Admin } from '@/app/admin/api'
import { UserOut } from '@/app/admin/models-out'
import { $dialog, middlewares } from '@/app/core/middlewares'
import { Permission } from '@/app/models-out'
import ComCtl from '@/components/ComCtl.vue'
import UserCreationForm from '@/components/form/admin/UserCreationForm.vue'
import UserUpdateForm from '@/components/form/admin/UserUpdateForm.vue'
import { PenToSquare as IPenToSquare, Plus as IPlus, TrashCan as ITrashCan } from '@vexip-ui/icons'
import { useRequest } from 'alova'
import {
  Button as VButton,
  Card as VCard,
  Modal as VModal,
  Table as VTable,
  Tag as VTag,
  defineTableColumns
} from 'vexip-ui'
import { h, ref } from 'vue'

const createModal = ref(false)
const updateModal = ref(false)
const columns = defineTableColumns<UserOut>([
  {
    name: '用户名',
    key: 'username'
  },
  {
    name: '姓名',
    key: 'idName'
  },
  {
    name: '身份证号',
    key: 'idCard'
  },
  {
    name: '权限',
    key: 'permission',
    renderer: ({ row }) =>
      row.permission === Permission.ADMIN
        ? h(VTag, { type: 'success' }, { default: () => '管理员' })
        : h(VTag, { type: 'warning' }, { default: () => '用户' })
  },
  {
    name: '操作',
    key: 'id',
    renderer: ({ row }) => [
      h(VButton, {
        type: 'error',
        onClick: () => {
          user.value = row
          destroy()
        },
        icon: ITrashCan,
        text: true
      }),
      h(VButton, {
        onClick: () => {
          user.value = row
          updateModal.value = true
        },
        icon: IPenToSquare,
        text: true
      })
    ]
  }
])

const user = ref(UserOut.$new())
const {
  loading,
  data: users,
  send: refresh
} = useRequest(Admin.list(), {
  initialData: []
})
const { send: destroy, onSuccess: onDestroySuccess } = useRequest(() => Admin.destroy(user.value), {
  immediate: false,
  middleware: middlewares($dialog.destroy)
})
onDestroySuccess(refresh)
</script>

<template>
  <ComCtl content-class="flex flex-col gap-4" :loading="loading">
    <VCard title="操作面板">
      <template #extra>
        <VButton type="success" :icon="IPlus" @click="createModal = true"></VButton>
      </template>
      <div class="font-semibold">
        <div>用户总数: {{ users.length }}</div>
        <div>管理员总数: {{ users.filter((x) => x.permission === Permission.ADMIN).length }}</div>
        <div>普通用户总数: {{ users.filter((x) => x.permission === Permission.USER).length }}</div>
      </div>
    </VCard>
    <VTable :columns="columns" :data="users" />
    <template #modals>
      <VModal v-if="createModal" v-model:active="createModal">
        <UserCreationForm
          @success="
            () => {
              refresh()
              createModal = false
            }
          "
        />
      </VModal>
      <VModal v-if="updateModal" v-model:active="updateModal">
        <UserUpdateForm
          :default-value="user"
          @success="
            () => {
              refresh()
              updateModal = false
            }
          "
        />
      </VModal>
    </template>
  </ComCtl>
</template>

<style scoped></style>
