package items;

import entidades.Heroi;
import java.util.ArrayList;
/**
 * Classe abstrata que representa um item genérico que pode ser utilizado por heróis no jogo.
 * Serve como base para todos os tipos de itens disponíveis no sistema.
 */
public abstract class ItemHeroi {
    protected String nome;
    protected int preco;
    protected ArrayList<String> heroispermitidos;
    /**
     * Construtor para criar um novo item de herói.
     *
     * @param nome Nome do item
     * @param preco Preço do item em moedas de ouro
     */
    public ItemHeroi(String nome, int preco) {
        this.nome = nome;
        this.preco = preco;
        this.heroispermitidos = new ArrayList<>();
    }
    /**
     * Exibe os detalhes do item na saída padrão.
     * Mostra o nome, preço e heróis permitidos.
     */
    public void exibirDetalhes() {
        System.out.println(this.nome);
        System.out.println("Preço: " + this.preco + " moedas de ouro");
        System.out.println("Heróis permitidos: " + this.heroispermitidos);

    }
    /**
     * Retorna o preço do item.
     *
     * @return Preço do item em moedas de ouro
     */
    public int getPreco() {
        return this.preco;
    }
    /**
     * Retorna o nome do item.
     *
     * @return Nome do item
     */
    public String getNome() {
        return nome;
    }
    /**
     * Adiciona um tipo de herói à lista de heróis permitidos para usar este item.
     *
     * @param tipoHeroi Tipo de herói a ser adicionado à lista de permissões
     */
    public void addHeroiPermitido(String tipoHeroi) {
        heroispermitidos.add(tipoHeroi);
    }
    /**
     * Verifica se um determinado tipo de herói pode usar este item.
     *
     * @param tipoHeroi Tipo de herói a ser verificado
     * @return true se o herói pode usar o item, false caso contrário
     */
    public boolean podeUsar(String tipoHeroi) {
        return heroispermitidos.contains(tipoHeroi);
    }
}

