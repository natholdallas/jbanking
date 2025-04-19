<script setup lang="ts">
import { Card } from '@/app/api'
import { $dialog, middlewares } from '@/app/core/middlewares'
import { idsOf } from '@/common/utils/vexip-util'
import ComCtl from '@/components/ComCtl.vue'
import CardCreateForm from '@/components/form/CardCreateForm.vue'
import { Plus as IPlus } from '@vexip-ui/icons'
import { useRequest } from 'alova'
import { Button as VButton, Card as VCard, Modal as VModal, Table as VTable } from 'vexip-ui'
import { defineTableColumns, type TableExposed } from 'vexip-ui/es/components/table/index.mjs'
import { ref } from 'vue'

const table = ref<TableExposed>()
const createModal = ref(false)
const columns = defineTableColumns([
  {
    type: 'selection'
  },
  {
    name: '卡号',
    key: 'number'
  },
  {
    name: '所属银行',
    key: 'name'
  },
  {
    name: '余额',
    key: 'balance'
  }
])

const {
  loading,
  data: cards,
  send: refresh
} = useRequest(Card.$list(), {
  initialData: []
})
const { send: destroy, onSuccess } = useRequest(() => Card.$destroyBatch(idsOf(table.value)), {
  immediate: false,
  middleware: middlewares($dialog.destroy)
})
onSuccess(refresh)
</script>

<template>
  <ComCtl content-class="flex flex-col h-full gap-2" :loading="loading">
    <VCard title="我的卡包">
      <template #extra>
        <VButton v-if="table?.getSelected().length" type="error" @click="destroy">删除</VButton>
        <VButton type="success" @click="createModal = true" :icon="IPlus">绑定</VButton>
      </template>
      <div class="font-semibold">银行卡总数: {{ cards.length }}</div>
    </VCard>
    <VTable ref="table" :loading="loading" :columns="columns" :data="cards" />
    <template #modals>
      <VModal v-if="createModal" title="绑定银行卡" v-model:active="createModal">
        <CardCreateForm
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
