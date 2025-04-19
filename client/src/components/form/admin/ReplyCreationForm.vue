<script setup lang="ts">
import { Reply } from '@/app/admin/api'
import { ReplyIn } from '@/app/admin/models-in'
import { useRequest } from 'alova'
import { Form, FormItem, FormSubmit, Textarea } from 'vexip-ui'
import { reactive } from 'vue'

const props = defineProps<{
  commentId: number
}>()
const emit = defineEmits<{
  (e: 'success'): void
}>()

const model = reactive(ReplyIn.$new({ comment: props.commentId }))
const { loading, send, onSuccess } = useRequest(() => Reply.create(model), {
  immediate: false
})
onSuccess(() => {
  emit('success')
  Object.assign(model, ReplyIn.$new())
})
</script>

<template>
  <Form :model="model" :rules="ReplyIn.$validations" :loading="loading" all-required>
    <FormItem prop="content">
      <Textarea :max-length="50"></Textarea>
    </FormItem>
    <FormItem action>
      <FormSubmit label="提交" @submit="send" block />
    </FormItem>
  </Form>
</template>

<style scoped></style>
