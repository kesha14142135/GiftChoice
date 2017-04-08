package ginteam.com.giftchoice.presenter;

import android.content.Context;

import ginteam.com.giftchoice.R;
import ginteam.com.giftchoice.contract.NewPersonContract;
import ginteam.com.giftchoice.model.Person;

/**
 * Created by sergej on 07.04.17.
 */

public class NewPersonPresenter implements NewPersonContract.Presenter {

    private NewPersonContract.View mView;
    private Context mContext;

    @Override
    public void attachView(NewPersonContract.View view) {
        mView = view;
        mContext = view.getContext();
    }

    @Override
    public void detachView() {

    }

    @Override
    public void addPerson(Person person) {
        if (!"".equals(person.getName()) &&
                0 != person.getType() &&
                0 != person.getDay() &&
                0 != person.getMonth() &&
                0 != person.getYear()) {
        } else {
            mView.showError(mContext.getResources().getString(R.string.error_fill_on_the_field));
        }
    }
}
