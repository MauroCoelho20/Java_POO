package items;

import java.util.ArrayList;
/**
 * Classe abstrata que representa um item consumível no jogo.
 * Estende a classe {@link ItemHeroi} e serve como base para todos
 * os tipos de itens consumíveis no sistema.
 */
public abstract class Consumivel extends ItemHeroi{
    /**
     * Construtor para criar um novo item consumível.
     *
     * @param nome Nome do item consumível
     * @param preco Preço do item em moedas de ouro
     */
    public Consumivel(String nome, int preco) {
        super(nome, preco);
    }
}
