package catalog;

import common.CourseService;
import model.Course;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.*;
import java.nio.file.*;

/**
 * Created by mkolbusz on 7/14/16.
 */
@ManagedBean
@SessionScoped
public class CoursePhotoController {
    UploadedFile file;
    Course course;

    @EJB(lookup = "java:jboss/exported/catalog/CourseServiceImplementation!common.CourseService")
    CourseService courseService;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String dummyAction(){
        try {
            Path folder = Paths.get("/home/mkolbusz/uploads/"+file.getFileName());
            Files.write(folder, file.getContents(), StandardOpenOption.CREATE);

            course.setImage(file.getFileName());
            courseService.save(course);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
