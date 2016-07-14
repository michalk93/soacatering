//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import com.google.gson.Gson;
import java.util.Scanner;
import java.util.function.Consumer;
import model.Category;

public class GetSelectedCategoryWithCourses extends RestService {
    public GetSelectedCategoryWithCourses() {
    }

    public void run() {
        String id = this.getCategoryId();
        if(id != null) {
            String json = this.GET("/catalog/category/" + id);
            Category category = (Category)(new Gson()).fromJson(json, Category.class);
            this.printCategory(category);
        }
    }

    private String getCategoryId() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ID kategorii");

        try {
            return String.valueOf(scanner.nextInt());
        } catch (NumberFormatException var3) {
            System.out.println("Niepoprawne ID kategorii");
            return null;
        }
    }

    public void printCategory(Category category) {
        System.out.println("- CATEGORY: " + category.getName());
        category.getCourses().stream().forEach((c) -> {
            System.out.println("|-- " + c.getName() + " [" + c.getPrice() + "]");
        });
    }
}
