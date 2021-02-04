package enums;

public enum Endpoints {
  CREATE_USER("user/");

  private final String value;

  Endpoints(String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }
}
