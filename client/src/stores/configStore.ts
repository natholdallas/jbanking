import { defineStore } from 'pinia'

export type Cfg = {
  editMode: boolean
  darkMode: boolean
  color: string
  menuReduced: boolean
  avatarCircle: boolean
}

export const useConfigStore = defineStore('config', {
  state: defaultValue,
  actions: {
    reset: () => Object.assign(useConfigStore().$state, defaultValue())
  },
  persist: {
    storage: localStorage
  }
})

function defaultValue(): Cfg {
  return {
    editMode: false,
    darkMode: false,
    color: '',
    menuReduced: false,
    avatarCircle: false
  }
}
