package ginteam.com.giftchoice.view.fragment.dialog;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioGroup;

import ginteam.com.giftchoice.R;
import ginteam.com.giftchoice.view.callback.CallBackPersonType;


/**
 * Created by sergej on 31.03.17.
 */

public class TypePersonDialogFragment extends DialogFragment implements View.OnClickListener {
    private RadioGroup mRadioGroup;
    private CallBackPersonType mCallback;

    public static TypePersonDialogFragment newInstance() {
        return new TypePersonDialogFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (CallBackPersonType) context;
        } catch (ClassCastException ignored) {}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_type, container, false);
        Button buttonNumberOk = (Button) view.findViewById(R.id.button_add_type_person);
        buttonNumberOk.setOnClickListener(this);
        Button buttonNumberCancel = (Button) view.findViewById(R.id.button_cancel_type_person);
        buttonNumberCancel.setOnClickListener(this);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.radio_group_type);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add_type_person: {
                int type = 0;
                switch (mRadioGroup.getCheckedRadioButtonId()) {
                    case R.id.radio_button_man:
                        type = 0;
                        break;
                    case R.id.radio_button_woman:
                        type = 1;
                        break;
                    case R.id.radio_button_child:
                        type = 2;
                        break;
                }
                if (mCallback != null) {
                    mCallback.onFinishTypeDialog(type);
                }
                dismiss();
                break;
            }
            case R.id.button_cancel_type_person: {
                dismiss();
                break;
            }
        }
    }
}

