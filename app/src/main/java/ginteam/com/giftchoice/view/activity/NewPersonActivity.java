package ginteam.com.giftchoice.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import ginteam.com.giftchoice.R;
import ginteam.com.giftchoice.contract.NewPersonContract;
import ginteam.com.giftchoice.model.Person;
import ginteam.com.giftchoice.presenter.NewPersonPresenter;
import ginteam.com.giftchoice.view.callback.CallBackPersonBirthday;
import ginteam.com.giftchoice.view.callback.CallBackPersonType;
import ginteam.com.giftchoice.view.fragment.dialog.TypePersonDialogFragment;
import ginteam.com.giftchoice.view.fragment.dialog.CalendarDialogFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class NewPersonActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener, CallBackPersonBirthday, CallBackPersonType, NewPersonContract.View, Validator.ValidationListener {
    @NotEmpty
    private EditText mEditTextName;
    @NotEmpty
    private EditText mEditTextType;
    @NotEmpty
    private EditText mEditTextBirthday;
    private DialogFragment mCalendarDialogFragment;
    private DialogFragment mTypeDialogFragment;
    private Person mPerson;
    private NewPersonContract.Presenter mPresenter;
    private boolean mFlagTest = false;
    private Validator mValidator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_person);
        updateViewDependencies();
        mPerson = new Person();
        mPresenter = new NewPersonPresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_text_person_type: {
                mTypeDialogFragment.show(
                        getSupportFragmentManager(),
                        getResources().getString(R.string.number_dialog)
                );
                break;
            }
            case R.id.image_back_button: {
                onBackPressed();
                break;
            }
            case R.id.edit_text_person_birthday: {
                mCalendarDialogFragment.show(
                        getSupportFragmentManager(),
                        getResources().getString(R.string.number_dialog)
                );
                break;
            }
            case R.id.button_add_person_start_test: {
                mFlagTest = true;
                mPerson.setName(mEditTextName.getText().toString());
                mValidator.validate();
                break;
            }
            case R.id.button_add_person: {
                mPerson.setName(mEditTextName.getText().toString());
                mValidator.validate();
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
                            getSupportFragmentManager(),
                            getResources().getString(R.string.number_dialog)
                    );
                    break;
                }
                case R.id.edit_text_person_type: {
                    mTypeDialogFragment.show(
                            getSupportFragmentManager(),
                            getResources().getString(R.string.number_dialog)
                    );
                    break;
                }
            }
        }
    }

    @Override
    public void onFinishTypeDialog(int type) {
        String[] typeRes = getResources().getStringArray(R.array.type);
        mEditTextType.setText(typeRes[type]);
        mPerson.setType(type);
    }

    @Override
    public void onFinishBirthdayDialog(int year, int month, int day) {
        Calendar calendar = new GregorianCalendar(year, month, day);
        SimpleDateFormat format = new SimpleDateFormat("dd / MM / yyyy");
        mEditTextBirthday.setText(format.format(calendar.getTime()));
        mPerson.setDay(day);
        mPerson.setMonth(month);
        mPerson.setYear(year);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showError(String message) {
        Snackbar.make(findViewById(R.id.relative_layout_add_person), message, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void successfulAddition(Long insertIdPerson) {
        if (mFlagTest) {
            Intent intent = new Intent(getContext(), TestActivity.class);
            intent.putExtra(getResources().getString(R.string.ID), Long.valueOf(insertIdPerson));
            getContext().startActivity(intent);
            finish();
        } else {
            onBackPressed();
        }
    }

    private void updateViewDependencies() {
        mPresenter = new NewPersonPresenter();

        findViewById(R.id.button_add_person).setOnClickListener(this);
        findViewById(R.id.button_add_person_start_test).setOnClickListener(this);
        findViewById(R.id.image_back_button).setOnClickListener(this);

        mEditTextName = (EditText) findViewById(R.id.edit_text_person_name);

        mEditTextType = (EditText) findViewById(R.id.edit_text_person_type);
        mEditTextType.setOnFocusChangeListener(this);
        mEditTextType.setOnClickListener(this);

        mEditTextBirthday = (EditText) findViewById(R.id.edit_text_person_birthday);
        mEditTextBirthday.setOnFocusChangeListener(this);
        mEditTextBirthday.setOnClickListener(this);

        mCalendarDialogFragment = CalendarDialogFragment.newInstance();
        mCalendarDialogFragment.setStyle(R.style.CardView, R.style.Theme_AppCompat_DayNight_Dialog_Alert);

        mTypeDialogFragment = TypePersonDialogFragment.newInstance();
        mTypeDialogFragment.setStyle(R.style.CardView, R.style.Theme_AppCompat_DayNight_Dialog_Alert);

        mValidator = new Validator(this);
        mValidator.setValidationListener(this);
    }

    @Override
    public void onValidationSucceeded() {
        mPresenter.addPerson(mPerson);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Snackbar.make(findViewById(R.id.relative_layout_add_person), message, Snackbar.LENGTH_LONG)
                        .show();
            }
        }
    }
}

