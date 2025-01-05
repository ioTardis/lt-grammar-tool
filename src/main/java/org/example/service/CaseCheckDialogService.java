package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CaseCheckDialogService {

    static final int WORDS_COUNT = 3;

    // TODO: make it more case-agnostic, accept interface
    private final NominativeCaseService nominativeCaseService;

    public void initiateCaseCheckDialog(Terminal terminal) {
        terminal.writer().println("Let's try to conjugate a word in Lithuanian!");

        final var lineReader = LineReaderBuilder.builder().terminal(terminal).build();

        var wordsToConjugate = nominativeCaseService.getFixedAmountOfNominativeCases(WORDS_COUNT);
        wordsToConjugate.forEach(word -> {
            terminal.writer().println("Please conjugate the word: " + word.getSingular());
            terminal.writer().println("Your answer: ");
            terminal.flush();

            String answer;

            answer = lineReader.readLine();
            if (!answer.equals(word.getPlural())) {
                terminal.writer().println("Incorrect! The correct answer is: " + word.getPlural());
            }

            terminal.writer().println("Correct!");
        });
    }
}
