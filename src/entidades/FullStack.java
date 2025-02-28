package entidades;

import items.ArmaPrincipal;
/**
 * Classe que representa um herói especializado em FullStack.
 * Um desenvolvedor versátil que domina tanto o FrontEnd quanto o BackEnd.
 */
public class FullStack extends Heroi{
    /**
     * Construtor da classe FullStack
     *
     * @param nome Nome do herói FullStack
     * @param vidaMax Vida máxima do herói
     * @param forca Força do herói
     * @param nivel Nível do herói
     * @param ouro Quantidade de ouro inicial
     * @param armaPrincipal Arma principal do herói
     */
    public FullStack(String nome, int vidaMax, int forca, int nivel, int ouro, ArmaPrincipal armaPrincipal) {
        super(nome, vidaMax, forca, nivel, ouro, armaPrincipal);
    }

    public FullStack(FullStack heroi) {
        super(heroi);
    }

    /**
     * Implementação específica para o FullStack Atacar
      */
    @Override
    public void atacar(Entidade alvo) {
        int danoHeroi = this.forca + this.armaPrincipal.getAtaque();
        alvo.receberDano(danoHeroi);
        System.out.println(this.nome + " atacou \uD83D\uDCA2 " + alvo.getNome() + " causando " + danoHeroi + " \uD83E\uDE78 de dano!\n");

    }

    @Override
    public FullStack Clone() {
        return new FullStack(this);
    }
}
