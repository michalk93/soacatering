//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input;
        do {
            printMenu();
            input = scanner.nextLine();
            byte var4 = -1;
            switch(input.hashCode()) {
                case 48:
                    if(input.equals("0")) {
                        var4 = 0;
                    }
                    break;
                case 49:
                    if(input.equals("1")) {
                        var4 = 1;
                    }
                    break;
                case 50:
                    if(input.equals("2")) {
                        var4 = 2;
                    }
                    break;
                case 51:
                    if(input.equals("3")) {
                        var4 = 3;
                    }
            }

            switch(var4) {
                case 0:
                    break;
                case 1:
                    (new GetAllCategoriesRestService()).run();
                    break;
                case 2:
                    (new GetSelectedCategoryWithCourses()).run();
                    break;
                case 3:
                    (new PostNewCourseToCategory()).run();
                    break;
                default:
                    System.out.println("Nieprawidlowa operacja");
            }
        } while(!input.equalsIgnoreCase("0"));

    }

    private static void printMenu() {
        System.out.println("\n----\nAplikacja kliencka serwisu REST");
        System.out.println("[1] - pobierz wszystkie kategorie [JSON]");
        System.out.println("[2] - pobierz wybrana kategorie z posiłkami [JSON]");
        System.out.println("[3] - dodaj pozycję w wybranej kategorii");
        System.out.println("[0] - zakończ");
        System.out.println("Wybor: ");
    }
}
