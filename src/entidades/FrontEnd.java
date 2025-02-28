package entidades;

import items.ArmaPrincipal;

/**
 * Classe que representa um herói especializado em FrontEnd.
 * Especialista em desenvolver interfaces e experiências de usuário.
 */
public class FrontEnd extends Heroi {
    /**
     * Construtor da classe FrontEnd
     *
     * @param nome          Nome do herói FrontEnd
     * @param vidaMax       Vida máxima do herói
     * @param forca         Força do herói
     * @param nivel         Nível do herói
     * @param ouro          Quantidade de ouro inicial
     * @param armaPrincipal Arma principal do herói
     */
    public FrontEnd(String nome, int vidaMax, int forca, int nivel, int ouro, ArmaPrincipal armaPrincipal) {
        super(nome, vidaMax, forca, nivel, ouro, armaPrincipal);
    }

    public FrontEnd(FrontEnd heroi) {
        super(heroi);
    }

    /**
     * Implementação específica para o FrontEnd Atacar
     */
    @Override
    public void atacar(Entidade alvo) {
        int danoHeroi = this.forca + this.armaPrincipal.getAtaque();
        alvo.receberDano(danoHeroi);
        System.out.println(this.nome + " atacou \uD83D\uDCA2 " + alvo.getNome() + " causando " + danoHeroi + " \uD83E\uDE78 de dano!\n");
    }

    @Override
    public FrontEnd Clone() {
        return new FrontEnd(this);
    }
}
