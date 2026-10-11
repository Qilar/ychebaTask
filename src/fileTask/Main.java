package fileTask;

import java.io.*;
import java.nio.file.*;


public class Main {
    public static void main(String[] args) {
       try(BufferedWriter bw = Files.newBufferedWriter(Path.of("Hello.txt"))){
           bw.write("Привет, мир!");
       }catch (IOException e){
           e.printStackTrace();
       }

       try(BufferedReader br = Files.newBufferedReader(Path.of("Hello.txt"))) {
           String text = br.readLine();
           System.out.println(text);
       }catch (IOException e){
           e.printStackTrace();
       }
    }
}
