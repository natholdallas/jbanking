export function isBigInt(value: string | number | bigint | boolean) {
  try {
    BigInt(value)
    return true
  } catch {
    return false
  }
}
