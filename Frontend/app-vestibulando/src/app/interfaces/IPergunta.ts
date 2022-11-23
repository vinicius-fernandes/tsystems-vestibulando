import IBanca from "./IBanca";
import IGerarSimulado from "./IGerarSimulado";
import IMateria from "./IMateria";

export default interface IPergunta {
    id?: number,
    corpo: string,
    materias: IMateria,
    bancas: IBanca,
    simulado: IGerarSimulado[]
}
  