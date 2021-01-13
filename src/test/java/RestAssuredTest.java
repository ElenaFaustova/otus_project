import enums.Endpoints;
import models.SwaggerUserData;
import models.SwaggerUserNoIdData;
import org.junit.jupiter.api.Test;

import static apiHelpers.restSpecifications.requestSpec;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;

public class RestAssuredTest {


  private SwaggerUserData allData = new SwaggerUserData(123, "efUserName", "efFirstName", "efLastName", "ef@test.com", "efPassword", "123", 2);

  @Test
  public void createUser() {
    given()
            .spec(requestSpec())
            .body(allData)
            .post(Endpoints.CREATE_USER.value())
            .then()
            .statusCode(SC_OK);
  }

  @Test
  public void createUserWithoutId() {
    SwaggerUserNoIdData dataWithoutId = new SwaggerUserNoIdData("efUserName", "efFirstName", "efLastName", "ef@test.com", "efPassword", "123", 2);

    given()
            .spec(requestSpec())
            .body(dataWithoutId)
            .post(Endpoints.CREATE_USER.value())
            .then()
            .statusCode(SC_OK);
  }

  @Test
  public void createUserEmptyBody() {
    given()
            .spec(requestSpec())
            .body("")
            .post(Endpoints.CREATE_USER.value())
            .then()
            .statusCode(SC_METHOD_NOT_ALLOWED);
  }

  @Test
  public void getUser() {
    given()
            .spec(requestSpec())
            .get(Endpoints.CREATE_USER.value() + userName())
            .then()
            .statusCode(SC_OK);
  }

  @Test
  public void getNotExistedUser() {
    given()
            .spec(requestSpec())
            .get(Endpoints.CREATE_USER.value() + "thereIsNoSuchUserEF")
            .then()
            .statusCode(SC_NOT_FOUND);
  }


  private String userName() {
    given()
            .spec(requestSpec())
            .body(allData)
            .post(Endpoints.CREATE_USER.value())
            .then()
            .statusCode(SC_OK);

    return allData.getUsername();
  }
}