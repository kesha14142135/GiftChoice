package ginteam.com.giftchoice.presenter;

import java.util.ArrayList;
import java.util.List;

import ginteam.com.giftchoice.R;
import ginteam.com.giftchoice.contract.PersonsContract;
import ginteam.com.giftchoice.data.PersonsDataBaseSource;
import ginteam.com.giftchoice.data.PersonsDataSource;
import ginteam.com.giftchoice.model.Person;

/**
 * Created by sergej on 08.02.17.
 */

public class PersonsPresenter implements PersonsContract.Presenter {

    private PersonsContract.View mView;
    private PersonsDataSource mPersonsDataSource;

    @Override
    public void getPersons() {
        List<Person> person = new ArrayList<>();
       mView.showPersons(person);
    }

    @Override
    public void attachView(PersonsContract.View view) {
        mView = view;
        mPersonsDataSource = new PersonsDataBaseSource(mView.getContext());
    }

    @Override
    public void detachView() {

    }
}
