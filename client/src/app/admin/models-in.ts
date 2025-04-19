import { Model } from '../core/models'
import { Permission } from '../models-out'
import { validateRange } from '../validator'

export class UserIn extends Model {
  username: string = ''
  password: string = ''
  permission: Permission = Permission.USER

  static $validations = {
    username: validateRange(4, 20),
    password: validateRange(4, 20)
  } as const
}

export class ReplyIn extends Model {
  comment: number = -1
  content: string = ''

  static $validations = {
    content: validateRange(0, 50)
  } as const
}
