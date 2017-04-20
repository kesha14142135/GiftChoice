package ginteam.com.giftchoice.data;

import android.content.Context;

import java.util.List;

import ginteam.com.giftchoice.model.Person;


/**
 * Created by sergej on 07.02.17.
 */

public class PersonsDataBaseSource implements PersonsDataSource {

    private Context mContext;

    public PersonsDataBaseSource(Context context) {
        mContext = context;
    }

    @Override
    public void readAll(CallbackPersons callback) {
        try {
            List<Person> persons = Person.listAll(Person.class);
            callback.onSuccess(persons);
        } catch (Exception e) {
            callback.onFailure();
        }
    }

    @Override
    public void addPerson(Person person, CallBackNewPerson callBack) {
            Person newPerson = person;
            callBack.onSuccess(newPerson.save());

    }

    @Override
    public void updateFieldPassedTest(long personId, boolean flag) {
        Person person = Person.findById(Person.class, personId);
        person.setPassedTheTest(flag);
        person.save();
    }
}
