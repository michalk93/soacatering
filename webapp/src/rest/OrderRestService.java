package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import common.OrderService;
import model.Order;
import model.OrderStatus;
import rest.exlusion.CategoryWithCourses;
import rest.exlusion.OrderExclusion;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;
import java.util.Optional;

/**
 * Created by mkolbusz on 7/15/16.
 */
@Path("/order")
@Stateless
@RequestScoped
public class OrderRestService {
    @EJB(lookup = "java:jboss/exported/order/OrderServiceImplementation!common.OrderService")
    private OrderService orderService;

    @GET
    @Path("/{id}/status/{status}")
    @Produces("application/json")
    public boolean changeOrderStatus(@PathParam("id") Integer id, @PathParam("status") Integer statusId){
        Order order = orderService.getOrderById(id);
        List<OrderStatus> statuses = orderService.getOrderStatuses();
        Optional<OrderStatus> status = statuses.stream().filter(s -> s.getStatusID() == statusId).findFirst();
        if(status.isPresent()){
            order.setStatus(status.get());
            orderService.update(order);
            return true;
        }
        return false;
    }

    @GET
    @Path("/{id}")
    public String getOrder(@PathParam("id") Integer id){
        Gson gson = new GsonBuilder().setExclusionStrategies(new OrderExclusion()).create();
        return gson.toJson(orderService.getOrderById(id));
    }
}
