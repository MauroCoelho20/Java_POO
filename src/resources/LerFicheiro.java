package resources;

import java.io.*;
import java.util.Scanner;

public class LerFicheiro {

    public static void LerFicheiroTexto(String texto) {
        // Caminho relativo para o ficheiro
        String caminhoFicheiro = "src/resources/" + texto;

        try (Scanner scanner = new Scanner(new File(caminhoFicheiro))) {
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                System.out.println(linha);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado: " + caminhoFicheiro);
            e.printStackTrace();
        }
    }
}