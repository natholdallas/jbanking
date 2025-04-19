<script setup lang="ts">
import { User } from '@/app/api'
import ComCtl from '@/components/ComCtl.vue'
import IdentityForm from '@/components/form/IdentityForm.vue'
import ResetPasswordForm from '@/components/form/ResetPasswordForm.vue'
import { AddressCard as IAddressCard, Lock as ILock } from '@vexip-ui/icons'
import { useRequest } from 'alova'
import { Button as VButton, Card as VCard, Modal as VModal } from 'vexip-ui'
import { ref } from 'vue'

const identityModal = ref(false)
const resetPasswordModal = ref(false)

const {
  loading,
  data: user,
  send: refresh
} = useRequest(User.$retrive(), {
  initialData: User.$new()
})
</script>

<template>
  <ComCtl content-class="grid lg:grid-cols-2 gap-2" :loading="loading">
    <VCard title="用户信息">
      <div class="font-semibold">
        <div>id: {{ user.id }}</div>
        <div>username: {{ user.username }}</div>
      </div>
    </VCard>
    <VCard title="操作面板">
      <VButton v-if="!user.isIdentify" :icon="IAddressCard" @click="identityModal = true">实名认证</VButton>
      <VButton :icon="ILock" @click="resetPasswordModal = true">修改密码</VButton>
    </VCard>
    <template #modals>
      <VModal title="实名认证" v-model:active="identityModal">
        <IdentityForm
          @success="
            () => {
              refresh()
              identityModal = false
            }
          "
        />
      </VModal>
      <VModal title="修改密码" v-model:active="resetPasswordModal">
        <ResetPasswordForm
          @success="
            () => {
              refresh()
              resetPasswordModal = false
            }
          "
        />
      </VModal>
    </template>
  </ComCtl>
</template>

<style scoped></style>
