
export const environment = {
  production: false,
  apiUrl: process.env["API_URL"]? process.env["API_URL"]: "http://localhost:8080",
  OAUTH_CLIENT: 'tsystems',
  OAUTH_SECRET: 'tsystems123'
}
