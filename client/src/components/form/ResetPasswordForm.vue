<script setup lang="ts">
import { User } from '@/app/api'
import { PasswordIn } from '@/app/models-in'
import { useRequest } from 'alova'
import { Button, Form, FormItem, Input } from 'vexip-ui'
import { reactive } from 'vue'

const emit = defineEmits<{
  (e: 'success'): void
}>()

const model = reactive(PasswordIn.$new())

const { loading, send, onSuccess } = useRequest(() => User.$password(model), {
  immediate: false
})

onSuccess(() => {
  emit('success')
  Object.assign(model, PasswordIn.$new())
})
</script>

<template>
  <Form :model="model" :rules="PasswordIn.$validations" :loading="loading" label-align="top">
    <FormItem label="密码" prop="password">
      <Input type="password" :max-length="20" plain-password />
    </FormItem>
    <FormItem label="确认密码" prop="confirmPassword">
      <Input type="password" :max-length="20" plain-password />
    </FormItem>
    <Button type="info" @click="send" :loading="loading" block>提交</Button>
  </Form>
</template>

<style scoped></style>
