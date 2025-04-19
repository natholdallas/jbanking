import { cloneDeep } from 'lodash'
import type { Rule } from 'vexip-ui'

type NonFunctionPropertyNames<T> = {
  [K in keyof T]: T[K] extends Function ? never : K
}[keyof T]

type NonFunctionProperties<T> = Pick<T, NonFunctionPropertyNames<T>>

export class Model {
  static $validations?: Record<string, Rule[] | Rule>

  static $new<T extends Model>(this: new () => T, data?: Partial<NonFunctionProperties<T>>) {
    const model = new this()
    Object.assign(model, data)
    return model
  }

  static $copy<T>(value: T): T {
    return cloneDeep(value)
  }
}
