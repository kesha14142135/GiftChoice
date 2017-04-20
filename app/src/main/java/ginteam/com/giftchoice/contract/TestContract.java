package ginteam.com.giftchoice.contract;

import java.util.List;

import ginteam.com.giftchoice.model.Answer;
import ginteam.com.giftchoice.model.Question;


/**
 * Created by sergej on 08.02.17.
 */

public interface TestContract {

    interface View extends BaseContract.View {

        void showQuestion(Question testQuestion);

        void completeText();
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void recordingAnswerToQuestion(Answer answer);

        void getTestQuestions();

    }

}
