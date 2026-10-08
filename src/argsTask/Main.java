package argsTask;


public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Аргументов нет");
        }
        for (int i = 0; i < args.length; i++) {
            System.out.println("Аргумент " + i + ": " + args[i]);
        }


    }
}
