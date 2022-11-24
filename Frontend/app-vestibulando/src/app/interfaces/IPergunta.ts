import IBanca from "./IBanca";
import IMateria from "./IMateria";
import IResposta from "./IResposta";

export default interface IPergunta {
    id?: number,
    corpo: string,
    materia: IMateria,
    banca: IBanca,
    respostas?: IResposta[]
}
  