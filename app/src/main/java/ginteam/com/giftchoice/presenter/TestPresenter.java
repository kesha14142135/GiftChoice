package ginteam.com.giftchoice.presenter;

import java.util.ArrayList;
import java.util.List;

import ginteam.com.giftchoice.contract.TestContract;
import ginteam.com.giftchoice.data.PersonsDataBaseSource;
import ginteam.com.giftchoice.data.PersonsDataSource;
import ginteam.com.giftchoice.data.TestAnswersDataBaseSource;
import ginteam.com.giftchoice.data.TestAnswersDataSource;
import ginteam.com.giftchoice.data.TestQuestionsDataBaseSource;
import ginteam.com.giftchoice.data.TestQuestionsDataSource;
import ginteam.com.giftchoice.model.Answer;
import ginteam.com.giftchoice.model.Question;

/**
 * Created by sergej on 11.04.17.
 */

public class TestPresenter implements TestContract.Presenter {
    private TestContract.View mView;
    private TestQuestionsDataSource mDatabaseQuestions;
    private TestAnswersDataSource mDatabaseAnswers;
    private PersonsDataSource mDatabasePerson;
    private List<Question> mQuestions;
    private List<Answer> mAnswers;
    private long mPersonId;

    public TestPresenter(long personId) {
        mPersonId = personId;
    }

    @Override
    public void attachView(TestContract.View view) {
        mView = view;
        mDatabaseQuestions = new TestQuestionsDataBaseSource(view.getContext());
        mDatabaseAnswers = new TestAnswersDataBaseSource(view.getContext());
        mDatabasePerson = new PersonsDataBaseSource(view.getContext());
        mAnswers = new ArrayList<>();
    }

    @Override
    public void detachView() {

    }

    @Override
    public void recordingAnswerToQuestion(Answer answer) {
        mAnswers.add(answer);
        if (mAnswers.size() == mQuestions.size()) {
            mDatabaseAnswers.addAnswers(mAnswers);
            mDatabasePerson.updateFieldPassedTest(mPersonId, true);
            mView.completeText();
        } else {
            mView.showQuestion(mQuestions.get(mAnswers.size()));
        }
    }

    @Override
    public void getTestQuestions() {

        mDatabaseQuestions.getQuestions(new TestQuestionsDataSource.CallBackQuestions() {
            @Override
            public void onSuccess(List<Question> questions) {
                mQuestions = questions;
                mView.showQuestion(questions.get(0));
            }

            @Override
            public void onFailure() {

            }
        });
    }
}
