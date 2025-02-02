package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Case;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CaseCheckDialogService<T extends Case> {

    static final int WORDS_COUNT = 3;

    private final CaseService<T> caseService;

    public void initiateCaseCheckDialog(Terminal terminal) {
        terminal.writer().println("Let's try to conjugate a word in Lithuanian!");

        final var lineReader = LineReaderBuilder.builder().terminal(terminal).build();

        var wordsToConjugate = caseService.getFixedAmount(WORDS_COUNT);
        wordsToConjugate.forEach(word -> {
            terminal.writer().println("Please conjugate the word: " + word.getSingular());
            terminal.writer().println("Your answer: ");
            terminal.flush();

            String answer;

            answer = lineReader.readLine();
            final var isCorrect = word.getPlural().equals(answer);

            terminal.writer().println(isCorrect
                    ? "Correct!"
                    : "Incorrect! The correct answer is: " + word.getPlural());
        });
    }
}
