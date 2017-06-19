package ka.thriftoverhttpusingspringboot;

import javax.servlet.Servlet;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.server.TServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import ka.thriftoverhttpusingspringboot.api.CalculationService;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public ServletRegistrationBean servletRegistrationBean() {
    TProcessor tProcessor = new CalculationService.Processor<CalculationServiceHandler>(
        new CalculationServiceHandler());
    Servlet servlet = new TServlet(tProcessor, new TJSONProtocol.Factory());
    return new ServletRegistrationBean(servlet, "/calculator/*");
  }

}
