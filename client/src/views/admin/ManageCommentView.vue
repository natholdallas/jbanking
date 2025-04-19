<script setup lang="ts">
import { Comment } from '@/app/admin/api'
import { CommentOut } from '@/app/admin/models-out'
import { $dialog, middlewares } from '@/app/core/middlewares'
import ComCtl from '@/components/ComCtl.vue'
import ReplyCreationForm from '@/components/form/admin/ReplyCreationForm.vue'
import { Plus as IPlus, TrashCan as ITrashCan } from '@vexip-ui/icons'
import { useRequest } from 'alova'
import { Avatar, Bubble, Button as VButton, Card as VCard } from 'vexip-ui'
import { ref } from 'vue'

const createModal = ref(false)

const comment = ref(CommentOut.$new())
const {
  loading,
  data: comments,
  send: refresh
} = useRequest(Comment.list(), {
  initialData: []
})
const { send: destroy, onSuccess: onDestroySuccess } = useRequest(() => Comment.destroy(comment.value), {
  immediate: false,
  middleware: middlewares($dialog.destroy)
})
onDestroySuccess(refresh)
</script>

<template>
  <ComCtl content-class="flex flex-col gap-2" :loading="loading" scroll>
    <VCard title="功能提议">
      <div class="font-semibold">留言数: {{ comments.length }}</div>
    </VCard>
    <TransitionGroup name="group-transition" class="flex flex-col gap-2" tag="div">
      <VCard v-for="i in comments" :key="i.id">
        <template #title>{{ i.title }} | {{ i.user.username }}</template>
        <template #extra>
          <VButton
            @click="
              () => {
                comment = i
                destroy()
              }
            "
            :icon="ITrashCan"
            color="red"
          />
          <VButton
            @click="
              () => {
                comment = i
                createModal = !createModal
              }
            "
            :icon="IPlus"
          />
        </template>
        <div>
          <Avatar circle>{{ i.user.username }}</Avatar>
          <Bubble type="info">{{ i.content }}</Bubble>
        </div>
        <div class="flex flex-col gap-2">
          <div v-for="ri in i.replies" class="flex flex-row-reverse" :key="ri.id">
            <Avatar circle>{{ ri.user.username }}</Avatar>
            <Bubble type="success" placement="left">{{ ri.content }}</Bubble>
          </div>
        </div>
        <ReplyCreationForm
          v-if="createModal && comment.id === i.id"
          class="mt-4"
          :comment-id="i.id"
          @success="
            () => {
              refresh()
              createModal = false
            }
          "
        />
      </VCard>
    </TransitionGroup>
  </ComCtl>
</template>

<style scoped></style>
