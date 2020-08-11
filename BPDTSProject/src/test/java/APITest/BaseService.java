package APITest;

    import static io.restassured.RestAssured.given;

    import io.restassured.http.ContentType;
    import io.restassured.response.Response;

public class BaseService {

    public Response callGet(String resourcePath, int expStatus) {

        Response response = given().accept( ContentType.JSON ).get( resourcePath );
        response.then().log().ifValidationFails().statusCode( expStatus );

        return response;
    }
}
