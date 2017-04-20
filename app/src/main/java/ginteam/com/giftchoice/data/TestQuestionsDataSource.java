package ginteam.com.giftchoice.data;

import java.util.List;

import ginteam.com.giftchoice.model.Answer;
import ginteam.com.giftchoice.model.Question;

/**
 * Created by sergej on 11.04.17.
 */

public interface TestQuestionsDataSource {

    void getQuestions(CallBackQuestions callBack);

    interface CallBackQuestions {

        void onSuccess(List<Question> questions);

        void onFailure();
    }
}
