<script setup lang="ts">
import { Comment } from '@/app/api'
import { $dialog, middlewares } from '@/app/core/middlewares'
import ComCtl from '@/components/ComCtl.vue'
import { TrashCan as ITrashCan } from '@vexip-ui/icons'
import { useRequest } from 'alova'
import { Button as VButton, Card as VCard, Divider as VDivider, Bubble as VBubble, Avatar as VAvatar } from 'vexip-ui'
import { ref } from 'vue'

const comment = ref(Comment.$new())
const {
  loading,
  data: comments,
  send: refresh
} = useRequest(Comment.$list(), {
  initialData: []
})
const { send: destory, onSuccess: onDestroySuccess } = useRequest(() => Comment.$destroy(comment.value), {
  immediate: false,
  middleware: middlewares($dialog.destroy)
})
onDestroySuccess(refresh)
</script>

<template>
  <ComCtl content-class="flex flex-col gap-2" :loading="loading">
    <VCard title="收件箱">
      <div class="font-semibold">邮件: {{ comments.length }}</div>
      <VDivider />
      <div>
        <div>你可以在这里看到管理员回复您的消息</div>
        <div>你可以对此回复消息进行管理</div>
        <div>我们建议您把他归档(已读), 或者你可以直接删除此功能建议</div>
        <div>当然, 如果您不满意此回复, 您可以进一步联系我们</div>
      </div>
    </VCard>
    <TransitionGroup name="group-transition" class="flex flex-col gap-2" tag="div">
      <VCard v-for="i in comments" :key="i.id" :title="i.title">
        <template #extra>
          <VButton
            @click="
              () => {
                comment = i
                destory()
              }
            "
            :icon="ITrashCan"
            type="error"
          />
        </template>
        <VBubble type="info">{{ i.content }}</VBubble>
        <div v-for="ri in i.replies" class="flex flex-row-reverse gap-2 ml-auto w-full" :key="ri.id">
          <VAvatar circle>{{ ri.user.username }}</VAvatar>
          <VBubble type="success" placement="left">{{ ri.content }}</VBubble>
        </div>
      </VCard>
    </TransitionGroup>
  </ComCtl>
</template>

<style scoped></style>
