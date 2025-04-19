import { GenericRequest } from './core/api'
import {
  CardIn,
  CommentIn,
  IdentityIn,
  LoanIn,
  PasswordIn,
  TransferIn,
  type LoginIn,
  type RegisterIn
} from './models-in'
import { CardOut, CommentOut, LoanOut, TransferOut, UserOut } from './models-out'

export class Account {
  static request = new GenericRequest()

  static $register(data: RegisterIn) {
    return this.request.post('/register', data)
  }

  static $login(data: LoginIn) {
    return this.request.post('/login', data)
  }

  static $logout() {
    return this.request.post('/logout')
  }

  static $destroy() {
    return this.request.delete('/account')
  }
}

export class User extends UserOut {
  static request = new GenericRequest<User>('/user')

  static $retrive() {
    return this.request.$retrieve()
  }

  static $identity(data: IdentityIn) {
    return this.request.patch('/identity', data)
  }

  static $password(data: PasswordIn) {
    return this.request.patch('/password', data)
  }
}

export class Card extends CardOut {
  static request = new GenericRequest<Card>('/card')

  static $list() {
    return this.request.$list()
  }

  static $create(data: CardIn) {
    return this.request.$create(undefined, data)
  }

  static $destroyBatch(ids: number[]) {
    return this.request.delete(undefined, ids)
  }

  static $destroy(data: CardOut) {
    return this.request.delete(`/${data.id}`)
  }
}

export class Comment extends CommentOut {
  static request = new GenericRequest<Comment>('/comment')

  static $list() {
    return this.request.$list()
  }

  static $create(data: CommentIn) {
    return this.request.$create(undefined, data)
  }

  static $destroy(data: CommentOut) {
    return this.request.$destroy(`/${data.id}`)
  }
}

export class Loan extends LoanOut {
  static request = new GenericRequest<Loan>('/loan')

  static $list() {
    return this.request.$list()
  }

  static $create(data: LoanIn) {
    return this.request.$create(undefined, data)
  }

  static $destroyBatch(ids: number[]) {
    return this.request.delete(undefined, ids)
  }

  static $destroy(data: LoanOut) {
    return this.request.delete(`/${data.id}`)
  }
}

export class Transfer extends TransferOut {
  static request = new GenericRequest<Transfer>('/transfer')

  static $list() {
    return this.request.$list()
  }

  static $create(data: TransferIn) {
    return this.request.$create(undefined, data)
  }
}
