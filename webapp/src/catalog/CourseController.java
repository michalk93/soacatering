package catalog;

import common.CourseService;
import model.Category;
import model.Course;
import model.CourseIngredient;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by mkolbusz on 6/8/16.
 */
@ManagedBean
@ViewScoped
public class CourseController implements Serializable {

    private Course course;

    private List<Course> courseList = new ArrayList<>();

    private CourseIngredient selectedCourseIngredient;

    @EJB(lookup = "java:jboss/exported/catalog/CourseServiceImplementation!common.CourseService")
    CourseService courseService;

    @PostConstruct
    void init(){
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        if(context.getRequestParameterMap().containsKey("id")){
            Integer id = Integer.parseInt(context.getRequestParameterMap().get("id"));
            if(id != null){
                course = courseService.getById(id);
            }
        }else {
            course = new Course();
        }


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

    public CourseIngredient getSelectedCourseIngredient() {
        return selectedCourseIngredient;
    }

    public void setSelectedCourseIngredient(CourseIngredient selectedCourseIngredient) {
        this.selectedCourseIngredient = selectedCourseIngredient;
    }

    public void addIngredient(){
        CourseIngredient courseIngredient = new CourseIngredient(course,
                selectedCourseIngredient.getIngredient(), selectedCourseIngredient.getQuantity());

        Optional<CourseIngredient> ingredient = course.getCourseIngredients().stream()
                .filter(o -> o.equals(courseIngredient)).findFirst();

        FacesMessage msg;
        if(ingredient.isPresent()){
            ingredient.get().setQuantity(courseIngredient.getQuantity());
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Zaktualizowano składnik",
                    courseIngredient.getIngredient().getName() + ": " + courseIngredient.getQuantity());
        }else {
            course.getCourseIngredients().add(courseIngredient);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dodano nowy składnik",
                    courseIngredient.getIngredient().getName() + ": " + courseIngredient.getQuantity());
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void save(){
        courseService.save(course);

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Danie zapisane pomyślnie", course.getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
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

    public List<Course> getTop10(){
        return courseService.getTop10();
    }

    public void newTop10(){
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish("/top10", new FacesMessage("TOP10", "Nastąpiła zmiana w TOP10"));
    }

    public void removeCourse(Course course) {
        courseService.remove(course);
        courseList.remove(course);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Danie zostało usunięte"));
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
