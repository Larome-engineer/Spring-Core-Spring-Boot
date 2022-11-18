package spring.jdbc.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ResourceBundle;
@Service("ioService")
@RequiredArgsConstructor
public class IOServiceImpl implements IOService {

    private final String service = String.valueOf(getClass());

    private final Logger log;

    @Override
    public void outputMessageByLocale(ResourceBundle message, String key) {
        System.out.println(message.getString(key));
        log.info("Вывод сообщения на пользовательском языке из сервиса: " + service);
    }

    @Override
    public void printPeople(Object o) {
        System.out.println(o);
        log.info("Вывод результата работы методов DAO из сервиса: " + service);
    }
}
