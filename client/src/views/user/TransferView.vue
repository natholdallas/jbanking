<script setup lang="ts">
import { Transfer } from '@/app/api'
import ComCtl from '@/components/ComCtl.vue'
import TransferCreateForm from '@/components/form/TransferCreateForm.vue'
import { Plus as IPlus } from '@vexip-ui/icons'
import { useRequest } from 'alova'
import { Button as VButton, Card as VCard, Modal as VModal, Table as VTable } from 'vexip-ui'
import { defineTableColumns, type TableExposed } from 'vexip-ui/es/components/table/index.mjs'
import { ref } from 'vue'

const table = ref<TableExposed>()
const showCreateModal = ref(false)
const columns = defineTableColumns<Transfer>([
  {
    type: 'selection'
  },
  {
    name: '转账卡号',
    renderer: ({ row }) => row.card.number,
    key: 'card'
  },
  {
    name: '目标账户',
    key: 'target'
  },
  {
    name: '转账金额',
    key: 'amount'
  }
])

const {
  loading,
  data: cards,
  send: refresh
} = useRequest(Transfer.$list(), {
  initialData: []
})
</script>

<template>
  <ComCtl content-class="flex flex-col gap-2" :loading="loading">
    <VCard title="我的卡包">
      <template #extra>
        <VButton type="success" :icon="IPlus">转账</VButton>
      </template>
      <div class="font-semibold">银行卡总数: {{ cards.length }}</div>
    </VCard>
    <VTable ref="table" :loading="loading" :columns="columns" :data="cards" />
    <template #modals>
      <VModal title="绑定银行卡" v-model:active="showCreateModal">
        <TransferCreateForm
          @success="
            () => {
              refresh()
              showCreateModal = false
            }
          "
        />
      </VModal>
    </template>
  </ComCtl>
</template>

<style scoped></style>
