package catalog;

import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.impl.JSONEncoder;

import javax.faces.application.FacesMessage;

/**
 * Created by mkolbusz on 7/8/16.
 */
@PushEndpoint("/top10")
public class Top10Resource {
    @OnMessage(encoders = {JSONEncoder.class})
    public FacesMessage onMessage(FacesMessage message){
        System.err.println("OnMESSAGE");
        return message;
    }

}
