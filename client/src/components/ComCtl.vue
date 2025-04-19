<script setup lang="ts">
import { isArray } from 'lodash'
import { NativeScroll as VNativeScroll, Spin as VSpin } from 'vexip-ui'

defineProps<{
  contentClass?: string
  loading?: boolean | boolean[]
  scroll?: boolean
}>()
</script>

<template>
  <VSpin
    class="w-full h-full p-4"
    tip="Loading..."
    :delay="200"
    :active="isArray(loading) ? loading.some((x) => x) : loading"
  >
    <VNativeScroll v-if="scroll" width="100%" height="100%">
      <div :class="contentClass">
        <slot></slot>
      </div>
    </VNativeScroll>
    <div v-else :class="contentClass">
      <slot></slot>
    </div>
    <slot name="modals"></slot>
    <slot name="drawers"></slot>
  </VSpin>
</template>

<style scoped></style>
