<script setup lang="ts">
import { Transfer } from '@/app/admin/api'
import { TransferOut } from '@/app/admin/models-out'
import { $dialog, middlewares } from '@/app/core/middlewares'
import ComCtl from '@/components/ComCtl.vue'
import { TrashCan as ITrashCan } from '@vexip-ui/icons'
import { useRequest } from 'alova'
import { Button as VButton, Card as VCard, Table as VTable, defineTableColumns } from 'vexip-ui'
import { h, ref } from 'vue'

const columns = defineTableColumns<TransferOut>([
  {
    name: '用户',
    key: 'card',
    renderer: ({ row }) => row.card.user.username
  },
  {
    name: '转账卡号',
    key: 'card',
    renderer: ({ row }) => row.card.number
  },
  {
    name: '目标账户',
    key: 'target'
  },
  {
    name: '转账数额',
    key: 'amount'
  },
  {
    name: '操作',
    key: 'id',
    renderer: ({ row }) =>
      h(VButton, {
        type: 'error',
        onClick: () => {
          transfer.value = row
          destroy()
        },
        icon: ITrashCan,
        text: true
      })
  }
])

const transfer = ref(TransferOut.$new())
const {
  loading,
  data: transfers,
  send: refresh
} = useRequest(Transfer.list(), {
  initialData: []
})
const { send: destroy, onSuccess: onDestroySuccess } = useRequest(() => Transfer.destroy(transfer.value), {
  immediate: false,
  middleware: middlewares($dialog.destroy)
})
onDestroySuccess(refresh)
</script>

<template>
  <ComCtl content-class="flex flex-col gap-2" :loading="loading">
    <VCard title="操作面板">
      <div class="font-semibold">
        <div>订单总数: {{ transfers.length }}</div>
      </div>
    </VCard>
    <VTable :columns="columns" :data="transfers" />
  </ComCtl>
</template>

<style scoped></style>
