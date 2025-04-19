<script setup lang="ts">
import { Comment } from '@/app/api'
import { CommentIn } from '@/app/models-in'
import { useRequest } from 'alova'
import { Form, FormItem, FormSubmit, Input, Textarea } from 'vexip-ui'
import { reactive } from 'vue'

const emit = defineEmits<{
  (e: 'success'): void
}>()

const model = reactive(CommentIn.$new())
const { loading, send, onSuccess } = useRequest(() => Comment.$create(model), {
  immediate: false
})
onSuccess(() => {
  emit('success')
  Object.assign(model, CommentIn.$new())
})
</script>

<template>
  <Form :model="model" :rules="CommentIn.$validations" :loading="loading" all-required>
    <FormItem label="标题" prop="title">
      <Input :max-length="20" />
    </FormItem>
    <FormItem label="内容" prop="content">
      <Textarea :max-length="200"></Textarea>
    </FormItem>
    <FormItem action>
      <FormSubmit label="提交" @submit="send" block />
    </FormItem>
  </Form>
</template>

<style scoped></style>
