<script setup lang="ts">
import { Admin } from '@/app/admin/api'
import { UserOut } from '@/app/admin/models-out'
import { Permission } from '@/app/models-out'
import { useRequest } from 'alova'
import { Form, FormItem, FormSubmit, Input, Radio, RadioGroup } from 'vexip-ui'
import { reactive } from 'vue'

const props = defineProps<{
  defaultValue: UserOut
}>()
const emit = defineEmits<{
  (e: 'success'): void
}>()

const model = reactive(UserOut.$copy(props.defaultValue))
const { loading, send, onSuccess } = useRequest(() => Admin.update(model), {
  immediate: false
})
onSuccess(() => emit('success'))
</script>

<template>
  <Form :model="model" :rules="UserOut.$validations" :loading="loading" all-required>
    <FormItem label="权限" prop="permission">
      <RadioGroup>
        <Radio :label="Permission.USER">用户</Radio>
        <Radio :label="Permission.ADMIN">管理员</Radio>
      </RadioGroup>
    </FormItem>
    <FormItem label="用户名" prop="username">
      <Input :max-length="20" />
    </FormItem>
    <FormItem label="姓名" prop="idName">
      <Input :max-length="10" />
    </FormItem>
    <FormItem label="身份证" prop="idCard">
      <Input :max-length="18" />
    </FormItem>
    <FormItem action>
      <FormSubmit label="提交" @submit="send" block />
    </FormItem>
  </Form>
</template>

<style scoped></style>
