package courseWork2.Makarov.Implements;

import courseWork2.Makarov.Service.ExaminerService;
import courseWork2.Makarov.Service.QuestionService;
import courseWork2.Makarov.exception.BadRequestException;
import courseWork2.Makarov.model.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
@Service

public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }


    @Override
    public Collection<Question> getQuestions(int amount) {
        if (questionService.getAll().size() < amount) {
            throw new BadRequestException("Исчерпано число вопросов");

        }
        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(questionService.getRandomQuestion());

        }
        return questions;
    }

}
