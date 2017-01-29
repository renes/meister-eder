package at.rs.alexa;

import at.rs.alexa.me.MeisterEderSpeechlet;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.servlet.SpeechletServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
@EnableAutoConfiguration
public class MeisterEderApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MeisterEderApp.class, args);
    }

    @SuppressWarnings("serial")
    @Bean
    public ServletRegistrationBean alexaServlet() {
        SpeechletServlet servlet = new SpeechletServlet();
        servlet.setSpeechlet(defaultSpeechlet());
        return new ServletRegistrationBean(servlet, "/meister-eder/");
    }

    @Bean
    public Speechlet defaultSpeechlet() {
        return new MeisterEderSpeechlet();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MeisterEderApp.class);
    }
}