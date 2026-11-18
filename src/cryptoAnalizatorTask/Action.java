package cryptoAnalizatorTask;

import java.io.IOException;
import java.util.Scanner;

public class Action {
    public static int rInt(Scanner s) {

        int num = s.nextInt();
        s.nextLine();
        return num;
    }

    public static void start() throws IOException {
        String text = null;
        String path = null;
        String file = null;
        int vibor = 0;
        int sdvid = 0;
        Scanner s = new Scanner(System.in);
        System.out.println("Привет тебя приветствует шифрователь цезаря!!!");
        System.out.println("Размер нашего алфавита и символов для шифрования - " + Alphabet.size());
        System.out.println("Например символ Р находится на " + Alphabet.getIndex('р') + " месте!");
        System.out.println("А на тридцатом месте находится символ " + Alphabet.getChar(30));
        System.out.println(Alphabet.getIndex('X') + "  а минус один означает что такого символа не обнаружено");
        System.out.println("Чтобы начать введи 1 или введи 0 для выхода из программы");
        vibor = rInt(s);

        if (vibor == 0) {
            System.out.println("До свидания, ждем вас снова!");
            System.exit(0);
        } else if (vibor == 1) {
            System.out.println("Что ты хочешь сделать, зашифровать текст или расшифровать его?");
            System.out.println("Если хочешь зашифровать то введи 1 если расшифровать то введи 2 если хочешь взломать ключ введи 3:");
            vibor = rInt(s);
            if (vibor == 1) {
                System.out.println("Ты хочешь зашифровать свой текст или возьмем его из файла?");
                System.out.println("1- свой");
                System.out.println("2- из файла");
                vibor = rInt(s);
                if (vibor == 1) {
                    System.out.println("Введите текст который вы хотите зашифровать:");
                    text = s.nextLine();
                    System.out.println("Введите ключ для шифрования.");
                    sdvid = rInt(s);
                    file = Hofrovatel.Zahivrovat(text, sdvid);
                    System.out.println("Поздравляю ваш текст успешно зашифрован что вы хотите с ним сделать сохранить в файл или посмотреть его?");
                    System.out.println("Введите 1 чтобы посмотреть");
                    System.out.println("Введите 2 чтобы сохранить в файл");
                    vibor = rInt(s);
                    if (vibor == 1) {
                        System.out.println(file);
                    } else if (vibor == 2) {
                        System.out.println("Укажите путь к фалу который в который хотите сохранить текст");
                        path = s.nextLine();
                        FileManager.zapis(path, file);
                    }
                } else if (vibor == 2) {
                    System.out.println("Введите путь к фалу:");
                    path = s.nextLine();
                    System.out.println("Введите ключ");
                    sdvid = rInt(s);
                    file = Hofrovatel.Zahivrovat(FileManager.chtenie(path), sdvid);
                    System.out.println("Ваш зашифрованный текст \"" + file + "\"");
                    System.out.println("Хотите сохранить его в файл 1- да 2- нет");
                    vibor = rInt(s);
                    if (vibor == 1) {
                        System.out.println("Введите путь к фалу в котором хотите сохранить текст");
                        path = s.nextLine();
                        FileManager.zapis(path, file);
                        System.out.println("Данные успешно сохранены в файл вот его содержимое \"" + file + "\"");
                    } else if (vibor == 2) {
                        System.out.println("Спасибо до свидания!");
                        System.exit(0);
                    }
                }

            } else if (vibor == 2) {
                System.out.println("Ты хочешь расшифровать свой текст или возьмем его из файла?");
                System.out.println("1- свой");
                System.out.println("2- из файла");
                vibor = rInt(s);
                if (vibor == 1) {
                    System.out.println("Введите текст:");
                    text = s.nextLine();
                    System.out.println("Введите ключ:");
                    sdvid = rInt(s);
                    file = Hofrovatel.Rashivrovat(text, sdvid);
                    System.out.println("Ваш расшифрованный текст \"" + file + "\"");
                } else if (vibor == 2) {
                    System.out.println("Введите путь к файлу");
                    path = s.nextLine();
                    System.out.println("Введите ключ");
                    sdvid = rInt(s);
                    file = Hofrovatel.Rashivrovat(FileManager.chtenie(path), sdvid);
                    System.out.println("Ваш расшифрованный текст \"" + file + "\"");
                    System.out.println("Хотите сохранить его в файл? 1- да 2 нет");
                    vibor = rInt(s);
                    if (vibor == 1) {
                        System.out.println("Введите путь к файлу");
                        path = s.nextLine();
                        FileManager.zapis(path, file);
                        System.out.println("Данные успешно сохранены в файл вот его содержимое \"" + file + "\"");
                    } else {
                        System.exit(0);
                    }
                }
            } else if (vibor == 3) {
                System.out.println("Как будем ломать ключь?");
                System.out.println("1-Перебор(BruteForce)");
                System.out.println("2-По словарю.");
                System.out.println("3-По статистике.");
                vibor = rInt(s);
                if (vibor == 1) {

                    System.out.println("Ты хочешь ввести текст или взять его из файла?");
                    System.out.println("1-написать");
                    System.out.println("2-взять из файла");
                    vibor = rInt(s);
                    if (vibor == 1) {
                        System.out.println("Напиши текст");
                        text = s.nextLine();
                        System.out.println("Вот варианты твоего ключа выбери правильный");
                        Hofrovatel.vzlom(text);
                    } else if (vibor == 2) {
                        System.out.println("Введи путь к файлу");
                        path = s.nextLine();
                        System.out.println("Вот варианты твоего ключа выбери правильный");
                        Hofrovatel.vzlom(FileManager.chtenie(path));
                    }
                } else if (vibor == 2) {
                    System.out.println("Ты хочешь ввести текст или взять его из файла?");
                    System.out.println("1 — написать самому");
                    System.out.println("2 — из файла");
                    vibor = rInt(s);
                    if (vibor == 1) {
                        System.out.println("Напиши текст для взлома:");
                        text = s.nextLine();
                    } else if (vibor == 2) {
                        System.out.println("Введи путь к файлу:");
                        path = s.nextLine();
                        text = FileManager.chtenie(path);
                    }
                    String raw = FileManager.chtenie("D:\\Projects\\ychebaTask\\src\\cryptoAnalizatorTask\\SlovarikDlyaAnalizatora");
                    String[] wordsArray = raw.split(",");
                    java.util.HashSet<String> dictionary = new java.util.HashSet<>();
                    for (String word : wordsArray) {
                        dictionary.add(word.trim().toLowerCase());
                    }
                    System.out.println("[Отладка] Загружено слов в словарь: " + dictionary.size());
                    Hofrovatel.vzlomSlovarem(text, dictionary);
                }
            }
        }
    }
}

