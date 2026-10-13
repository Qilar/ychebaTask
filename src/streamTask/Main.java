package streamTask;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        list.stream()
                .filter(i -> i % 2 == 0)
                .map(i -> i * 2)
                .sorted((i, j) -> j - i)
                .collect(Collectors.toList())
                .forEach(i -> System.out.println(i));


    }
}
