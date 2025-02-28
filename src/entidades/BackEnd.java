package entidades;

import items.ArmaPrincipal;
/**
 * Classe que representa um herói especializado em BackEnd.
 * Especialista em gerenciar servidores, bases de dados e lógica de funcionamento.
 */
public class BackEnd extends Heroi{
    /**
     * Construtor da classe BackEnd
     *
     * @param nome Nome do herói BackEnd
     * @param vidaMax Vida máxima do herói
     * @param forca Força do herói
     * @param nivel Nível do herói
     * @param ouro Quantidade de ouro inicial
     * @param armaPrincipal Arma principal do herói
     */
    public BackEnd(String nome, int vidaMax,  int forca, int nivel, int ouro, ArmaPrincipal armaPrincipal) {
        super(nome, vidaMax,  forca, nivel, ouro, armaPrincipal);
    }

    public BackEnd(BackEnd heroi) {
        super(heroi);
    }

    /**
     * Implementação específica para o BackEnd Atacar
     */
    @Override
    public void atacar(Entidade alvo) {
        int danoHeroi = this.forca + this.armaPrincipal.getAtaque();
        alvo.receberDano(danoHeroi);
        System.out.println(this.nome + " atacou \uD83D\uDCA2 " + alvo.getNome() + " causando " + danoHeroi + " \uD83E\uDE78 de dano!\n");
    }

    @Override
    public BackEnd Clone() {
        return new BackEnd(this);
    }
}
