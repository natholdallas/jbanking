import { Model } from './core/models'

export enum Permission {
  USER = 'USER',
  ADMIN = 'ADMIN'
}

export class UserOut extends Model {
  id: number = -1
  username: string = ''
  permission: Permission = Permission.USER
  isIdentify: boolean = false
}

export class CardOut extends Model {
  id: number = -1
  number: string = ''
  name: string = ''
  balance: string = ''
}

export class CommentOut extends Model {
  id: number = -1
  title: string = ''
  content: string = ''
  replies: ReplyOut[] = []
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

export class ReplyOut extends Model {
  id: number = -1
  user: UserOut = UserOut.$new()
  content: string = ''
}

export type InvalidOut = Readonly<{
  name: string
  message: string
}>
