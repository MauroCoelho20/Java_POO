package entidades;

import items.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe abstrata que representa um Herói no jogo.
 * Serve como base para todos os tipos de heróis jogáveis.
 * Estende a classe {@link Entidade} e adiciona funcionalidades específicas para personagens
 * controlados pelo jogador, como inventário, ouro e compra de itens.
 */
public abstract class Heroi extends Entidade {
    protected int nivel;
    protected int ouro;
    protected ArmaPrincipal armaPrincipal;
    protected ArrayList<Consumivel> inventario;
    protected boolean ataqueEspecialUsado;

    /**
     * Construtor da classe Heroi
     *
     * @param nome Nome do herói
     * @param vidaMax Vida máxima do herói
     * @param forca Força do herói
     * @param nivel Nível do herói
     * @param ouro Quantidade de ouro inicial
     * @param armaPrincipal Arma principal do herói
     */
    public Heroi(String nome, int vidaMax,  int forca, int nivel, int ouro, ArmaPrincipal armaPrincipal) {
        super(nome, vidaMax, forca);
        this.nivel = nivel;
        this.ouro = ouro;
        this.armaPrincipal = armaPrincipal;
        this.inventario = new ArrayList<>();
        this.ataqueEspecialUsado = false;
    }

    public Heroi(Heroi heroi) {
        super(heroi.nome, heroi.vidaMax, heroi.forca);
        this.nivel = heroi.nivel;
        this.ouro = heroi.ouro;
        this.armaPrincipal = heroi.armaPrincipal;
        this.inventario = new ArrayList<>();
        this.ataqueEspecialUsado = false;
    }

    public abstract Heroi Clone();

    /**
     * Permite ao herói usar um item do inventário.
     * Apresenta uma interface de menu para o jogador escolher qual item consumir.
     * Identifica o tipo de item (Poção ou ConsumivelCombate) e aplica seus efeitos.
     */
    public void usarItem() {
        Scanner in = new Scanner(System.in);
        System.out.print("Quer usar um item do Inventario \uD83D\uDCCB? (S/N)");
        System.out.println(" Só pode usar um Item! Escolha bem!🎯");
        String escolhaUsar = in.nextLine().trim().toUpperCase();

        // Se não quiser usar um item, retorna ao menu anterior
        if (!escolhaUsar.equals("S")) {
            return;
        }

        // Lista todos os itens consumíveis no inventário
        boolean temConsumivel = false;
        for (int i = 0; i < inventario.size(); i++) {
            if (inventario.get(i) instanceof Consumivel) {
                if (inventario.get(i) instanceof Pocao) {
                    Pocao pocao = (Pocao) inventario.get(i);
                    System.out.println((i + 1) + ". 🧪 [Poção] " + pocao.getNome() +
                            " - Cura: ❤️ " + pocao.getCura() +
                            " - Aumento de Força: 💪 " + pocao.getAumentoForca());
                    temConsumivel = true;
                } else if (inventario.get(i) instanceof ConsumivelCombate) {
                    ConsumivelCombate consumivelCombate = (ConsumivelCombate) inventario.get(i);
                    System.out.println((i + 1) + ". 💣 [Consumível de Combate] " + consumivelCombate.getNome() +
                            " - Ataque Especial: ⚔️ " + consumivelCombate.getAtaqueInstantaneo());
                    temConsumivel = true;
                }
            }
        }

        // Verifica se há itens consumíveis no inventário
        if (!temConsumivel) {
            System.out.println("❌ Você não possui itens consumíveis no inventário.");
            return;
        }

        System.out.print("🎮 Digite o número do item (ou 0 para sair): ");
        int escolha = in.nextInt();

        // Verifica se o usuário escolheu sair
        if (escolha == 0) {
            System.out.println("👋 Saindo do menu de itens.");
            return;
        }

        if (escolha > 0 && escolha <= inventario.size()) {
            if (inventario.get(escolha - 1) instanceof Consumivel) {
                Consumivel item = (Consumivel) inventario.get(escolha - 1);

                // Verifica o tipo específico de consumível
                if (item instanceof Pocao) {
                    usarPocao((Pocao) item);
                    System.out.println("🧪 Poção utilizada com sucesso!");
                } else if (item instanceof ConsumivelCombate) {
                    usarConsumivelCombate((ConsumivelCombate) item);
                    System.out.println("💥 Item de combate utilizado com sucesso!");
                } else {
                    System.out.println("❓ Este tipo de consumível não pode ser usado.");
                }
            } else {
                System.out.println("❌ Item selecionado não é consumível.");
            }
        } else {
            System.out.println("⚠️ Escolha inválida.");
        }
    }

    /**
     * Método auxiliar para usar uma poção específica.
     * Aplica os efeitos de cura e aumento de força da poção ao herói.
     * Confirma com o jogador caso a poção possa desperdiçar pontos de vida.
     *
     * @param pocao A poção a ser utilizada
     */
    private void usarPocao(Pocao pocao) {
        // Verifica se a pocao passa a vidaMax e pergunta ao utilizador se quer tomar se passar
        if (this.vidaAtual + pocao.getCura() > this.vidaMax) {
            Scanner in = new Scanner(System.in);
            System.out.println("Usando esta poçao vai desperdiçar Vida!! Quer usar na mesma? (S/N) ⚔️");
            String desperdicar = in.nextLine().toUpperCase();

            if (!desperdicar.equals("S")) {
                return;
            }
        }
        this.vidaAtual += pocao.getCura();
        if (this.vidaAtual > this.vidaMax) {
            this.vidaAtual = this.vidaMax;
        }
        this.forca += pocao.getAumentoForca();
        inventario.remove(pocao);
        System.out.println("Poção " + pocao.getNome() + " usada com sucesso!");
        System.out.println("Vida: " + this.vidaAtual + " | Força: " + this.forca);
    }

    /**
     * Método auxiliar para usar um consumível de combate específico.
     * Aumenta o ataque especial da arma principal do herói com o valor do
     * ataque instantâneo do consumível.
     *
     * @param consumivelCombate O consumível de combate a ser utilizado
     */
    private void usarConsumivelCombate(ConsumivelCombate consumivelCombate) {


        int ataqueEspecialAtual = this.armaPrincipal.getAtaqueEspecial();

        // Adiciona o ataque instantâneo do consumível ao ataque especial da arma
        int novoAtaqueEspecial = ataqueEspecialAtual + consumivelCombate.getAtaqueInstantaneo();

        // Define o novo valor de ataque especial na arma principal
        this.armaPrincipal.setAtaqueEspecial(novoAtaqueEspecial);

        inventario.remove(consumivelCombate);
        System.out.println("Consumível de combate " + consumivelCombate.getNome() + " usado com sucesso!");
        System.out.println("Ataque Especial da arma aumentou de " + ataqueEspecialAtual +
                " para " + novoAtaqueEspecial + " (+" + consumivelCombate.getAtaqueInstantaneo() + ")");
    }

    /**
     * Permite ao herói comprar um item.
     * Verifica se o herói tem ouro suficiente e adiciona o item adequadamente
     * (equipando-o se for uma arma, ou adicionando ao inventário se for consumível).
     *
     * @param item O item a ser comprado
     * @return true se a compra foi realizada com sucesso, false caso contrário
     */
    public boolean comprarItem(ItemHeroi item){
        // Se o ouro for menor que o preço returna falso
        if (this.ouro < item.getPreco()) {
            return false;
        }
        this.ouro -= item.getPreco();
        if (item instanceof ArmaPrincipal) {
            this.armaPrincipal = (ArmaPrincipal) item;
        } else if (item instanceof Pocao) {
            this.inventario.add((Pocao) item);
        } else if (item instanceof Consumivel) {
            this.inventario.add((Consumivel) item);
        }
        System.out.println("\n✅ Item comprado com sucesso! | Adicionado ao inventario \uD83D\uDCDD \n" + item.getNome()+ "\n");
        System.out.println("💰 Ouro restante: " + this.ouro);
        return true;
    }
    /**
     * Obtém a quantidade de ouro do herói.
     *
     * @return Quantidade de ouro atual
     */
    public int getOuro() {
        return ouro;
    }

    /**
     * Aumenta a quantidade de ouro do herói.
     *
     * @param quantidade Quantidade de ouro a ser adicionada
     */
    public void ganharOuro(int quantidade) {
        this.ouro += quantidade;
    }

    /**
     * Aumenta o nível do herói.
     * Incrementa as estatísticas básicas do herói como força e vida máxima.
     */
    public void subirNivel() {
        this.nivel++;
        this.forca += 1;
        this.vidaAtual += 10;
        System.out.println("Você subiu para o nível " + this.nivel + "!");
    }

    /**
     * Metodo abstrato do ataque contra uma entidade alvo.
     *
     *
     * @param alvo A entidade que será atacada
     */
    public abstract void atacar (Entidade alvo);

    /**
     * Usa o ataque especial.
     * O ataque especial só pode ser usado uma vez por batalha.
     *
     * @param alvo A entidade que será atacada.
     */
    public void ataqueEspecial(Entidade alvo) {

        int dano = this.forca + this.armaPrincipal.getAtaqueEspecial();
        System.out.println(this.nome + " usou o ataque especial \uD83D\uDCA5 com " + this.armaPrincipal.getNome() + "!");
        System.out.println("Dano causado: " + dano);
        alvo.receberDano(dano);

    }

    /**
     * Exibe todos os itens no inventário do herói.
     * Lista os detalhes específicos de cada tipo de consumível.
     */
    public void getInventario() {
        // For each para mostrar o inventario
        for (Consumivel item : inventario) {
            if (item instanceof Pocao) {
                Pocao pocao = (Pocao) item;
                System.out.println(pocao.getNome()+":" + " Cura: " + pocao.getCura() + " AumentoForca: " + pocao.getAumentoForca());
            } else if (item instanceof ConsumivelCombate) {
                ConsumivelCombate consumivel = (ConsumivelCombate) item;
                System.out.println(consumivel.getNome()+":" + " Ataque Instantaneo: " +consumivel.getAtaqueInstantaneo());
            }
        }
    }
    /**
     * Retorna a arma principal equipada pelo herói.
     *
     * @return A arma principal atual do herói
     */
    public ArmaPrincipal getArmaPrincipal() {
        return armaPrincipal;
    }

    /**
     * Exibe os detalhes completos do herói na saída padrão.
     * Mostra estatísticas detalhadas incluindo classe, nível, vida, força,
     * ouro e detalhes da arma equipada.
     */
    public void exibirDetalhes() {
        System.out.println("🏆 ===== Detalhes do Herói ===== 🏆");
        System.out.println("👤 Classe: " + this.nome);
        System.out.println("📊 Nível: " + this.nivel);
        System.out.println("❤️ Vida: " + this.vidaAtual + "/" + this.vidaMax);
        System.out.println("💪 Força: " + this.forca);
        System.out.println("💰 Ouro: " + this.ouro);
        System.out.println("⚔️ Arma Principal: " + this.armaPrincipal.getNome());
        System.out.println("  - 🗡️ Ataque Base: " + this.armaPrincipal.getAtaque());
        System.out.println("  - 💥 Ataque Especial: " + this.armaPrincipal.getAtaqueEspecial()+ "\n");

    }

    /**
     * Rouba Ouro ao Heroi, sendo que se a quantidade for maior que o Ouro Atual, retira apenas o Ouro que o jogador tem,
     * não o deixa a negativo.
     * @param quantidade
     * @return Quantidade de Ouro Roubada
     */
    public int rouboOuro(int quantidade) {
        if (this.getOuro() <= 0)
            return 0;

        int ouroRoubado = Math.min(this.getOuro(), quantidade);
        this.ouro -= ouroRoubado;

        return ouroRoubado;
    }
}
