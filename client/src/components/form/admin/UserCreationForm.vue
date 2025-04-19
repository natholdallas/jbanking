<script setup lang="ts">
import { Admin } from '@/app/admin/api'
import { UserIn } from '@/app/admin/models-in'
import { Permission } from '@/app/models-out'
import { useRequest } from 'alova'
import { Form, FormItem, FormSubmit, Input, Radio, RadioGroup } from 'vexip-ui'
import { reactive } from 'vue'

const emit = defineEmits<{
  (e: 'success'): void
}>()

const model = reactive(UserIn.$new())
const { loading, send, onSuccess } = useRequest(() => Admin.create(model), {
  immediate: false
})
onSuccess(() => {
  emit('success')
  Object.assign(model, UserIn.$new())
})
</script>

<template>
  <Form :model="model" :rules="UserIn.$validations" :loading="loading" all-required>
    <FormItem label="权限" prop="permission">
      <RadioGroup>
        <Radio name="用户" :label="Permission.USER" />
        <Radio name="管理员" :label="Permission.ADMIN" />
      </RadioGroup>
    </FormItem>
    <FormItem label="用户名" prop="username">
      <Input :max-length="20" />
    </FormItem>
    <FormItem label="密码" prop="password">
      <Input type="password" plain-password />
    </FormItem>
    <FormItem action>
      <FormSubmit label="提交" @submit="send" block />
    </FormItem>
  </Form>
</template>

<style scoped></style>
