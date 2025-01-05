package org.example;

import lombok.RequiredArgsConstructor;
import org.example.service.CaseCheckDialogService;
import org.jline.terminal.TerminalBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LtGrammarTool implements CommandLineRunner {

    private final CaseCheckDialogService caseCheckDialogService;

    @Override
    public void run(String... args) throws Exception {
        try (var terminal = TerminalBuilder.terminal()) {
            terminal.enterRawMode();

            terminal.writer().println("Welcome to the Lithuanian grammar tool: ");
            terminal.writer().println("Choose case to train word conjugation:");
            terminal.writer().println("1 - Nominative plural");
            terminal.writer().println("2 - Genitive");
            terminal.writer().println("3 - Accusative");
            terminal.writer().println("Q - Exit application.");
            terminal.writer().println();
            terminal.writer().flush();

            while (true) {
                int c = terminal.reader().read(16);
                if (c >= 0) {
                    terminal.writer().flush();

                    switch ((char) c) {
                        case '1':
                            terminal.writer().println("You chose Nominative plural form.");
                            terminal.writer().flush();
                            caseCheckDialogService.initiateCaseCheckDialog(terminal);
                            return;
                        case '2':
                            terminal.writer().println("You chose Genitive case.");
                            terminal.writer().flush();
                            break;
                        case '3':
                            terminal.writer().println("You chose Accusative case.");
                            terminal.writer().flush();
                            break;
                        case 'Q':
                        case 'q':
                            terminal.writer().println("Exiting application.");
                            terminal.writer().flush();
                            return; // Exit the application
                        default:
                            terminal.writer().println("Invalid option. Please choose 1, 2, or Q.");
                            terminal.writer().flush();
                            break;
                    }
                }
            }
        }
    }
}