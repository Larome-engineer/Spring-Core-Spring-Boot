package spring.jdbc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.jdbc.config.AppConfig;
import spring.jdbc.ui.Menu;


public class Main {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Menu menu = context.getBean("menu", Menu.class);


        menu.menu();

        context.close();
    }
}

