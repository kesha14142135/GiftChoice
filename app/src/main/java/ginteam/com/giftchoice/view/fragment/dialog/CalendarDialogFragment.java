package ginteam.com.giftchoice.view.fragment.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Date;

import ginteam.com.giftchoice.R;
import ginteam.com.giftchoice.view.callback.CallBackPersonBirthday;

/**
 * Created by sergej on 30.03.17.
 */

public class CalendarDialogFragment extends DialogFragment implements View.OnClickListener {
    private Button mButtonNumberOk;
    private Button mButtonNumberCancel;
    private DatePicker mDatePickerBirthday;

    public static CalendarDialogFragment newInstance() {
        CalendarDialogFragment fragment = new CalendarDialogFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_calendar, container, false);
        getDialog().setCanceledOnTouchOutside(false);
        mDatePickerBirthday = (DatePicker) view.findViewById(R.id.date_picker_update_birthday);
        mDatePickerBirthday.setMaxDate(new Date().getTime());
        mButtonNumberOk = (Button) view.findViewById(R.id.button_add_update_birthday);
        mButtonNumberOk.setOnClickListener(this);
        mButtonNumberCancel = (Button) view.findViewById(R.id.button_cancel_update_birthday);
        mButtonNumberCancel.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add_update_birthday: {
                CallBackPersonBirthday activity = (CallBackPersonBirthday) getActivity();
                activity.onFinishBirthdayDialog(
                        mDatePickerBirthday.getYear(),
                        mDatePickerBirthday.getMonth(),
                        mDatePickerBirthday.getDayOfMonth()
                );
                this.dismiss();
                break;
            }
            case R.id.button_cancel_update_birthday: {
                this.dismiss();
                break;
            }
        }
    }
}
