import { validateEmail as $validateEmail, validateUrl as $validateUrl, type Rule } from 'vexip-ui'

export function validateEmail(): Rule {
  return {
    type: 'string',
    validator: (value: string) => $validateEmail(value),
    message: '非法的邮箱格式'
  }
}

export function validatePassword(): Rule {
  return {
    type: 'string',
    validator: (value: string) => /^(?=.*[a-zA-Z])(?=.*\d).{4,20}$/.test(value),
    message: `必须包含数字和字母 ${/^(?=.*[a-zA-Z])(?=.*\d).{4,20}$/}`
  }
}

export function validateLength(length: number): Rule {
  return {
    type: 'string',
    length: length,
    message: `长度必须为 ${length} 位`
  }
}

export function validateRange(min: number, max: number): Rule {
  return {
    type: 'string',
    range: [min, max],
    message: `长度在 ${min} 到 ${max} 个字符之间`
  }
}

export function validateEquals(field: string): Rule {
  return {
    type: 'string',
    validator: (value: string, model: Record<string, any>) => value === model[field],
    message: '输入的密码和确认密码不符合'
  }
}

export function validateUrl(): Rule {
  return {
    type: 'string',
    validator: (value: string) => (value ? $validateUrl(value) : true),
    message: '非法的网址格式'
  }
}

export function validateBigInt(): Rule {
  return {
    type: 'string',
    validator: (value: string) => {
      try {
        BigInt(value)
        return true
      } catch {
        return false
      }
    },
    message: '不是整数'
  }
}
