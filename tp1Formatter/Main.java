
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
    text = new File("Result.txt");
    myWriter = new FileWriter(text);
    myWriter.write("\n//Exercício x");
    appendClass();
  }

  static void appendClass() throws IOException {
    try (Stream<Path> paths = Files.walk(Paths.get("."))) {
      paths.filter(Files::isRegularFile).filter(x -> x.getFileName().toString().contains(".java")).forEach(x -> {
        try {
          Scanner sc = new Scanner(x.toFile());
          sc.useDelimiter("\\Z");
          myWriter.append("\n\n//Classe " + x.getFileName().toString().substring(0, x.getFileName().toString().length()-5)+ "\n\n");
          myWriter.append(sc.next());
        } catch (Exception f) {
          System.out.println("Erro!" + f);
        }
      });
    } catch (Exception e) {
      System.out.println("Erro!" + e);
    }
    myWriter.close();
  }

}