package courseWork2.Makarov.Service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import courseWork2.Makarov.Implements.JavaQuestionService;
import courseWork2.Makarov.model.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JavaQuestionServiceTest {
    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        questionService = new JavaQuestionService();
    }


    @Test
    void addString() {
        Question actual = questionService.add("Текст", "Ответ");
        Question expected = new Question("Текст", "Ответ");
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void addModelTest() {
        Question modelTest = new Question("Вопрос", "Ответ");
        Question actual = questionService.add(modelTest);
        Question expected = new Question("Вопрос", "Ответ");
        Assertions.assertEquals(expected, actual);
    }


    @Test
    void removeTest() {
        Question removeTest = new Question("Вопрос", "Ответ");
        Question actual = questionService.remove(removeTest);
        Question expected = removeTest;
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void getAllTest() {
        int actual = questionService.getAll().size();
        int expected = 0;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void exceptionRemoveNotFound() {
        Question removeExceptionTest = new Question("Вопрос", "Ответ");
        Assertions.assertThrows(RuntimeException.class, () -> questionService.remove(removeExceptionTest));
    }

}