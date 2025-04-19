import router from '@/router'
import { urls } from '@/router/urls'
import type { Method } from 'alova'
import { Notice, Toast } from 'vexip-ui'
import type { InvalidOut } from '../models-out'

export interface Event {
  emit(response: Response, method: Method): Promise<object>
}

export class OkEvent implements Event {
  async emit(response: Response, method: Method) {
    if (method.type !== 'GET') {
      Notice.success({ title: '操作成功' })
    }
    const text = await response.text()
    if (text) return JSON.parse(text)
    return {}
  }
}

export class BadRequestEvent implements Event {
  async emit(response: Response) {
    const json: InvalidOut[] = await response.json()
    if (json.length) {
      Notice.error({
        title: json[0].name,
        content: json[0].message
      })
    } else {
      Notice.error('请求参数有误，详细请查阅控制台输出')
    }
    Toast.error(`错误数量：${json.length}`)
    throw response
    return {}
  }
}

export class UnauthorizedEvent implements Event {
  async emit() {
    router.push(urls.entrance)
    return {}
  }
}

export class ForbiddenEvent implements Event {
  async emit() {
    router.push(urls.forbidden)
    return {}
  }
}

export class InternalServerErrorEvent implements Event {
  async emit(response: Response) {
    Notice.error({
      title: 'Internal Server Error',
      content: '请致电开发人员'
    })
    throw response
    return {}
  }
}
