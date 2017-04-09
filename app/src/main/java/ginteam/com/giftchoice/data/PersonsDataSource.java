package ginteam.com.giftchoice.data;

import java.util.List;

import ginteam.com.giftchoice.model.Person;

/**
 * Created by sergej on 07.02.17.
 */

public interface PersonsDataSource {

    void readAll(CallbackPersons callback);

    void addPerson(Person Person);

    interface CallbackPersons {

        void onSuccess(List<Person> persons);

        void onFailure();
    }

}
