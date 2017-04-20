package ginteam.com.giftchoice.data;

import java.util.List;

import ginteam.com.giftchoice.model.Answer;
import ginteam.com.giftchoice.model.Question;

/**
 * Created by sergej on 11.04.17.
 */

public interface TestAnswersDataSource {

    void addAnswers(List<Answer> answers);

    void deleteAllAnswers(int personId, CallBackAnswers callBack);

    void updateAnswer(int personId, int hashTag, CallBackAnswers callBack);

    interface CallBackAnswers {

        void onSuccess();

        void onFailure();
    }

}
