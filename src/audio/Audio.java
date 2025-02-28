package audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
/**
 * Classe responsável pela reprodução de áudio no sistema.
 * Fornece métodos para reproduzir arquivos de som em diferentes modos.
 */
public class Audio {
    /**
     * Reproduz um arquivo de áudio.
     *
     * Este método tenta abrir e reproduzir um arquivo de áudio do caminho especificado.
     * Se o parâmetro loop for verdadeiro, o áudio será reproduzido continuamente.
     * Caso contrário, o áudio será reproduzido apenas uma vez.
     */
    public static void playMusic(String caminho, boolean loop) {
        try { // Bloco try para envolver o código que pode potencialmente lançar uma exceção
            File audio = new File(caminho);
            if (audio.exists()) { // Se encontrar o ficheiro
                // Instanciar objeto do tipo AudioInputStream com o ficheiro áudio passado como parâmetro
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(audio);
                Clip clip = AudioSystem.getClip(); // Instanciar objeto do tipo Clip
                clip.open(audioInput); // Abre o ficheiro

                if (loop) {
                    clip.loop(Clip.LOOP_CONTINUOUSLY); // Repete o som continuamente
                } else {
                    clip.start(); // Toca o som uma vez
                }
            } else { // Se não encontrar o ficheiro
                // NOTA: Aqui poderia inserir uma mensagem a informar o utilizador que o ficheiro de áudio não foi encontrado.
                // Contudo, achei melhor não mostrar nada caso não exista ficheiro.
                // Como existe exceção, o programa não dá erro e continua a correr, mas sem áudio.
            }
        } catch (Exception e) { // Código a ser executado se for detectado erro
            System.out.println("Erro ao reproduzir o áudio: " + e.getMessage());
        }
    }
}