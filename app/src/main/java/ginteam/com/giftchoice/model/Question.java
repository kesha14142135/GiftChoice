package ginteam.com.giftchoice.model;

import com.orm.SugarRecord;

/**
 * Created by sergej on 07.03.17.
 */

public class Question extends SugarRecord {
    private String mName;
    private String mHashtag;
    private int mQuestionCategories;
    private int mImageUrl;

    public Question(String name, String hashtag, int typePerson, int url) {
        mName = name;
        mHashtag = hashtag;
        mQuestionCategories = typePerson;
        mImageUrl = url;
    }

    public String getHashtag() {
        return mHashtag;
    }

    public void setHashtag(String hashtag) {
        mHashtag = hashtag;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getTypeUser() {
        return mQuestionCategories;
    }

    public void setTypeUser(int typeUser) {
        mQuestionCategories = typeUser;
    }

    public int getUrl() {
        return mImageUrl;
    }

    public void setUrl(int imageUrl) {
        mImageUrl = imageUrl;
    }
}
