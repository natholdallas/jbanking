<script setup lang="ts">
import { Account } from '@/app/api'
import { LoginIn } from '@/app/models-in'
import router from '@/router'
import { urls } from '@/router/urls'
import { Lock as ILock, User as IUser } from '@vexip-ui/icons'
import { useRequest } from 'alova'
import { Form, FormItem, FormSubmit, Input } from 'vexip-ui'
import { reactive } from 'vue'

const model = reactive(LoginIn.$new())
const { loading, send, onSuccess } = useRequest(Account.$login(model), {
  immediate: false
})
onSuccess(() => router.push(urls.home))
</script>

<template>
  <Form :model="model" :rules="LoginIn.$validations" :loading="loading" all-required>
    <FormItem label="用户名" prop="username">
      <Input :prefix="IUser" :max-length="20" />
    </FormItem>
    <FormItem label="密码" prop="password">
      <Input type="password" :prefix="ILock" :max-length="20" plain-password />
    </FormItem>
    <FormItem action>
      <FormSubmit label="登录" @submit="send" block />
    </FormItem>
  </Form>
</template>

<style scoped></style>
