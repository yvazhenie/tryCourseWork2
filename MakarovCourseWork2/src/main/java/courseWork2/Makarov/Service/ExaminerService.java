package courseWork2.Makarov.Service;

import courseWork2.Makarov.model.Question;

import java.util.Collection;
import java.util.Set;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
