package br.pegoraro.code.timetracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.logging.Logger;

@SpringBootApplication
public class TimetrackingApplication {

  private static Logger logger = Logger.getLogger(TimetrackingApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(TimetrackingApplication.class, args);
  }

  @RequestMapping("/")
  public String home() {
    logger.info("Handling home");
    return "Hello World";
  }

}
