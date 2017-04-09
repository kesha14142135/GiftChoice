package ginteam.com.giftchoice.data;

import ginteam.com.giftchoice.model.Person;

/**
 * Created by sergej on 08.04.17.
 */

public interface NewPersonDataSource {

    void addPerson(Person person, CallBackNewPerson callBack);

    public interface CallBackNewPerson {

        void onSuccess();

        void onFailure();
    }

}
