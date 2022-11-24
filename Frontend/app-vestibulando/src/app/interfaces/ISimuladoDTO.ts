import IBanca from "./IBanca";
import IMateria from "./IMateria";
import IPerguntaDTO from "./IPerguntaDTO";

export default interface ISimuladoDTO{
  id:number,
  bancas:IBanca[],
  materias:IMateria[],
  perguntas:IPerguntaDTO[]
}
