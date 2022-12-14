/**
 * create by Zaryvnoire Alexandr on 14.12.2022
 **/

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
CRUD
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat inputFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        if (args.length != 0) {
            switch (args[0]) {
                case "-c" :
                    synchronized (allPeople) {
                        String name;
                        String sex;
                        Date bd;
                        for (int i = 1; i < args.length; i = i + 3) {
                            name = args[i];
                            sex = args[i + 1];
                            bd = inputFormatter.parse(args[i + 2]);
                            CSolution(name, sex, bd);
                            System.out.println("Добавлен человек " + allPeople.get(allPeople.size() - 1).toString() + "с индексом -> " + (allPeople.size() - 1));
                        }
                    }
                    break;
                case "-u" :
                    synchronized (allPeople) {
                        int id;
                        String name;
                        String sex;
                        Date bd;
                        for (int i = 1; i < args.length; i = i + 4) {
                            id = Integer.parseInt(args[i]);
                            name = args[i + 1];
                            sex = args[i + 2];
                            bd = inputFormatter.parse(args[i + 3]);
                            USolution(id, name, sex, bd);
                        }
                    }
                    break;
                case "-d" :
                    synchronized (allPeople) {
                        for (int i = 1; i < args.length; i++) {
                            DSolution(Integer.parseInt(args[i]));
                        }
                    }
                    break;
                case "-i" :
                    synchronized (allPeople) {
                        for (int i = 1; i < args.length; i++) {
                            ISolution(Integer.parseInt(args[i]));
                        }
                    }
            }
        } else {
            System.out.println("Вы не ввели аргументы запуска программы!");
            System.out.println("Пожалуйста введите аргументы и попробуйте заново.");
        }

    }

    public static void CSolution(String name, String sex, Date bd) {
        allPeople.add(sex.equals("м") ? Person.createMale(name, bd) : Person.createFemale(name, bd));
    }
    public static void USolution(int id, String name, String sex, Date bd) {
        Person person = allPeople.get(id);
        person.setName(name);
        person.setSex(sex.equals("м") ? Sex.MALE : Sex.FEMALE);
        person.setBirthDate(bd);
    }
    public static void DSolution(int id) {
        Person person = allPeople.get(id);
        person.setName(null);
        person.setSex(null);
        person.setBirthDate(null);
    }
    public static void ISolution(int id) {
        Person person = allPeople.get(id);
        System.out.println(person.toString());
    }
}

