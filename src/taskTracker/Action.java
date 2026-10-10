package taskTracker;

import taskTracker.exception.TaskNotFoundException;

import java.util.*;


public class Action {
    public static final String NOTFOUNDVALUE = "Это не число. Попробуй ещё.";

    public static void start() {
        int select = 0;
        List<Task> tasks = new ArrayList<Task>();
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.printf("1. Добавить задачу%n2. Показать все%n3. Отметить выполненной и обратно.%n4. Удалить%n0. Выход%n");
            try {
                select = s.nextInt();
                s.nextLine();
            }catch (InputMismatchException e){
                System.out.println(NOTFOUNDVALUE);
                s.nextLine();
                continue;
            }
            switch (select) {
                case 1 -> tasks.add(Fabricas.fabricaTask(s.nextLine()));
                
                case 2 -> {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + " " + tasks.get(i));
                    }
                }
                
                case 3 -> {
                    System.out.println("Выбери из предложенного. Что отметить?");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + " " + tasks.get(i));
                    }
                    System.out.println("Введи номер ");
                    int i = 0;
                    try {
                        i = s.nextInt();
                        s.nextLine();
                        if (i < 0 || i >= tasks.size()) {
                            throw new TaskNotFoundException("Задача " + i + " не найдена попробуй еще раз.");
                        }
                        tasks.get(i).toggle();
                        System.out.println("Вы выбрали " + tasks.get(i) + " Оно было отмечено.");
                    } catch (InputMismatchException e) {
                        System.out.println(NOTFOUNDVALUE);
                        s.nextLine();
                    } catch (TaskNotFoundException e) {
                        System.out.println(e.getMessage());
                    }

                }
                
                case 4 -> {
                    System.out.println("Выбери из предложенного. Что нужно удалить?");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + " " + tasks.get(i));
                    }
                    System.out.println("Введи номер ");
                    int i = 0;
                    try {
                        i = s.nextInt();
                        s.nextLine();
                        if (i < 0 || i >= tasks.size()) {
                            throw new TaskNotFoundException("Задача " + i + " не найдена попробуй еще раз.");
                        }
                        Task removed = tasks.get(i);
                        tasks.remove(i);
                        System.out.println("Вы выбрали " + removed + " Оно было удалено.");
                    } catch (InputMismatchException e) {
                        System.out.println(NOTFOUNDVALUE);
                        s.nextLine();
                    } catch (TaskNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
                
                case 0 -> {
                    System.out.println("До свидания.");
                    return;
                }
            }
        }
    }
}


