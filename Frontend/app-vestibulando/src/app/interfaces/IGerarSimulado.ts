import IBanca from "./IBanca";
import IMateria from "./IMateria";

export default interface IGerarSimulado
{
  numeroPaginas: number,
  materias: IMateria[],
  bancas:IBanca[]
}
