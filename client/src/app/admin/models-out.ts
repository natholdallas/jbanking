import { Model } from '../core/models'
import { Permission } from '../models-out'
import { validateLength, validateRange } from '../validator'

export class UserOut extends Model {
  readonly id: number = -1
  username: string = ''
  idName: string = ''
  idCard: string = ''
  permission: Permission = Permission.USER

  static $validations = {
    username: validateRange(4, 20),
    password: validateRange(4, 20),
    idName: validateRange(1, 10),
    idCard: validateLength(18)
  }
}

export class CardOut extends Model {
  id: number = -1
  user: UserOut = UserOut.$new()
  number: string = ''
  name: string = ''
  balance: string = ''
}

export class CommentOut extends Model {
  id: number = -1
  user: UserOut = UserOut.$new()
  title: string = ''
  content: string = ''
  replies: ReplyOut[] = []
}

export class ReplyOut extends Model {
  id: number = -1
  user: UserOut = UserOut.$new()
  content: string = ''
}

export class LoanOut extends Model {
  id: number = -1
  card: CardOut = CardOut.$new()
  amount: string = ''
  active: boolean = false
}

export class TransferOut extends Model {
  id: number = -1
  card: CardOut = CardOut.$new()
  target: number = -1
  amount: string = ''
}
