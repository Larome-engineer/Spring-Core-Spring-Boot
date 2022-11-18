package SpringBootAndShell.bootAndShell.services;

import SpringBootAndShell.bootAndShell.aop.annotation.MET;
import SpringBootAndShell.bootAndShell.aop.annotation.MyDeprecated;
import SpringBootAndShell.bootAndShell.services.servicesInterface.CurrentLocaleService;
import SpringBootAndShell.bootAndShell.services.servicesInterface.MessageService;
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
    @MyDeprecated
    public String localize(String code, Object... params) {
        return messageSource.getMessage(code, params, currentLocaleService.get());
    }
}
