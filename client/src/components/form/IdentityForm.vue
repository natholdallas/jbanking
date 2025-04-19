<script setup lang="ts">
import { User } from '@/app/api'
import { IdentityIn } from '@/app/models-in'
import { useRequest } from 'alova'
import { Form, FormItem, FormSubmit, Input } from 'vexip-ui'
import { reactive } from 'vue'

const emit = defineEmits<{
  (e: 'success', data: IdentityIn): void
}>()

const model = reactive(IdentityIn.$new())
const { loading, send, onSuccess } = useRequest(() => User.$identity(model), {
  immediate: false
})
onSuccess(() => {
  emit('success', model)
  Object.assign(model, IdentityIn.$new())
})
</script>

<template>
  <Form :model="model" :rules="IdentityIn.$validations" :loading="loading" all-required>
    <FormItem label="姓名" prop="idName">
      <Input :max-length="10" />
    </FormItem>
    <FormItem label="身份证号" prop="idCard">
      <Input :max-length="18" />
    </FormItem>
    <FormItem action>
      <FormSubmit label="提交" @submit="send" block />
    </FormItem>
  </Form>
</template>

<style scoped></style>
