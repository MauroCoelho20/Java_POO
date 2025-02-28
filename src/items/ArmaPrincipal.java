package items;

import java.util.ArrayList;
/**
 * Classe que representa uma arma principal de um herói no jogo.
 * Estende a classe abstrata {@link ItemHeroi} e adiciona funcionalidades
 * específicas para armas com ataque normal e especial.
 */
public class ArmaPrincipal extends ItemHeroi {
    private int ataque;
    private int ataqueEspecial;
    /**
     * Construtor para criar uma nova arma principal.
     *
     * @param nome Nome da arma
     * @param preco Preço da arma em moedas de ouro
     * @param ataque Valor do ataque normal da arma
     * @param ataqueEspecial Valor do ataque especial da arma
     */
    public ArmaPrincipal(String nome, int preco, int ataque, int ataqueEspecial) {
        super(nome, preco);
        this.ataque = ataque;
        this.ataqueEspecial = ataqueEspecial;
    }
    /**
     * Exibe os detalhes da arma principal.
     * Sobrescreve o método da classe pai e adiciona informações
     * específicas sobre ataque normal e especial.
     */
    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("Ataque: " + this.ataque);
        System.out.println("Ataque Especial: " + this.ataqueEspecial);
    }
    /**
     * Retorna o valor do ataque normal da arma.
     *
     * @return Valor do ataque normal
     */
    public int getAtaque() {
        return this.ataque;
    }
    /**
     * Define um novo valor para o ataque especial da arma.
     *
     * @param ataqueEspecial Novo valor de ataque especial
     */
    public void setAtaqueEspecial(int ataqueEspecial) {
        this.ataqueEspecial = ataqueEspecial;
    }
    /**
     * Retorna o valor do ataque especial da arma.
     *
     * @return Valor do ataque especial
     */
    public int getAtaqueEspecial() {
        return this.ataqueEspecial;
    }
}
