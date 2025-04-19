<script setup lang="ts">
import props from '@/common/vexip.config'
import { ConfigProvider } from 'vexip-ui'
import { useConfigStore } from './stores/configStore'

const configStore = useConfigStore()
</script>

<template>
  <ConfigProvider :props="props" :theme="configStore.darkMode ? 'dark' : 'light'">
    <RouterView v-slot="{ Component }">
      <Transition name="vxp-fade">
        <component :is="Component" />
      </Transition>
    </RouterView>
  </ConfigProvider>
</template>

<style scoped></style>
