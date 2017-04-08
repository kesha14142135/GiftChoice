package ginteam.com.giftchoice.view.activity;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import ginteam.com.giftchoice.R;
import ginteam.com.giftchoice.contract.NewPersonContract;
import ginteam.com.giftchoice.model.Person;
import ginteam.com.giftchoice.presenter.NewPersonPresenter;
import ginteam.com.giftchoice.view.callback.CallBackPersonBirthday;
import ginteam.com.giftchoice.view.callback.CallBackPersonType;
import ginteam.com.giftchoice.view.fragment.dialog.TypeDialogFragment;
import ginteam.com.giftchoice.view.fragment.dialog.CalendarDialogFragment;


public class NewPerson extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener, CallBackPersonBirthday, CallBackPersonType, NewPersonContract.View {

    private RelativeLayout mRelativeLayout;
    private Button mButtonAddPersonStartTest;
    private Button mButtonAddPerson;
    private EditText mEditTextName;
    private EditText mEditTextType;
    private EditText mEditTextBirthday;
    private DialogFragment mCalendarDialogFragment;
    private DialogFragment mTypeDialogFragment;
    private Person mPerson;
    private NewPersonContract.Presenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_person);
        mPerson = new Person();
        initializationOfVariables();
        mPresenter = new NewPersonPresenter();
        mPresenter.attachView(this);
    }

    private void initializationOfVariables() {
        mPresenter = new NewPersonPresenter();
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relative_layout_add_person);
        mButtonAddPerson = (Button) findViewById(R.id.button_add_person);
        mButtonAddPerson.setOnClickListener(this);
        mButtonAddPersonStartTest = (Button) findViewById(R.id.button_add_person_start_test);
        mButtonAddPersonStartTest.setOnClickListener(this);
        mEditTextName = (EditText) findViewById(R.id.edit_text_person_name);

        mEditTextType = (EditText) findViewById(R.id.edit_text_person_type);
        mEditTextType.setOnFocusChangeListener(this);
        mEditTextType.setOnClickListener(this);

        mEditTextBirthday = (EditText) findViewById(R.id.edit_text_person_birthday);
        mEditTextBirthday.setOnFocusChangeListener(this);
        mEditTextBirthday.setOnClickListener(this);

        mCalendarDialogFragment = CalendarDialogFragment.newInstance();
        mCalendarDialogFragment.setStyle(R.style.CardView, R.style.Theme_AppCompat_DayNight_Dialog_Alert);

        mTypeDialogFragment = TypeDialogFragment.newInstance();
        mTypeDialogFragment.setStyle(R.style.CardView, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_text_person_type: {
                mTypeDialogFragment.show(
                        getFragmentManager(),
                        getResources().getString(R.string.number_dialog)
                );
                break;
            }
            case R.id.edit_text_person_birthday: {
                mCalendarDialogFragment.show(
                        getFragmentManager(),
                        getResources().getString(R.string.number_dialog)
                );
                break;
            }
            case R.id.button_add_person_start_test: {
                mPresenter.addPerson(mPerson);
                break;
            }
            case R.id.button_add_person: {
                mPresenter.addPerson(mPerson);
                break;
            }

        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            switch (v.getId()) {
                case R.id.edit_text_person_birthday: {
                    mCalendarDialogFragment.show(
                            getFragmentManager(),
                            getResources().getString(R.string.number_dialog)
                    );
                    break;
                }
                case R.id.edit_text_person_type: {
                    mTypeDialogFragment.show(
                            getFragmentManager(),
                            getResources().getString(R.string.number_dialog)
                    );
                    break;
                }
            }
        }
    }

    @Override
    public void onFinishTypeDialog(int type) {
        mEditTextType.setText(type);
        mPerson.setType(type);
    }

    @Override
    public void onFinishBirthdayDialog(int year, int month, int day) {
        Calendar calendar = new GregorianCalendar(year, month, day);
        SimpleDateFormat format = new SimpleDateFormat("dd / MM / yyyy");
        mEditTextBirthday.setText(format.format(calendar.getTime()));
        mPerson.setDay(day);
        mPerson.setMonth(month);
        mPerson.setDay(year);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showError(String message) {
        Snackbar.make(mRelativeLayout, message, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void successfulAddition() {

    }
}

