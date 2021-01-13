package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:config.properties")
public interface ServerConfig extends Config {

  @Key("url")
  String url();

  @Key("apiUrlSwagger")
  String apiUrlSwagger();

  @Key("email")
  String email();

  @Key("password")
  String password();

  @Key("urlUserDetails")
  String urlUserDetails();
}
