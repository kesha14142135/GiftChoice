package ginteam.com.giftchoice.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import ginteam.com.giftchoice.R;
import ginteam.com.giftchoice.model.Person;
import ginteam.com.giftchoice.view.callback.CallBackPerson;

/**
 * Created by sergej on 09.02.17.
 */

public class PersonsAdapter extends RecyclerView.Adapter<PersonsAdapter.PersonViewHolder> {

    private List<Person> mPersons;
    private CallBackPerson mCallBackPerson;

    public PersonsAdapter(List<Person> persons, CallBackPerson callBack) {
        mPersons = persons;
        mCallBackPerson = callBack;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.relative_layout_person,
                parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, final int position) {
        holder.mTextViewName.setText(mPersons.get(position).getName());
        switch (mPersons.get(position).getType()) {
            case 0:
                holder.mImageViewPerson.setImageResource(R.drawable.ic_man);
                break;
            case 1:
                holder.mImageViewPerson.setImageResource(R.drawable.ic_woman);
                break;
            case 2:
                holder.mImageViewPerson.setImageResource(R.drawable.ic_baby);
                break;
        }
        Calendar calendar = new GregorianCalendar(
                mPersons.get(position).getYear(),
                mPersons.get(position).getMonth(),
                mPersons.get(position).getDay()
        );
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
        holder.mTextViewBirthday.setText(format.format(calendar.getTime()));
        if (!mPersons.get(position).getPassedTheTest()) {
            holder.mButtonTestPerson.setVisibility(View.VISIBLE);
            holder.mButtonTestPerson.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallBackPerson.successTest(mPersons.get(position).getId(), mPersons.get(position).getType());
                }
            });
            holder.mButtonGiftPerson.setVisibility(View.INVISIBLE);
        } else {
            holder.mButtonTestPerson.setVisibility(View.INVISIBLE);
            holder.mButtonGiftPerson.setVisibility(View.VISIBLE);
            holder.mRelativeLayoutPerson.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallBackPerson.successPerson(mPersons.get(position).getId(), mPersons.get(position).getType());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mPersons.size();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout mRelativeLayoutPerson;
        private TextView mTextViewName;
        private TextView mTextViewBirthday;
        private Button mButtonTestPerson;
        private Button mButtonGiftPerson;
        private ImageView mImageViewPerson;

        public PersonViewHolder(View itemView) {
            super(itemView);
            mRelativeLayoutPerson = (RelativeLayout) itemView.findViewById(R.id.relative_layout_recycler_person);
            mTextViewName = (TextView) itemView.findViewById(R.id.text_view_card_person_name);
            mTextViewBirthday = (TextView) itemView.findViewById(R.id.text_view_card_person_birthday);
            mButtonTestPerson = (Button) itemView.findViewById(R.id.button_card_person_test);
            mButtonGiftPerson = (Button) itemView.findViewById(R.id.button_card_person_gift);
            mImageViewPerson = (ImageView) itemView.findViewById(R.id.image_card_view_person);
        }
    }
}
