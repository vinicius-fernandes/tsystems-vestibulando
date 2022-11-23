import IPergunta from "./IPergunta";

export default interface IResposta {
    id?: number,
    descricao: string,
    correta: boolean,
    pergunta: IPergunta
}