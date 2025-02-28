package items;

import java.util.ArrayList;
/**
 * Classe que representa um iten consumível de combate no jogo.
 * Estende a classe abstrata {@link Consumivel} e adiciona funcionalidades
 * específicas para itens que proporcionam ataque instantâneo.
 */

public class ConsumivelCombate extends Consumivel {
    private int ataqueInstantaneo;
    /**
     * Construtor para criar um novo consumível de combate.
     *
     * @param nome Nome do item consumível de combate
     * @param preco Preço do item em moedas de ouro
     * @param ataqueInstantaneo Valor do ataque instantâneo proporcionado pelo item
     */
    public ConsumivelCombate(String nome, int preco, int ataqueInstantaneo) {
        super(nome, preco);
        this.ataqueInstantaneo = ataqueInstantaneo;
    }
    /**
     * Exibe os detalhes do item consumível de combate.
     * Sobrescreve o método da classe pai e adiciona informações
     * específicas sobre o ataque instantâneo.
     */
    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("Ataque Instantâneo: " + this.ataqueInstantaneo );
    }
    /**
     * Retorna o valor do ataque instantâneo do item.
     *
     * @return Valor do ataque instantâneo
     */
    public int getAtaqueInstantaneo() {
        return ataqueInstantaneo;
    }


}
