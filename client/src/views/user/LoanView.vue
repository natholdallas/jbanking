<script setup lang="ts">
import { Loan } from '@/app/api'
import { $dialog, middlewares } from '@/app/core/middlewares'
import { idsOf } from '@/common/utils/vexip-util'
import ComCtl from '@/components/ComCtl.vue'
import LoanCreateForm from '@/components/form/LoanCreateForm.vue'
import { Plus as IPlus } from '@vexip-ui/icons'
import { useRequest } from 'alova'
import { Button as VButton, Card as VCard, Modal as VModal, Table as VTable, Tag as VTag } from 'vexip-ui'
import { defineTableColumns, type TableExposed } from 'vexip-ui/es/components/table/index.mjs'
import { h, ref } from 'vue'

const table = ref<TableExposed>()
const createModal = ref(false)
const columns = defineTableColumns<Loan>([
  {
    type: 'selection',
    disableRow: (row: Loan) => row.active
  },
  {
    name: '贷款卡号',
    renderer: ({ row }) => row.card.id,
    key: 'card'
  },
  {
    name: '贷款银行',
    renderer: ({ row }) => row.card.name,
    key: 'card'
  },
  {
    name: '借款额度',
    key: 'amount'
  },
  {
    name: '审批状态',
    renderer: ({ row }) =>
      row.active
        ? h(VTag, { type: 'success' }, { default: () => '已审批' })
        : h(VTag, { type: 'warning' }, { default: () => '待审批' }),
    key: 'active'
  }
])

const {
  loading: loading,
  data: loans,
  send: refresh
} = useRequest(Loan.$list(), {
  initialData: []
})
const { send: destory, onSuccess: onDestroySuccess } = useRequest(() => Loan.$destroyBatch(idsOf(table.value)), {
  immediate: false,
  middleware: middlewares($dialog.destroy)
})
onDestroySuccess(refresh)
</script>

<template>
  <ComCtl content-class="flex flex-col gap-2" :loading="[loading]">
    <VCard title="数据面板">
      <template #extra>
        <VButton v-if="table?.getSelected().length" type="error" @click="destory">撤销请求</VButton>
        <VButton type="success" @click="createModal = true" :icon="IPlus">新建</VButton>
      </template>
      <div class="font-semibold">贷款请求: {{ loans.length }}</div>
      <div>
        <div>2021年3月31日, 中国人民银行发布公告, 所有贷款产品均应明示贷款年化利率</div>
        <div>如果他们没有明确告诉你借款的“年利率”, 你可以要求他们提供</div>
        <div>或者向市场利率定价自律机制或金融消费者权益保护部门反映维护自身权益</div>
      </div>
    </VCard>
    <VTable ref="table" :loading="loading" :columns="columns" :data="loans" />
    <template #modals>
      <VModal title="申请贷款" v-model:active="createModal">
        <LoanCreateForm
          @success="
            () => {
              refresh()
              createModal = false
            }
          "
        />
      </VModal>
    </template>
  </ComCtl>
</template>

<style scoped></style>
