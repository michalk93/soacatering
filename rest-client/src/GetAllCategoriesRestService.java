//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class GetAllCategoriesRestService extends RestService {
    public GetAllCategoriesRestService() {
    }

    public void run() {
        String json = this.GET("/catalog/categories");
        System.out.println(json);
    }
}
