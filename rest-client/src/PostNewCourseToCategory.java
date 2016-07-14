//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import com.google.gson.Gson;
import java.util.Scanner;
import model.Category;
import model.Course;

public class PostNewCourseToCategory extends RestService {
    public PostNewCourseToCategory() {
    }

    public void run() {
        String json = (new Gson()).toJson(this.createNewCourse());
        this.POST("/catalog/course", json);
    }

    private Course createNewCourse() {
        Course course = new Course();
        Category category = new Category();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj nazwe potrawy: ");
        course.setName(scanner.nextLine());
        System.out.print("Podaj cenę potrawy: ");
        course.setPrice(Double.valueOf(scanner.nextDouble()));
        System.out.print("Podaj ID kategorii: ");
        category.setId(scanner.nextInt());
        course.setCategory(category);
        return course;
    }
}
