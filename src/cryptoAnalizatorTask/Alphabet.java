package cryptoAnalizatorTask;

public class Alphabet {
    private static final char[] ALPHABET = (
            "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" +
                    "abcdefghijklmnopqrstuvwxyz" +
                    "0123456789" +
                    " !?.,:;-_=+*()[]{}\"'/@#$%^&~`«»").toCharArray();

    public static int getIndex(char ch){ // посмотреть индекс по значению
        for (int i = 0; i < ALPHABET.length; i++) {
            if (ALPHABET[i] == ch) {
                return i;
            }
        } return -1;
    }
    public static char getChar(int i){ // посмотреть значение по индексу
        return ALPHABET[i];
    }
    public static int size(){ // размер этого говна
        return ALPHABET.length;
    }

}
