import IBanca from "./IBanca";
import IMateria from "./IMateria";

export default interface IGerarSimulado {
  numeroPerguntas: number,
  materias: IMateria[],
  bancas: IBanca[]
}
