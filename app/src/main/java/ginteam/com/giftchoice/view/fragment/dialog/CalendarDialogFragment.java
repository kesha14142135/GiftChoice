package ginteam.com.giftchoice.view.fragment.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;

import java.util.Date;

import ginteam.com.giftchoice.R;
import ginteam.com.giftchoice.view.callback.CallBackPersonBirthday;

/**
 * Created by sergej on 30.03.17.
 */

public class CalendarDialogFragment extends DialogFragment implements View.OnClickListener {
    private DatePicker mDatePickerBirthday;
    private CallBackPersonBirthday mCallback;

    public static CalendarDialogFragment newInstance() {
        return new CalendarDialogFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (CallBackPersonBirthday) context;
        } catch (ClassCastException ignored) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_calendar, container, false);
        getDialog().setCanceledOnTouchOutside(false);

        view.findViewById(R.id.button_add_update_birthday).setOnClickListener(this);
        view.findViewById(R.id.button_cancel_update_birthday).setOnClickListener(this);

        mDatePickerBirthday = (DatePicker) view.findViewById(R.id.date_picker_update_birthday);
        mDatePickerBirthday.setMaxDate(new Date().getTime());
        return view;
    }

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
       getDialog().getWindow()
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add_update_birthday: {
                if (mCallback != null) {
                    mCallback.onFinishBirthdayDialog(
                            mDatePickerBirthday.getYear(),
                            mDatePickerBirthday.getMonth(),
                            mDatePickerBirthday.getDayOfMonth()
                    );
                }
                dismiss();
                break;
            }
            case R.id.button_cancel_update_birthday: {
                dismiss();
                break;
            }
        }
    }
}
