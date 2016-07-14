package example;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * Created by mkolbusz on 7/8/16.
 */
@WebService(targetNamespace = "http://paymentsystem.soap/")
public class SalaryPayment {
  @WebMethod
  public boolean getMoneyFromSalary(@WebParam(name = "email") String email,
                                   @WebParam(name = "authCode") String authCode,
                                   @WebParam(name = "money") Double money) {
    System.out.println("Prośba o zapłatę " + money + " zł z konta pracownika " + email);
    return true;
  }


}
