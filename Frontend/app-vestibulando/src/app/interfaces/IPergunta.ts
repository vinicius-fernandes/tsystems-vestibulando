import IBanca from "./IBanca";
import IGerarSimulado from "./IGerarSimulado";
import IMateria from "./IMateria";
import IResposta from "./IResposta";

export default interface IPergunta {
    id?: number,
    corpo: string,
    materias: IMateria,
    bancas: IBanca,
    respostas?: IResposta[]
}
  