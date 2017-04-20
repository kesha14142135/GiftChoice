package ginteam.com.giftchoice.data;

import java.util.List;

import ginteam.com.giftchoice.model.Person;

/**
 * Created by sergej on 07.02.17.
 */

public interface PersonsDataSource {

    void readAll(CallbackPersons callback);

    interface CallbackPersons {

        void onSuccess(List<Person> persons);

        void onFailure();
    }

    void addPerson(Person person, CallBackNewPerson callBack);

    interface CallBackNewPerson {

        void onSuccess(Long insertId);

        void onFailure();
    }

    void updateFieldPassedTest(long personId, boolean flag);
}
