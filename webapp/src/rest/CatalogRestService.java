package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import common.CategoryService;
import common.CourseService;
import model.Course;
import rest.exlusion.CategoryWithCourses;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by mkolbusz on 6/29/16.
 */
@Path("/catalog")
@Stateless
@RequestScoped
public class CatalogRestService {

    @EJB(lookup = "java:jboss/exported/catalog/CategoryServiceImplementation!common.CategoryService")
    CategoryService categoryService;

    @EJB(lookup = "java:jboss/exported/catalog/CourseServiceImplementation!common.CourseService")
    CourseService courseService;

    @GET
    @Path("/categories")
    @Produces("application/json")
    public String getCategories(){
        Gson gson = new GsonBuilder().setExclusionStrategies(new CategoryWithCourses()).create();
        return gson.toJson(categoryService.getAll());
    }

    @GET
    @Path("/category/{categoryId}")
    @Produces("application/json")
    public String getCoursesFromCategory(@PathParam("categoryId") int id) {
        Gson gson = new GsonBuilder().setExclusionStrategies(new CategoryWithCourses()).create();
        return gson.toJson(categoryService.getCategoryById(id));
    }


    @POST
    @Path("/course")
    @Consumes("application/json")
    public Response postNewCourse(String json){
        Course course = new Gson().fromJson(json, Course.class);
        courseService.save(course);
        return Response.status(Response.Status.CREATED).build();
    }
}
