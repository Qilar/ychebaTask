package taskTracker;

import java.util.*;


public class Action {

    public static void start(){
        int select;
        String message;
        List<Task> tasks = new ArrayList<Task>();
        Scanner s = new Scanner(System.in);
        while(true){System.out.printf("1. Добавить задачу%n2. Показать все%n3. Отметить выполненной и обратно.%n4. Удалить%n0. Выход%n" );
            select = s.nextInt();
            s.nextLine();
        switch (select){
            case 1 -> tasks.add(Fabricas.fabricaTask(s.nextLine()));
            case 2 -> {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + " " + tasks.get(i));
                }
            }
            case 3 ->{
                System.out.println("Выбери из предложенного. Что отметить?");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + " " + tasks.get(i));
                }
                System.out.println("Введи номер ");
               int i = s.nextInt();
               tasks.get(i).toggle();
                System.out.println("Вы выбрали " + tasks.get(i) +  " Оно было отмечено." );

            }
            case 4 ->{
                System.out.println("Выбери из предложенного. Что нужно удалить?");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + " " + tasks.get(i));
                }
                System.out.println("Введи номер ");
                int i = s.nextInt();
                Task removed = tasks.get(i);
                tasks.remove(i);
                System.out.println("Вы выбрали " + removed +  " Оно было удалено." );

            }
            case 0 ->{
                System.out.println("До свидания.");
             return;}
            }
        }
    }
}


