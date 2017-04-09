package ginteam.com.giftchoice.presenter;

import android.content.Context;

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
    private Context mContext;
    private PersonsDataSource mDatabase;

    @Override
    public void getPersons() {
        mDatabase.readAll(new PersonsDataSource.CallbackPersons() {
            @Override
            public void onSuccess(List<Person> persons) {
                mView.showPersons(persons);
            }

            @Override
            public void onFailure() {
                mView.showError(mContext.getResources().getString(R.string.error_reading_user));
            }
        });
    }

    @Override
    public void attachView(PersonsContract.View view) {
        mView = view;
        mContext = view.getContext();
        mDatabase = new PersonsDataBaseSource(mView.getContext());
    }

    @Override
    public void detachView() {

    }
}
