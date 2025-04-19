<script setup lang="ts">
import { Comment } from '@/app/api'
import { $dialog, middlewares } from '@/app/core/middlewares'
import ComCtl from '@/components/ComCtl.vue'
import CommentCreateForm from '@/components/form/CommentCreateForm.vue'
import { Message as IMessage, TrashCan as ITrashCan } from '@vexip-ui/icons'
import { useRequest } from 'alova'
import { Divider, Button as VButton, Card as VCard, Modal as VModal } from 'vexip-ui'
import { ref } from 'vue'

const comment = ref(Comment.$new())
const showCreateModal = ref(false)

const {
  loading: lcomments,
  data: comments,
  send: refresh
} = useRequest(Comment.$list(), {
  initialData: []
})
const {
  loading: ldestroy,
  send: destroy,
  onSuccess: onDestroySuccess
} = useRequest(() => Comment.$destroy(comment.value), {
  immediate: false,
  middleware: middlewares($dialog.destroy)
})
onDestroySuccess(refresh)
</script>

<template>
  <ComCtl content-class="flex flex-col gap-2" :loading="[lcomments, ldestroy]">
    <VCard title="功能提议">
      <template #extra>
        <VButton type="warning" @click="showCreateModal = true" :icon="IMessage">留言</VButton>
      </template>
      <div class="font-semibold">留言数: {{ comments.length }}</div>
      <Divider />
      <div>
        <div>你可以在此处管理自己的功能提议信息</div>
        <div>在你留言之后, 管理员会尽快阅读和处理您的意见</div>
        <div>管理员将会回复您信息, 信息面板位于屏幕右上角, 点击头像即可看到信息面板</div>
      </div>
    </VCard>
    <TransitionGroup name="group-transition" class="flex flex-col gap-2" tag="div">
      <VCard v-for="(i, index) in comments" :key="index" :title="i.title">
        <template #extra>
          <VButton @click=";(comment = i), destroy()" :icon="ITrashCan" color="red" />
        </template>
        <div class="font-semibold">{{ i.content }}</div>
      </VCard>
    </TransitionGroup>
    <template #modals>
      <VModal title="功能建议" v-model:active="showCreateModal" >
        <CommentCreateForm @success="refresh(), (showCreateModal = false)" />
      </VModal>
    </template>
  </ComCtl>
</template>

<style scoped></style>
