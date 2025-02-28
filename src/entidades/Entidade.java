package entidades;

import java.util.Arrays;
/**
 * Classe abstrata que representa uma entidade genérica no jogo.
 * Serve como base para todas as entidades vivas no sistema, como heróis e Npcs.
 *
 */
public abstract class Entidade {
    protected String nome;
    protected int vidaMax;
    protected int vidaAtual;
    protected int forca;
    /**
     * Construtor para criar uma nova entidade.
     *
     * @param nome Nome da entidade
     * @param vidaMax Vida máxima da entidade
     * @param forca Força da entidade
     */
    public Entidade(String nome, int vidaMax,  int forca) {
        this.nome = nome;
        this.vidaMax = vidaMax;
        this.vidaAtual = vidaMax;
        this.forca = forca;
    }
    /**
     * Exibe os detalhes da entidade na saída padrão.
     * Mostra o nome, vida atual/máxima e força.
     */
    public void exibirDetalhes() {
        System.out.println("Nome: " + this.nome);
        System.out.println("Vida: " + this.vidaAtual + "/" + this.vidaMax);
        System.out.println("Força: " + this.forca);
    }
    /**
     * Retorna o nome da entidade.
     *
     * @return Nome da entidade
     */
    public String getNome() {
        return nome;
    }
    /**
     * Define um novo valor para a vida atual da entidade.
     *
     * @param vidaAtual Novo valor de vida atual
     */
    public void setVidaAtual(int vidaAtual) {
        this.vidaAtual = vidaAtual;
    }

    /**
     * Cura a vida da entidade de acordo com o valor especificado.
     * Aumenta a vida atual sem ultrapassar a vida máxima.
     *
     * @param cura Quantidade de vida a ser recuperada
     */
    public void curar(int cura){
        this.vidaAtual = this.vidaAtual + cura;
    }
    /**
     * Retorna o valor atual de vida da entidade.
     *
     * @return Vida atual da entidade
     */
    public int getVidaAtual() {
        return vidaAtual;
    }
    /**
     * Retorna o valor de força da entidade.
     *
     * @return Força da entidade
     */
    public int getForca() {
        return forca;
    }

    /**
     * Aplica dano à entidade, reduzindo sua vida atual.
     * Se a vida ficar negativa, define como zero.
     *
     * @param dano Quantidade de dano a ser aplicada
     */
    public void receberDano(int dano) {
        this.vidaAtual -= dano;
        if (this.vidaAtual < 0) {
            this.vidaAtual = 0;
        }
    }

    /**
     * Verifica se a entidade ainda está viva.
     * Uma entidade está viva se sua vida atual for maior que zero.
     *
     * @return true se a entidade estiver viva, false caso contrário
     */
    public boolean estaVivo() {
        return this.vidaAtual > 0;
    }

    /**
     * Método abstrato para realizar um ataque contra outra entidade.
     * Cada subclasse deve implementar sua própria lógica de ataque.
     *
     * @param alvo Entidade que será alvo do ataque
     */
    public abstract void atacar(Entidade alvo);

}
