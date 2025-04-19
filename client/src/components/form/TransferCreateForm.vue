<script setup lang="ts">
import { Card, Transfer } from '@/app/api'
import { TransferIn } from '@/app/models-in'
import { useRequest } from 'alova'
import { Button, Form, FormItem, Input, Select } from 'vexip-ui'
import { computed, reactive } from 'vue'

const emit = defineEmits<{
  (e: 'success'): void
}>()

const model = reactive(TransferIn.$new())
const options = computed(() =>
  cards.value.map((x) => ({
    label: `${x.name}-${x.number}`,
    value: x.id
  }))
)

const { data: cards } = useRequest(Card.$list(), {
  initialData: []
})
const { loading, send, onSuccess } = useRequest(() => Transfer.$create(model), {
  immediate: false
})

onSuccess(() => {
  emit('success')
  Object.assign(model, TransferIn.$new())
})
</script>

<template>
  <Form :model="model" :loading="loading" label-align="top">
    <FormItem label="所属银行" prop="card">
      <Select v-model:value="model.card" :options="options"></Select>
    </FormItem>
    <FormItem label="转账给" prop="target">
      <Input :max-length="20" />
    </FormItem>
    <FormItem label="转账金额" prop="amount">
      <Input />
    </FormItem>
    <Button type="info" @click="send" :loading="loading" block>提交</Button>
  </Form>
</template>

<style scoped></style>
