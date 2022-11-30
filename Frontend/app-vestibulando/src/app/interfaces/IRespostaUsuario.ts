import IGeneric from "./IGeneric";

export default interface IRespostaUsuario {
  respostas: IGeneric[],
  simulado: IGeneric,
  usuario: IGeneric
}
