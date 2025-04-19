<script setup lang="ts">
import { Card } from '@/app/api'
import { CardIn } from '@/app/models-in'
import { rowOptions } from '@/common/utils/vexip-util'
import { useRequest } from 'alova'
import { Form, FormItem, FormSubmit, Input, Select } from 'vexip-ui'
import { reactive } from 'vue'

const emit = defineEmits<{
  (e: 'success'): void
}>()

const options = rowOptions([
  '交通银行',
  '中国银行',
  '中国建设银行',
  '中国农业银行',
  '中国工商银行',
  '中国邮政储蓄银行',
  '中国光大银行',
  '中国民生银行',
  '招商银行',
  '中信银行',
  '华夏银行',
  '上海浦东发展银行',
  '平安银行',
  '广发银行',
  '兴业银行',
  '浙商银行',
  '渤海银行',
  '恒丰银行',
  '中国人民银行',
  '国家开发银行',
  '中国进出口银行',
  '中国农业发展银行'
])

const model = reactive(CardIn.$new())
const { loading, send, onSuccess } = useRequest(() => Card.$create(model), {
  immediate: false
})
onSuccess(() => emit('success'))
</script>

<template>
  <Form :model="model" :rules="CardIn.$validations" :loading="loading" all-required>
    <FormItem label="卡号" prop="number">
      <Input type="password" plain-password />
    </FormItem>
    <FormItem label="所属银行" prop="name">
      <Select v-model:label="model.name" :options="options"></Select>
    </FormItem>
    <FormItem action>
      <FormSubmit label="提交" @submit="send" block />
    </FormItem>
  </Form>
</template>

<style scoped></style>
