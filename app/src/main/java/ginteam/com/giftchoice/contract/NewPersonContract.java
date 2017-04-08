package ginteam.com.giftchoice.contract;


import ginteam.com.giftchoice.model.Person;


/**
 * Created by sergej on 08.02.17.
 */

public interface NewPersonContract {

    interface View extends BaseContract.View {

        void successfulAddition();
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void addPerson(Person person);
    }

}
