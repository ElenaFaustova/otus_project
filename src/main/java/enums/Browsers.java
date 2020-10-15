package enums;

public enum Browsers {

  CHROME("chrome"),
  FIREFOX("firefox");


  private final String value;

  Browsers(String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }
}
