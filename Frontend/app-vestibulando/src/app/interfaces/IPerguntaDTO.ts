import IBanca from './IBanca'
import IMateria from './IMateria'
import IRespostaDTO from './IRespostaDTO'

export default interface IPergunta {
    idPergunta: number,
    corpo: string,
    respostas: IRespostaDTO[],
    banca?: IBanca,
    materia?: IMateria
}
