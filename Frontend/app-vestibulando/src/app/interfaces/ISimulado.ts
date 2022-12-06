import IMateria from "./IMateria";
import IBanca from "./IBanca";
import IPergunta from "./IPergunta";

export default interface ISimulado {
  id: number,
  materias: IMateria[],
  bancas: IBanca[],
  perguntas: IPergunta[],
  createdAt: number,
  updatedAt: number | null
}
