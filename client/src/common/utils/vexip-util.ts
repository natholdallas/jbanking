import type { User } from '@/app/api'
import type { LayoutUser, TableExposed } from 'vexip-ui'
import { reactive, type UnwrapNestedRefs } from 'vue'

export function convertVexipUser(user: User): UnwrapNestedRefs<LayoutUser> {
  return reactive({
    name: user.username,
    avatar:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQzM0B7psIU6ZqTaQz1p9FCRNwOZeCK6dsPYqEoSXwT0l5DyWYSH_UMl9kP5jQF92110XI&usqp=CAU'
  })
}

export function rowOptions(array: string[]) {
  return array.map((x) => ({
    label: x,
    value: x
  }))
}

export function arrayOf<T = any>(table?: TableExposed): T[] {
  return table?.getSelected() ?? []
}

export function idsOf(table?: TableExposed): number[] {
  return (table?.getSelected() ?? []).map((x) => x.id)
}
