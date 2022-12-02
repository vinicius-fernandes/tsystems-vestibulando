export default interface ITokenDecoded {
  exp: number,
  user_name: string,
  authorities: string[],
  jti: string,
  client_id: string,
  scope: string[],
  userFullName: string,
  userId: number
}
