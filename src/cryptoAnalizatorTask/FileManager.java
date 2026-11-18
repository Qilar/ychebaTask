package cryptoAnalizatorTask;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {
    public static String chtenie(String path) throws IOException {
        File file = new File(path);
        return Files.readString(file.toPath());
    }
    public static  void zapis(String path,String text) throws IOException {
        Files.writeString(Path.of(path), text);
    }
}
