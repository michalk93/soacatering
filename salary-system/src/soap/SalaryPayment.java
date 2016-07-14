package soap;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by mkolbusz on 7/8/16.
 */
@WebService(targetNamespace = "http://paymentsystem.soap/")
public class SalaryPayment {

  @WebMethod
  public boolean getMoneyFromSalary(@WebParam(name = "email") String email,
                                   @WebParam(name = "orderId") String orderId,
                                   @WebParam(name = "money") Double money) {

    System.out.println("Prośba o zapłatę:" +
            "\tUzytkownik: " + email + "\n" +
            "\tZamowienie numer: " + orderId + "\n" +
            "\tKwota: " + money + " zł");

    return true;
  }


}
