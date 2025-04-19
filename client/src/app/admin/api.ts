import { SimpleRequest } from '../core/api'
import type { ReplyIn, UserIn } from './models-in'
import type { CardOut, CommentOut, LoanOut, TransferOut, UserOut } from './models-out'

export class Admin {
  static api = new SimpleRequest('/admin')

  static list() {
    return this.api.get<UserOut[]>()
  }

  static retrieve(id: number) {
    return this.api.get<UserOut>(`/${id}`)
  }

  static create(data: UserIn) {
    return this.api.post(undefined, data)
  }

  static update(data: UserOut) {
    return this.api.put(`/${data.id}`, data)
  }

  static destroyBatch(ids: number[]) {
    return this.api.delete(undefined, ids)
  }

  static destroy(data: UserOut) {
    return this.api.delete(`/${data.id}`)
  }
}

export class Card {
  static api = new SimpleRequest('/admin/card')

  static list() {
    return this.api.get()
  }

  static retrieve(id: number) {
    return this.api.get(`/${id}`)
  }

  static update(data: CardOut) {
    return this.api.put(`/${data.id}`, data)
  }

  static destroyBatch(ids: number[]) {
    return this.api.delete(undefined, ids)
  }

  static destroy(data: CardOut) {
    return this.api.delete(`/${data.id}`)
  }
}

export class Comment {
  static api = new SimpleRequest('/admin/comment')

  static list() {
    return this.api.get<CommentOut[]>()
  }

  static retrieve(id: number) {
    return this.api.get<CommentOut>(`/${id}`)
  }

  static update(data: CommentOut) {
    return this.api.put(`/${data.id}`, data)
  }

  static destroyBatch(ids: number[]) {
    return this.api.delete(undefined, ids)
  }

  static destroy(data: CommentOut) {
    return this.api.delete(`/${data.id}`)
  }
}

export class Loan {
  static api = new SimpleRequest('/admin/loan')

  static list() {
    return this.api.get<LoanOut[]>()
  }

  static retrieve(id: number) {
    return this.api.get<LoanOut>(`/${id}`)
  }

  static activeLoan(data: LoanOut) {
    return this.api.patch(`/${data.id}`, data)
  }

  static destroyBatch(ids: number[]) {
    return this.api.delete(undefined, ids)
  }

  static destroy(data: LoanOut) {
    return this.api.delete(`/${data.id}`)
  }
}

export class Reply {
  static api = new SimpleRequest('/admin/reply')

  static create(data: ReplyIn) {
    return this.api.post(undefined, data)
  }
}

export class Transfer {
  static api = new SimpleRequest('/admin/transfer')

  static list() {
    return this.api.get<TransferOut[]>()
  }

  static retrieve(id: number) {
    return this.api.get(`/${id}`)
  }

  static update(data: TransferOut) {
    return this.api.put(undefined, data)
  }

  static destroyBatch(ids: number[]) {
    return this.api.delete(undefined, ids)
  }

  static destroy(data: TransferOut) {
    return this.api.delete(`/${data.id}`)
  }
}
