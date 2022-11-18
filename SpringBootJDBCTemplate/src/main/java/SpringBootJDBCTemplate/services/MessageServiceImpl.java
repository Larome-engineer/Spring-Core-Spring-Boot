package SpringBootJDBCTemplate.services;

import SpringBootJDBCTemplate.aop.annotation.MET;
import SpringBootJDBCTemplate.services.serviceInterface.CurrentLocaleService;
import SpringBootJDBCTemplate.services.serviceInterface.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageSource messageSource;
    private final CurrentLocaleService currentLocaleService;

    @MET
    @Override
    public String localize(String code, Object... params) {
        return messageSource.getMessage(code, params, currentLocaleService.get());
    }
}