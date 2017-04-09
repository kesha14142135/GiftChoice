package ginteam.com.giftchoice.data;

import ginteam.com.giftchoice.model.Person;

/**
 * Created by sergej on 08.04.17.
 */

public class NewPersonDataBaseSource implements NewPersonDataSource {

    @Override
    public void addPerson(Person person, CallBackNewPerson callBack) {
        try {
            Person newPerson = person;
            newPerson.save();
            callBack.onSuccess();
        } catch (Exception e) {
            callBack.onFailure();
        }
    }
}
