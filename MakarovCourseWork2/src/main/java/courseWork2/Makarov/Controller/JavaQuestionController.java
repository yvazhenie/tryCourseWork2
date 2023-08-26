package courseWork2.Makarov.Controller;

import courseWork2.Makarov.Service.QuestionService;
import courseWork2.Makarov.model.Question;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")

public class JavaQuestionController {
    private final QuestionService questionService;

    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }


    @GetMapping("/add")
    public Question add (@RequestParam String question, @RequestParam String answer) {
        return questionService.add(question, answer);
    }
    @GetMapping("/remove")
    public Question remove (@RequestParam String question,@RequestParam String answer) {
        Question questionDelete = new Question(question, answer);
        return questionService.remove(questionDelete);
    }
    @GetMapping
    public Collection<Question> get() {
        return questionService.getAll();
    }
}
