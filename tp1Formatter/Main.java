
import java.util.stream.*;
import java.nio.file.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

  static File text;
  static FileWriter myWriter;

  public static void main(String... a) throws IOException {
    text = new File("Result.txt");      // Cria o .txt
    myWriter = new FileWriter(text);
    myWriter.write("\n//Exercício x");
    appendClass();
  }

  static void appendClass() throws IOException {
    // O stream basicamente procura por todos os arquivos que contem .java na pasta destino
    try (Stream<Path> paths = Files.walk(Paths.get("."))) {
      paths.filter(Files::isRegularFile).filter(x -> x.getFileName().toString().contains(".java")).forEach(x -> {     
        try {
          Scanner sc = new Scanner(x.toFile());   // Voce pode utilizar o scanner para receber o que esta nos arquivos, existem modulos especificos para tal tbm.
          sc.useDelimiter("\\Z");     // creio que este regex seja para o final do arquivo...
          myWriter.append("\n\n//Classe " + x.getFileName().toString().substring(0, x.getFileName().toString().length()-5)+ "\n\n"); // formata da maneira que a prof quer
          myWriter.append(sc.next());   // da append no seu codigo para o .txt
        } catch (Exception f) {
          System.out.println("Erro!" + f);
        }
      });
    } catch (Exception e) {
      System.out.println("Erro!" + e);
    }
    myWriter.close();   // fecha o arquivo muito importante!
  }

}
