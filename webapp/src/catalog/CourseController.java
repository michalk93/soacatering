package catalog;

import common.CourseService;
import model.Category;
import model.Course;
import model.CourseIngredient;
import model.Ingredient;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mkolbusz on 6/8/16.
 */
@ManagedBean
@ViewScoped
public class CourseController implements Serializable {
    private Course course;
    private UploadedFile coursePhoto;

    private List<Course> courseList = new ArrayList<>();

    private CourseIngredient selectedCourseIngredient;

    @EJB(lookup = "java:jboss/exported/catalog/CourseServiceImplementation!common.CourseService")
    CourseService courseService;

    @PostConstruct
    void init(){
        course = new Course();
        courseList = getCursesFromCategory();
        selectedCourseIngredient = new CourseIngredient();
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public UploadedFile getCoursePhoto() {
        return coursePhoto;
    }

    public void setCoursePhoto(UploadedFile coursePhoto) {
        this.coursePhoto = coursePhoto;
    }

    public CourseIngredient getSelectedCourseIngredient() {
        return selectedCourseIngredient;
    }

    public void setSelectedCourseIngredient(CourseIngredient selectedCourseIngredient) {
        this.selectedCourseIngredient = selectedCourseIngredient;
    }

    public void addIngredient(){
        course.getCourseIngredients().add(new CourseIngredient(course,
                selectedCourseIngredient.getIngredient(),
                selectedCourseIngredient.getQuantity()));
        FacesMessage msg = new FacesMessage("Dodano składnik" + " S: " + course.getCourseIngredients().size(), course.getName() );
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void save(){
        if(coursePhoto != null){
           if(this.uploadPhoto(coursePhoto)){
               course.setImage(coursePhoto.getFileName());
           }
        }
        courseService.save(course);
        FacesMessage msg = new FacesMessage("Danie zapisane pomyślnie", course.getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    private boolean uploadPhoto(UploadedFile file) {
        System.err.println("Start upload file");
        try {
            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File("~/uploads/" + file.getFileName()));
            InputStream in = file.getInputstream();

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private List<Course> getCursesFromCategory() {
        Integer categoryId;
        try {
            categoryId = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("categoryId"));
        }catch(NumberFormatException e) {
            categoryId = null;
        }

        if(categoryId == null) {
            return null;
        }

        Category category = new Category();
        category.setId(categoryId);
        List<Course> courses = courseService.getCoursesFromCategory(category);

        return courses;
    }

    public void removeCourse(Course course) {
        courseService.removeCourse(course);
        courseList.remove(course);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Danie zapisane pomyślnie"));
    }


    public void onRowEdit(RowEditEvent event) {
        courseService.save(course);
        FacesMessage msg = new FacesMessage("Course Ingredient Edited", String.valueOf(((CourseIngredient) event.getObject()).getCourseIngredientId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("id", String.valueOf(course.getCourseId()));
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(((CourseIngredient) event.getObject()).getCourseIngredientId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
