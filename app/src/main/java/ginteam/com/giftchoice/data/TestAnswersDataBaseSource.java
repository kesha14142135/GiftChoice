package ginteam.com.giftchoice.data;

import android.content.Context;
import android.content.res.Resources;
import java.util.List;
import ginteam.com.giftchoice.model.Answer;

/**
 * Created by sergej on 11.04.17.
 */

public class TestAnswersDataBaseSource implements TestAnswersDataSource {

    private Resources mResources;

    public TestAnswersDataBaseSource(Context context) {
        mResources = context.getResources();
    }


    @Override
    public void addAnswers(List<Answer> answers) {
        for (Answer answer : answers) {
            answer.save();
        }
    }

    @Override
    public void deleteAllAnswers(int personId, CallBackAnswers callBack) {

    }

    @Override
    public void updateAnswer(int personId, int hashTag, CallBackAnswers callBack) {

    }
}
