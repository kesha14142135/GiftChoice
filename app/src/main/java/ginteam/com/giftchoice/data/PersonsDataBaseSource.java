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
    public void readAll(PersonsCallback callback) {
        //List<Person> persons = Person.listAll(Person.class);
    }

    @Override
    public void addPerson(Person person) {
        Person book = person;
        book.save();
    }
}