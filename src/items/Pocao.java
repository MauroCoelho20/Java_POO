package items;

import java.util.ArrayList;
/**
 * Classe que representa uma poção no jogo.
 * Estende a classe abstrata {@link Consumivel} e adiciona funcionalidades
 * específicas para poções que podem curar vida e aumentar força.
 */
public class Pocao extends Consumivel{
    private int cura;
    private int aumentoForca;
    /**
     * Construtor para criar uma nova poção.
     *
     * @param nome Nome da poção
     * @param preco Preço da poção em moedas de ouro
     * @param cura Quantidade de vida que a poção pode curar
     * @param aumentoForca Quantidade de força que a poção pode aumentar
     */
    public Pocao(String nome, int preco, int cura, int aumentoForca) {
        super(nome, preco);
        this.cura = cura;
        this.aumentoForca = aumentoForca;
    }
    /**
     * Exibe os detalhes da poção.
     * Sobrescreve o método da classe pai e adiciona informações
     * específicas sobre cura e aumento de força.
     */
    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("Vida a curar: " + this.cura);
        System.out.println("Aumento de força: " + this.aumentoForca);
    }
    /**
     * Retorna a quantidade de vida que a poção pode curar.
     *
     * @return Quantidade de cura
     */
    public int getCura() {
        return cura;
    }
    /**
     * Retorna a quantidade de força que a poção pode aumentar.
     *
     * @return Quantidade de aumento de força
     */
    public int getAumentoForca() {
        return aumentoForca;
    }
}
