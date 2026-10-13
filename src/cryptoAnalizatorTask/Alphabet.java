package cryptoAnalizatorTask;

public class Alphabet {
    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public static int getIndex(char ch){
        return ALPHABET.indexOf(ch);
    }
    public static char getChar(int i){
        return ALPHABET.charAt(i);
    }
    public static int size(){
        return ALPHABET.length();
    }
}
