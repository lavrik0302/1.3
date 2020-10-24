package com.company;

import java.util.Scanner;
import java.lang.String;

class Node{
    public String phoneNumber;
    public String FIO;
    public Node next;
    public Node prev;

    public Node(String name,String number) {
        phoneNumber = number;
        FIO=name;
        next = null;
        prev = null;
    }
}
class OrderedList {
    private Node first;
    private int size;

    public OrderedList() {
        first = null;
        size = 0;
    }
    public void printList() {
        if (size == 0) System.out.println("Список пуст");
        else {
            int index = 1;
            Node curr = first;
            System.out.printf("|%14s|\n", "Упорядоченный");
            while (curr != null) {
                System.out.println("|------------------------------+--------------|");
                System.out.printf("|%-30s|%14s|\n", curr.FIO, curr.phoneNumber);
                curr = curr.next;
                index++;
            }
        }
        System.out.println("|______________________________|______________|");
    }
    public void addNew(Node obj) {
        if (size == 0)
            first = obj;
        else {
            Node curr = first, prev = curr;
            while (curr != null && (curr.FIO.compareTo(obj.FIO) < 0)) {
                prev = curr;
                curr = curr.next;
            }
            if (curr == first) {
                obj.next = first;
                first = obj;
            } else {
                obj.next = curr;
                prev.next = obj;
            }
        }
        size++;
    }

    public static class Main {

        public static void main(String[] args) {
            Scanner scn = new Scanner(System.in);
            Single<Abonent> iter = new Single<Abonent>();
            OrderedList ordlist = new OrderedList();
            System.out.println("Введите ФИО (на английском)");
            String name = "";
            boolean f = true;
            while (f == true) {
                name = scn.nextLine().trim();
                if (Abonent.checkName(name)) {

                    f = false;

                    name = name.replaceAll("\\s+", " ");

                    name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
                    for (int i = 1; i < name.length(); i++) {
                        if (name.charAt(i) == ' ') {
                            name = name.substring(0, i + 1) + Character.toUpperCase(name.charAt(i + 1)) + name.substring(i + 2);
                            i++;
                        } else {
                            name = name.substring(0, i) + Character.toLowerCase(name.charAt(i)) + name.substring(i + 1);
                        }
                    }
                } else {
                    System.out.println("Некорректный ввод . Попробуте ещё раз ");
                }
            }

            System.out.println("Введите 7 значный номер");
            String number = "";
            f = true;
            while (f == true) {
                number = scn.nextLine().trim();

                if (number.equals("0")) {
                    break;
                }
                if (Abonent.checknumber(number)) {
                    f = false;
                } else {
                    System.out.println("Некорректный ввод . Попробуте ещё раз ");
                }
                iter.add(new Abonent(name, number));


                ordlist.addNew(new Node(name,number));
            }
            label:
            while (true) {
                System.out.println("Что вы хотите?");
                System.out.println("");
                System.out.println("Введите 1, если Вы хотите определить по номеру телефона фамилию");
                System.out.println("Введите 2, если Вы хотите по фамилии определить список номеров телефонов");
                System.out.println("Введите 3, если Вы хотите добавить новый номер");
                System.out.println("Введите 4, если Вы хотите просмотреть упорядоченный лексикографически список");
                f = true;
                String s = ""; // хранит номер операции
                while (f) {
                    s = scn.nextLine().trim(); // считывает строку и удаляет пробелы
                    // в начале и конце строки . В
                    // дальнейшем не будет поясняться
                    if (s.matches("[1-4]")) { // если происходит соответствие
                        f = false;
                    } else {
                        System.out.println("Некорректный ввод . Введите ещё раз");
                    }
                }
                if (s.equals("1")) {
                    System.out.println("Введите номер телефона");
                    f = true;
                    while (f == true) {
                        number = scn.nextLine().trim();

                        // проверка на корректность
                        if (Abonent.checknumber(number)) {
                            f = false;
                        } else {
                            System.out.println("Некорректный ввод . Попробуте ещё раз ");
                        }
                    }
                    System.out.printf("|%14s|%-15s|\n", "Номер телефона", "Фамилия");
                    for (int i = 0; i < iter.size(); i++) {
                        if (number.equals(iter.get(i).number)) {
                            String s3 = iter.get(i).getname();
                            int ind = s3.indexOf(' ');
                            System.out.println("|--------------+---------------|");
                            System.out.printf("|%14s|%-15s|\n", iter.get(i).getnumber(), s3.substring(0, ind));
                        }
                    }
                    System.out.println("|______________|_______________|");
                }
                if (s.equals("2")) {
                    System.out.println("Введите Фамилию (на английском)");
                    f = true;
                    while (f == true) {
                        name = scn.nextLine().trim();
                        // возврат в меню

                        if (Abonent.check(name)) {
                            f = false;
                        } else {
                            System.out.println("Некорректный ввод . Попробуте ещё раз ");
                        }
                    }
                    System.out.printf("|%15s|%14s|\n", "ФИО", "Номер телефона");
                    for (int i = 0; i < iter.size(); i++) {
                        String s3 = iter.get(i).getname();
                        int d = s3.indexOf(' ');
                        s3 = s3.substring(0, d);
                        if (name.equals(s3)) {
                            System.out.println("|---------------+--------------|");
                            System.out.printf("|%-15s|%14s|\n", s3, iter.get(i).getnumber());
                        }
                    }
                    System.out.println("|_______________|______________|");
                }
                if (s.equals("3")) {
                    System.out.println("Введите ФИО (на английском)");
                    f = true;
                    while (f == true) {
                        name = scn.nextLine().trim();
                        if (Abonent.checkName(name)) {

                            f = false;

                            name = name.replaceAll("\\s+", " ");

                            name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
                            System.out.println("Введите 7 значный номер");
                            number = "";
                            f = true;
                            while (f == true) {
                                number = scn.nextLine().trim();

                                if (number.equals("0")) {
                                    break;
                                }
                                if (Abonent.checknumber(number)) {
                                    f = false;
                                } else {
                                    System.out.println("Некорректный ввод . Попробуте ещё раз ");
                                }
                                iter.add(new Abonent(name, number));
                                ordlist.addNew(new Node(name,number));
                            }
                            for (int i = 1; i < name.length(); i++) {
                                if (name.charAt(i) == ' ') {
                                    name = name.substring(0, i + 1) + Character.toUpperCase(name.charAt(i + 1)) + name.substring(i + 2);
                                    i++;
                                } else {
                                    name = name.substring(0, i) + Character.toLowerCase(name.charAt(i)) + name.substring(i + 1);
                                }
                            }
                        } else {
                            System.out.println("Некорректный ввод . Попробуте ещё раз ");
                        }
                    }
                }
                if (s.equals("4")) {
                  ordlist.printList();
                }

            }
        }
    }
}