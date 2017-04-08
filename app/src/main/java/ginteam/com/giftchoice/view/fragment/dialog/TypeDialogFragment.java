package ginteam.com.giftchoice.view.fragment.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import ginteam.com.giftchoice.R;
import ginteam.com.giftchoice.view.callback.CallBackPersonType;


/**
 * Created by sergej on 31.03.17.
 */

public class TypeDialogFragment extends DialogFragment implements View.OnClickListener {
    private Button mButtonNumberOk;
    private Button mButtonNumberCancel;
    private RadioGroup mRadioGroup;


    public static TypeDialogFragment newInstance() {
        return new TypeDialogFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_type, container, false);
        mButtonNumberOk = (Button) view.findViewById(R.id.button_add_type_person);
        mButtonNumberOk.setOnClickListener(this);
        mButtonNumberCancel = (Button) view.findViewById(R.id.button_cancel_type_person);
        mButtonNumberCancel.setOnClickListener(this);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.radio_group_type);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add_type_person: {
                int type = 1;
                switch (mRadioGroup.getCheckedRadioButtonId()) {
                    case R.id.radio_button_man:
                        type = 1;
                        break;
                    case R.id.radio_button_woman:
                        type = 2;
                        break;
                    case R.id.radio_button_child:
                        type = 3;
                        break;
                }
                CallBackPersonType activity = (CallBackPersonType) getActivity();
                activity.onFinishTypeDialog(type);
                this.dismiss();
                break;
            }
            case R.id.button_cancel_type_person: {
                this.dismiss();
                break;
            }
        }
    }
}

