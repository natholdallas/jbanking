import { Model } from './core/models'
import { validateBigInt, validateEquals, validateLength, validateRange } from './validator'

export class LoginIn extends Model {
  username: string = ''
  password: string = ''

  static $validations = {
    username: validateRange(4, 20),
    password: validateRange(4, 20)
  }
}

export class RegisterIn extends Model {
  username: string = ''
  password: string = ''
  confirmPassword: string = ''

  static $validations = {
    username: validateRange(4, 20),
    password: validateRange(4, 20),
    confirmPassword: validateEquals('password')
  }
}

export class PasswordIn extends Model {
  password: string = ''
  confirmPassword: string = ''

  static $validations = {
    password: validateRange(4, 20),
    confirmPassword: validateEquals('password')
  }
}

export class IdentityIn extends Model {
  idName: string = ''
  idCard: string = ''

  static $validations = {
    idName: validateRange(1, 10),
    idCard: validateLength(18)
  }
}

export class CardIn extends Model {
  number: string = ''
  name: string = ''

  static $validations = {
    number: [validateBigInt(), validateLength(18)],
    name: validateRange(1, 10)
  }
}

export class CommentIn extends Model {
  title: string = ''
  content: string = ''

  static $validations = {
    title: validateRange(0, 20),
    content: validateRange(0, 50)
  }
}

export class LoanIn extends Model {
  card: string = ''
  amount: string = ''
}

export class TransferIn extends Model {
  card: number = -1
  target: number = -1
  amount: string = ''
}
