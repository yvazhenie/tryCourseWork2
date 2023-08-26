package courseWork2.Makarov.Service;

import courseWork2.Makarov.Implements.ExaminerServiceImpl;
import courseWork2.Makarov.Implements.JavaQuestionService;
import courseWork2.Makarov.model.Question;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.BDDMockito.given;
@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService questionService;
    @InjectMocks
    ExaminerServiceImpl examinerService;
    private final Collection<Question> questionTest = new ArrayList<>(List.of(
            new Question("Sha", "Pka"),
            new Question("Svi", "Ter"),
            new Question("Va", "len")));
    private final List<Question> randomQuestion = new ArrayList<>(List.of(
            new Question("Ran", "Dom")));

    @Test
    void getQuestions() {
        //подготовка
        final int amount = 1;
        given(questionService.getAll()).willReturn(questionTest);
        given(questionService.getRandomQuestion()).willReturn(randomQuestion.get(0));
        //ожидаеммый
        Collection<Question> actual = examinerService.getQuestions(amount);
        //тест
        Assertions.assertEquals(randomQuestion, actual);
    }
}