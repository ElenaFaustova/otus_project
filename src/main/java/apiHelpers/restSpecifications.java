package apiHelpers;

import io.restassured.builder.RequestSpecBuilder;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static application.appManager.cfg;

public class restSpecifications {
  public static final String baseUrl = cfg.apiUrlSwagger();

  private static RequestLoggingFilter requestLoggingFilter  = new RequestLoggingFilter();
  private static ResponseLoggingFilter responseLoggingFilter  = new ResponseLoggingFilter();


  public static RequestSpecification requestSpec() {
    return new RequestSpecBuilder()
            .setBaseUri(baseUrl)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .build()
            .filters(new AllureRestAssured(), requestLoggingFilter, responseLoggingFilter);
  }
}
