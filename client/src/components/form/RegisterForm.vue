<script setup lang="ts">
import { Account } from '@/app/api'
import { RegisterIn } from '@/app/models-in'
import { Lock as ILock, User as IUser } from '@vexip-ui/icons'
import { useRequest } from 'alova'
import { Form, FormItem, FormSubmit, Input, Message } from 'vexip-ui'
import { reactive } from 'vue'

const model = reactive(RegisterIn.$new())
const { loading, send, onSuccess } = useRequest(Account.$register(model), {
  immediate: false
})
onSuccess(() => {
  Message.success({ content: '注册成功, 请前往登录界面', duration: 5000 })
  Object.assign(model, RegisterIn.$new())
})
</script>

<template>
  <Form :model="model" :rules="RegisterIn.$validations" :loading="loading" all-required>
    <FormItem label="用户名" prop="username">
      <Input :prefix="IUser" :max-length="20" />
    </FormItem>
    <FormItem label="密码" prop="password">
      <Input type="password" :prefix="ILock" :max-length="20" plain-password />
    </FormItem>
    <FormItem label="确认密码" prop="confirmPassword" required>
      <Input type="password" :prefix="ILock" :max-length="20" plain-password />
    </FormItem>
    <FormItem action>
      <FormSubmit label="注册" @submit="send" block />
    </FormItem>
  </Form>
</template>

<style scoped></style>
