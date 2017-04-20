package ginteam.com.giftchoice.data;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

import ginteam.com.giftchoice.R;
import ginteam.com.giftchoice.model.Answer;
import ginteam.com.giftchoice.model.Question;

/**
 * Created by sergej on 11.04.17.
 */

public class TestQuestionsDataBaseSource implements TestQuestionsDataSource {

    private int mPosition;
    private Resources mResources;

    public TestQuestionsDataBaseSource(Context context) {
        mResources = context.getResources();
    }

    @Override
    public void getQuestions(CallBackQuestions callBack) {
        mPosition = 0;
        String[] questionsNames = mResources.getStringArray(R.array.text_for_question);
        String[] questionsHashTags = mResources.getStringArray(R.array.text_for_question);
        TypedArray questionsImages = mResources.obtainTypedArray(R.array.pictures_for_questions);
        List<Question> mQuestionsForTest = new ArrayList<>();
        while (questionsNames.length > mPosition) {
            mQuestionsForTest.add(
                    new Question(
                            questionsNames[mPosition],
                            questionsHashTags[mPosition],
                            0,
                            questionsImages.getResourceId(mPosition,-1)
                    )
            );
            mPosition++;
        }
        callBack.onSuccess(mQuestionsForTest);
    }
}
