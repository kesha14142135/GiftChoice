package ginteam.com.giftchoice.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ginteam.com.giftchoice.R;
import ginteam.com.giftchoice.contract.TestContract;
import ginteam.com.giftchoice.model.Answer;
import ginteam.com.giftchoice.model.Question;
import ginteam.com.giftchoice.presenter.TestPresenter;

/**
 * Created by sergej on 11.04.17.
 */

public class TestActivity extends AppCompatActivity implements TestContract.View, View.OnClickListener {

    private TestContract.Presenter mPresenter;
    private Question mTestQuestion;
    private TextView mTextViewQuestionName;
    private ImageView mImageQuestion;
    private Long mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        updateViewDependencies();
        Intent intent = getIntent();
        mId = intent.getLongExtra(getResources().getString(R.string.ID),0);

        mPresenter = new TestPresenter(mId);
        mPresenter.attachView(this);
        mPresenter.getTestQuestions();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showQuestion(Question testQuestion) {
        mTestQuestion = testQuestion;
        mTextViewQuestionName.setText(testQuestion.getName());
        mImageQuestion.setImageResource(testQuestion.getUrl());
    }

    @Override
    public void completeText() {
        onBackPressed();
    }

    @Override
    public void onClick(View v) {
        Answer answer = new Answer(mTestQuestion, mId, false);
        answer.setSelected(v.getId() == R.id.button_yes_answer);
        mPresenter.recordingAnswerToQuestion(answer);
    }

    private void updateViewDependencies() {
        findViewById(R.id.button_yes_answer).setOnClickListener(this);
        findViewById(R.id.button_no_answer).setOnClickListener(this);
        mImageQuestion = (ImageView) findViewById(R.id.image_view_question);
        mTextViewQuestionName = (TextView) findViewById(R.id.text_view_question_name);
    }
}
