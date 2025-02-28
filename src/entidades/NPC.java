package entidades;
/**
 * Classe que representa um NPC (Non-Player Character) no jogo.
 * É um inimigo.
 */
public class NPC extends Entidade {
    private int ouro;
    /**
     * Construtor da classe NPC
     *
     * @param nome Nome do NPC
     * @param vidaMax Vida do NPC
     * @param forca Força do NPC
     * @param ouro Quantidade de ouro que o NPC possui
     */
    public NPC(String nome, int vidaMax, int forca, int ouro) {
        super(nome, vidaMax, forca);
        this.ouro = ouro;
    }

    /**
     * Implementação do ataque do NPC
     * @param alvo
     */
    @Override
    public void atacar(Entidade alvo) {
    int dano = this.forca ; // força do NPC

    // TODO alterei a forca do Npc para equilibrar mais o jogo!
    if (alvo instanceof FullStack) { // Verifica se o alvo é uma instância de FullStack
            dano = (int) (this.forca * 1.2); // se for Fullstack o NPC ataca com mais 20%
    }
        System.out.println(this.nome + " atacou \uD83D\uDCA2 " + alvo.getNome());
        System.out.println("Dano causado: \uD83E\uDE78 " + dano);
        alvo.receberDano(dano);
}
    /**
     * Obtém a quantidade de ouro do NPC
     *
     * @return Quantidade de ouro
     */
    public int getOuro() {
        return ouro;
    }

    /**
     * Metodo para modificar a vida do NPC
     * @param i
     */
    public void setVida(int i) {
        this.vidaAtual = i;
    }

    /**
     * Metodo para modificar a forca do NPC
     * @param i
     */
    public void setForca(int i) {
        this.forca = i;
    }
}
