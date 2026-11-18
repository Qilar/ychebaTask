package cryptoAnalizatorTask;

import java.io.IOException;
import java.util.Scanner;

public class Action {

    private static final String SLOVAR_PATH =
            "D:\\Projects\\ychebaTask\\src\\cryptoAnalizatorTask\\SlovarikDlyaAnalizatora";

    // Читает целое число, пока пользователь не введёт именно число
    public static int rInt(Scanner s) {
        while (true) {
            String stroka = s.nextLine().trim();
            try {
                return Integer.parseInt(stroka);
            } catch (NumberFormatException e) {
                System.out.println("Это не число. Введи целое число ещё раз:");
            }
        }
    }

    // Спрашивает текст: ручной ввод (можно много строк, с пустыми) или файл
    private static String sprositTekst(Scanner s) throws IOException {
        System.out.println("Введи 1 — если напишешь текст сам, или 2 — если возьмёшь его из файла:");
        int v = rInt(s);
        if (v == 1) {
            System.out.println("Пиши текст. Пустые строки сохраняются как часть текста.");
            System.out.println("Чтобы закончить ввод — напиши на отдельной строке /end и нажми Enter:");
            StringBuilder sb = new StringBuilder();
            while (true) {
                String stroka = s.nextLine();
                if (stroka.trim().equalsIgnoreCase("/end")) {
                    break;
                }
                sb.append(stroka).append("\n");
            }
            return sb.toString();
        } else if (v == 2) {
            System.out.println("Введи путь к файлу:");
            return FileManager.chtenie(s.nextLine());
        }
        System.out.println("Такого варианта нет, беру пустую строку");
        return "";
    }

    // Спрашивает путь для сохранения и пишет результат в файл
    private static void sohranitVFile(Scanner s, String result) throws IOException {
        System.out.println("Введи путь к файлу, в который сохранить:");
        String path = s.nextLine();
        FileManager.zapis(path, result);
        System.out.println("Готово! Результат сохранён в " + path);
    }

    // После операции: посмотреть результат, сохранить в файл или продолжить
    private static String posleOperacii(Scanner s, String result) throws IOException {
        System.out.println("Вот что получилось:\n" + result);
        System.out.println("Введи 1 — чтобы сохранить результат в файл, или 2 — чтобы продолжить работать с этим текстом:");
        int v = rInt(s);
        if (v == 1) {
            sohranitVFile(s, result);
        }
        return result;
    }

    public static void start() throws IOException {
        Scanner s = new Scanner(System.in);
        String tekst = null; // текущий текст, с которым работаем

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║       ШИФРОВАТЕЛЬ ЦЕЗАРЯ  v1.0           ║");
        System.out.println("║  твой личный взломщик древних шифров     ║");
        System.out.println("╚══════════════════════════════════════════╝");

        while (true) {
            System.out.println();
            System.out.println("========= ГЛАВНОЕ МЕНЮ =========");
            System.out.println("Введи 1 — чтобы зашифровать текст");
            System.out.println("Введи 2 — чтобы расшифровать текст");
            System.out.println("Введи 3 — чтобы взломать ключ");
            System.out.println("Введи 0 — чтобы выйти из программы");
            int vibor = rInt(s);

            if (vibor == 0) {
                System.out.println("Спасибо за работу! Возвращайся, если понадобится спрятать или открыть секреты.");
                return;

            } else if (vibor == 1) {
                System.out.println("Зашифровать. Откуда взять текст?");
                tekst = sprositTekst(s);
                System.out.println("Введи ключ для шифрования:");
                int klyuch = rInt(s);
                tekst = Hofrovatel.Zahivrovat(tekst, klyuch);
                tekst = posleOperacii(s, tekst);

            } else if (vibor == 2) {
                System.out.println("Расшифровать. Откуда взять текст?");
                tekst = sprositTekst(s);
                System.out.println("Введи ключ расшифровки:");
                int klyuch = rInt(s);
                tekst = Hofrovatel.Rashivrovat(tekst, klyuch);
                tekst = posleOperacii(s, tekst);

            } else if (vibor == 3) {
                System.out.println("Взлом. Выбери способ:");
                System.out.println("Введи 1 — перебором (BruteForce)");
                System.out.println("Введи 2 — по словарю");
                System.out.println("Введи 3 — по статистике");
                int sposob = rInt(s);

                System.out.println("Откуда взять текст для взлома?");
                tekst = sprositTekst(s);

                if (sposob == 1) {
                    System.out.println("Вот варианты твоего ключа, выбери правильный:");
                    Hofrovatel.vzlom(tekst);

                } else if (sposob == 2) {
                    String raw = FileManager.chtenie(SLOVAR_PATH);
                    String[] wordsArray = raw.split(",");
                    java.util.HashSet<String> dictionary = new java.util.HashSet<>();
                    for (String word : wordsArray) {
                        dictionary.add(word.trim().toLowerCase());
                    }
                    System.out.println("[Отладка] Загружено слов в словарь: " + dictionary.size());
                    Hofrovatel.vzlomSlovarem(tekst, dictionary);

                } else if (sposob == 3) {
                    Hofrovatel.vzlomStatistike(tekst);

                } else {
                    System.out.println("Такого способа взлома нет, возвращаюсь в меню");
                    continue;
                }

                // После взлома: расшифровываем найденным ключом и продолжаем с текстом
                System.out.println("Введи найденный ключ, чтобы расшифровать текст и продолжить работу (или введи 0 — чтобы вернуться в меню):");
                int klyuch = rInt(s);
                if (klyuch != 0) {
                    tekst = Hofrovatel.Rashivrovat(tekst, klyuch);
                    tekst = posleOperacii(s, tekst);
                }

            } else {
                System.out.println("Такого варианта нет, введи число из списка:");
            }
        }
    }
}
