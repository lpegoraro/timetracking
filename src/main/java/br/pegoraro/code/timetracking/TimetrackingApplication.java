package br.pegoraro.code.timetracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class TimetrackingApplication {

  public static void main(String[] args) {
    SpringApplication.run(TimetrackingApplication.class, args);
  }
}
