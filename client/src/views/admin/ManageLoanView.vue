<script setup lang="ts">
import { Loan } from '@/app/admin/api'
import { LoanOut } from '@/app/admin/models-out'
import { $dialog, middlewares } from '@/app/core/middlewares'
import ComCtl from '@/components/ComCtl.vue'
import { Check as ICheck, Plus as IPlus, TrashCan as ITrashCan } from '@vexip-ui/icons'
import { useRequest } from 'alova'
import { Button as VButton, Card as VCard, Table as VTable, Tag as VTag, defineTableColumns } from 'vexip-ui'
import { h, ref } from 'vue'

const columns = defineTableColumns<LoanOut>([
  {
    type: 'expand',
    key: 'card',
    renderer: ({ row }) => `卡号: ${row.card.number} 用户名: ${row.card.user.username}`
  },
  {
    name: '贷款金额',
    key: 'amount'
  },
  {
    name: '审核状态',
    key: 'active',
    renderer: ({ row }) =>
      row.active
        ? h(VTag, { type: 'success' }, { default: () => '已审批' })
        : h(VTag, { type: 'warning' }, { default: () => '待审批' })
  },
  {
    name: '操作',
    key: 'id',
    renderer: ({ row }) => [
      h(VButton, {
        type: 'error',
        onClick: () => {
          loan.value = row
          destroy()
        },
        icon: ITrashCan,
        text: true
      }),
      h(VButton, {
        type: 'success',
        onClick: () => {
          loan.value = row
          activeLoan()
        },
        icon: ICheck,
        text: true
      })
    ]
  }
])

const loan = ref(LoanOut.$new())
const {
  loading,
  data: loans,
  send: refresh
} = useRequest(Loan.list(), {
  initialData: []
})
const { send: destroy, onSuccess: onDestroySuccess } = useRequest(() => Loan.destroy(loan.value), {
  immediate: false,
  middleware: middlewares($dialog.destroy)
})
onDestroySuccess(refresh)
const { send: activeLoan, onSuccess: onActiveLoanSuccess } = useRequest(() => Loan.activeLoan(loan.value), {
  immediate: false,
  middleware: middlewares($dialog.activeLoan)
})
onActiveLoanSuccess(refresh)
</script>

<template>
  <ComCtl content-class="flex flex-col gap-4" :loading="loading">
    <VCard title="操作面板">
      <template #extra>
        <VButton type="success" :icon="IPlus"></VButton>
      </template>
      <div class="font-semibold">
        <div>贷款总数: {{ loans.length }}</div>
        <div></div>
      </div>
    </VCard>
    <VTable :columns="columns" :data="loans" />
  </ComCtl>
</template>

<style scoped></style>
