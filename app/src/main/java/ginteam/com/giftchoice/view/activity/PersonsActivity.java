package ginteam.com.giftchoice.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.common.collect.Lists;

import java.util.List;

import ginteam.com.giftchoice.R;
import ginteam.com.giftchoice.contract.PersonsContract;
import ginteam.com.giftchoice.model.Person;
import ginteam.com.giftchoice.presenter.PersonsPresenter;
import ginteam.com.giftchoice.view.adapter.PersonsAdapter;
import ginteam.com.giftchoice.view.callback.CallBackPerson;


public class PersonsActivity extends AppCompatActivity implements View.OnClickListener, PersonsContract.View {

    private RecyclerView mRecyclerView;
    private PersonsContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons);
        updateViewDependencies();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_action_button_add_home: {
                Intent intent = new Intent(getContext(), NewPersonActivity.class);
                getContext().startActivity(intent);
                break;
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.getPersons();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showError(String message) {
        Snackbar.make(findViewById(R.id.coordinator_layout_home), message, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void showPersons(List<Person> persons) {
        PersonsAdapter adapter = new PersonsAdapter(Lists.reverse(persons), new CallBackPerson() {
            @Override
            public void successTest(long personId, int type) {
                Intent intent = new Intent(getContext(), TestActivity.class);
                intent.putExtra(getResources().getString(R.string.ID), Long.valueOf(personId));
                getContext().startActivity(intent);
            }

            @Override
            public void successPerson(long personId, int type) {
                Intent intent = new Intent(getContext(), InfoAboutPersonActivity.class);
                intent.putExtra(getResources().getString(R.string.ID), Long.valueOf(personId));
                getContext().startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(adapter);
    }

    private void updateViewDependencies() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_home);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        FloatingActionButton floatingButton = (FloatingActionButton) findViewById(R.id.floating_action_button_add_home);
        floatingButton.setOnClickListener(this);

        mPresenter = new PersonsPresenter();
        mPresenter.attachView(this);
        mPresenter.getPersons();
    }
}
