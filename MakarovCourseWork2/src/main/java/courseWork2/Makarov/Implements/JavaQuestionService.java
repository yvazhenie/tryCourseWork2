package courseWork2.Makarov.Implements;

import courseWork2.Makarov.Service.QuestionService;
import courseWork2.Makarov.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;
@Service

public class JavaQuestionService implements QuestionService {
    Set<Question> questions = new HashSet<>();
    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        questions.add(newQuestion);
        return (newQuestion);
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return (question);
    }

    @Override
    public Question remove(Question question) {
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        int indexRandom = random.nextInt( questions.size());
        return questions.stream().toList().get(indexRandom);
    }
}
