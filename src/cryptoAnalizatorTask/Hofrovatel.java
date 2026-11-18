package cryptoAnalizatorTask;

import java.util.HashSet;

public class Hofrovatel {

    // Шифрует текст сдвигом Цезаря
    public static String Zahivrovat(String text, int sdvig) {
        text = text.toLowerCase(); // весь текст в нижний регистр
        StringBuilder sb = new StringBuilder(); // сюда собираем результат

        // идём по каждому символу текста
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i); // текущий символ
            int index = Alphabet.getIndex(c); // его номер в алфавите

            if (index == -1) { // символа нет в алфавите (цифра, точка...)
                sb.append(c); // оставляем как есть
            } else {
                int newIndex = (index + sdvig) % Alphabet.size(); // сдвигаем с зацикливанием
                if (newIndex < 0) { // защита от отрицательного сдвига
                    newIndex += Alphabet.size();
                }
                sb.append(Alphabet.getChar(newIndex)); // новый символ в результат
            }
        }

        return sb.toString();
    }

    // Расшифровка = шифрование обратным сдвигом
    public static String Rashivrovat(String text, int sdvig) {
        return Zahivrovat(text, -sdvig);
    }

    // Взлом перебором (brute force): печатает все 100 вариантов, ключ выбирает человек
    public static void vzlom(String text) {
        // перебираем все возможные ключи от 0 до размера алфавита
        for (int i = 0; i < Alphabet.size(); i++) {
            String result = Rashivrovat(text, i); // расшифровываем текст текущим ключом
            System.out.println("Ключ " + i + ": " + result); // показываем вариант на экран
        }
    }
    // правильный ключ среди вывода ищет глазами человек,
    // программа ничего не выбирает сама

    // Взлом по словарю: программа сама находит ключ
    public static void vzlomSlovarem(String text, HashSet<String> dictionary) {
        int bestKey = 0;   // ключ-чемпион
        int bestScore = -1; // его очки (с -1, чтобы любой ключ мог стать первым чемпионом)

        // перебираем все 100 ключей
        for (int key = 0; key < Alphabet.size(); key++) {
            String decrypted = Rashivrovat(text, key); // расшифровка текущим ключом
            String[] words = decrypted.split("[^а-яё]+"); // режем на слова: разделитель — всё, что не русская буква

            int score = 0; // очки текущего ключа

            // проверяем каждое слово
            for (String w : words) {
                // фильтр: слова из 1 буквы не даём очко (они случайно вылезают в любом мусоре)
                if (w.length() > 1 && dictionary.contains(w)) {
                    score++; // слово есть в словаре — очко
                }
            }

            // если счёт лучше рекорда — новый чемпион
            if (score > bestScore) {
                bestScore = score;
                bestKey = key;
            }
        }

        // вывод победителя
        System.out.println("Найден лучший ключ: " + bestKey);
        System.out.println("Совпадений со словарём: " + bestScore);
        System.out.println("Расшифрованный текст:\n" + Rashivrovat(text, bestKey));
    }

    public static void vzlomStatistike(String text) {
        // Шаг 1: считаем, сколько раз встречается каждый символ алфавита в шифровке
        int[] counts = new int[Alphabet.size()];
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);           // текущий символ
            int index = Alphabet.getIndex(c);  // его номер в алфавите
            if (index != -1) {                 // пробелы, запятые и прочее пропускаем
                counts[index]++;
            }
        }

        // Шаг 2: ищем чемпиона — символ с максимальной частотой
        int bestIndex = 0;
        int bestCount = counts[0];
        for (int i = 1; i < counts.length; i++) {
            if (counts[i] > bestCount) {
                bestCount = counts[i];
                bestIndex = i;
            }
        }

        // Шаг 3: защита от коротких текстов — если лидер встречается редко, статистике нечего сказать
        if (bestCount < 5) {
            System.out.println("Текст слишком короткий для статистического взлома. Используй взлом по словарю.");
            return;
        }

        // Шаг 4: считаем ключ: самая частая буква шифровки — это переодетая "о"
        int key = bestIndex - Alphabet.getIndex('о');
        if (key < 0) { // если ушли в минус — заворачиваем в диапазон 0..99
            key += Alphabet.size();
        }

        // Шаг 5: расшифровываем найденным ключом
        System.out.println("Найден лучший ключ: " + key);
        System.out.println("Расшифрованный текст:\n" + Rashivrovat(text, key));
}}
