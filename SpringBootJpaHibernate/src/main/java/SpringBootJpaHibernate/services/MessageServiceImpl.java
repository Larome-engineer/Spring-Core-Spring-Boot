package SpringBootJpaHibernate.services;

import SpringBootJpaHibernate.aop.annotation.MET;
import SpringBootJpaHibernate.services.serviceInterface.CurrentLocaleService;
import SpringBootJpaHibernate.services.serviceInterface.MessageService;
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
    @Deprecated
    public String localize(String code, Object... params) {
        return messageSource.getMessage(code, params, currentLocaleService.get());
    }
}