package SpringBootJDBCTemplate.ui;

import lombok.RequiredArgsConstructor;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomPromptProvider implements PromptProvider {
    private static final AttributedString
            MAIN_MENU_PROMPT = new AttributedString("main-menu> ",
            AttributedStyle.BOLD.foreground(AttributedStyle.MAGENTA));

    private final PeopleController controller;

    @Override
    public AttributedString getPrompt() {
        if (controller.getState() == PeopleController.State.MAIN_MENU) {
            return MAIN_MENU_PROMPT;
        }
        throw new IllegalArgumentException();
    }
}
