package spring.jdbc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import spring.jdbc.aop.Logging;
import spring.jdbc.dao.PersonDAOImpl;
import spring.jdbc.services.CurrentLocaleServiceImpl;
import spring.jdbc.services.IOServiceImpl;
import spring.jdbc.services.PeopleServiceImpl;
import spring.jdbc.ui.Menu;

import java.util.Scanner;


@Configuration
@ComponentScan("spring.jdbc")
public class AppConfig {

    @Bean
    public Logging logging() {
        return new Logging();
    }

    @Bean
    PersonDAOImpl personDAO(){
        return new PersonDAOImpl();
    }

    @Bean
    @Primary
    public Logger logger() {
        return LoggerFactory.getLogger("SampleLogger");
    }

    @Bean
    IOServiceImpl ioService() {return new IOServiceImpl(logger());}

    @Bean
    CurrentLocaleServiceImpl localeService() {
        return new CurrentLocaleServiceImpl(logger(), ioService());
    }

    @Bean
    PeopleServiceImpl peopleService() {
        return new PeopleServiceImpl(logger(), personDAO());
    }

    @Bean
    Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    Menu menu() {
        return new Menu(peopleService(), localeService(), ioService(), scanner());
    }


    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource src = new ReloadableResourceBundleMessageSource();
        src.setDefaultEncoding("UTF-8");
        src.setBasename("bundle");
        src.setUseCodeAsDefaultMessage(true);
        return src;
    }
}
