<script setup lang="ts">
import { Card, Loan } from '@/app/api'
import { LoanIn } from '@/app/models-in'
import { useRequest } from 'alova'
import { Form, FormItem, FormSubmit, Input, Select } from 'vexip-ui'
import { computed, reactive } from 'vue'

const emit = defineEmits<{
  (e: 'success'): void
}>()

const model = reactive(LoanIn.$new())
const options = computed(() =>
  cards.value.map((x) => ({
    label: `${x.name}-${x.number}`,
    value: x.id
  }))
)

const { data: cards } = useRequest(Card.$list(), {
  initialData: []
})
const { loading, send, onSuccess } = useRequest(() => Loan.$create(model), {
  immediate: false
})
onSuccess(() => {
  emit('success')
  Object.assign(model, LoanIn.$new())
})
</script>

<template>
  <Form :model="model" :rules="LoanIn.$validations" :loading="loading" all-required>
    <FormItem label="所属银行" prop="card">
      <Select :options="options"></Select>
    </FormItem>
    <FormItem label="贷款数额" prop="amount">
      <Input />
    </FormItem>
    <FormItem action>
      <FormSubmit label="提交" @submit="send" block />
    </FormItem>
  </Form>
</template>

<style scoped></style>
