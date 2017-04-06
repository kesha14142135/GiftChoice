package ginteam.com.giftchoice.contract;

import java.util.List;

import ginteam.com.giftchoice.model.Person;


/**
 * Created by sergej on 08.02.17.
 */

public interface PersonsContract {

    interface View extends BaseContract.View {

        void showPersons(List<Person> persons);

    }

    interface Presenter extends BaseContract.Presenter<View> {

        void addPerson(String name, int type);

        void getPersons();
    }

}
