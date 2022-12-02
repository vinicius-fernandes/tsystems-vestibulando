import IRole from './IRole'
export default interface IUsuario {
    id?: number,
    email: string,
    senha?: string,
    nome: string
    roles?: IRole[]
}
