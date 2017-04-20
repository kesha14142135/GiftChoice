package ginteam.com.giftchoice.model;

import com.orm.SugarRecord;

/**
 * Created by sergej on 07.03.17.
 */

public class Answer extends SugarRecord {

    private long mIdPerson;
    private boolean mSelected;
    private Question mQuestion;

    public Answer() {

    }

    public Answer(String name, String hashtag, int idPerson, int url, int typePerson, boolean selected) {
        mQuestion = new Question(name, hashtag, typePerson, url);
        mSelected = selected;
        mIdPerson = idPerson;
    }

    public Answer(Question question, long idPerson, boolean selected) {
        mQuestion = new Question(question.getName(), question.getHashtag(), question.getTypeUser(), question.getUrl());
        mSelected = selected;
        mIdPerson = idPerson;
    }

    public boolean getSelected() {
        return mSelected;
    }

    public void setSelected(boolean selected) {
        mSelected = selected;
    }

    public long getIdPerson() {
        return mIdPerson;
    }

    public void setIdPerson(int idPerson) {
        mIdPerson = idPerson;
    }

    public Question getQuestion() {
        return mQuestion;
    }

    public void setQuestion(Question question) {
        mQuestion = question;
    }


}
