package taskTracker;

import java.util.*;


public class Action {

    public static void start(){
        int select;
        String message;
        List<Task> tasks = new ArrayList<Task>();
        Scanner s = new Scanner(System.in);
        while(true){System.out.printf("1. Добавить задачу%n2. Показать все%n3. Отметить выполненной%n4. Удалить%n0. Выход%n" );
            select = s.nextInt();
            s.nextLine();
        switch (select){
            case 1 -> tasks.add(Fabricas.fabricaTask(s.nextLine()));
            case 2 -> tasks.forEach(a->System.out.println(a));
            case 3 -> tasks.forEach(a -> a.complete(true));
            case 4 -> tasks.removeAll(tasks);
            case  0 -> {
                System.out.println("Досвидаия"); return;
            }
        }}
    }

}
