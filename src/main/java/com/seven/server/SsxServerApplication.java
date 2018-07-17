package com.seven.server;

import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.seven.server.core.ProjectConstant.MAPPER_PACKAGE;

@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication
@MapperScan(basePackages = MAPPER_PACKAGE)
public class SsxServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SsxServerApplication.class, args);
  }
}
