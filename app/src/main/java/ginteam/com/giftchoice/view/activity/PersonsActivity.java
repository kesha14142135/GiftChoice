package ginteam.com.giftchoice.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

import ginteam.com.giftchoice.R;
import ginteam.com.giftchoice.contract.PersonsContract;
import ginteam.com.giftchoice.model.Person;
import ginteam.com.giftchoice.presenter.PersonsPresenter;
import ginteam.com.giftchoice.view.adapter.PersonsAdapter;
import ginteam.com.giftchoice.view.callback.CallBackPerson;


public class PersonsActivity extends AppCompatActivity implements View.OnClickListener, PersonsContract.View {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private FloatingActionButton mFloatingButton;
    private CoordinatorLayout mCoordinatorLayout;
    private PersonsContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initializationOfVariables();
    }

    private void initializationOfVariables() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_home);
        mLayoutManager = new LinearLayoutManager(this);
        mFloatingButton = (FloatingActionButton) findViewById(R.id.floating_action_button_add_home);
        mFloatingButton.setOnClickListener(this);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout_home);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (recyclerView.getVisibility() == View.VISIBLE && dy > 0) {
                    mFloatingButton.hide();
                } else if (recyclerView.getVisibility() == View.VISIBLE && dy < 0) {
                    mFloatingButton.show();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        mPresenter = new PersonsPresenter();
        mPresenter.attachView(this);
        mPresenter.getPersons();
    }

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.floating_action_button_add_home: {

                        break;
                    }
                }
            }

    @Override
    protected void onStart() {
        super.onStart();
      //  mPresenter.getPersons();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showError(String message) {
        Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void showPersons(List<Person> persons) {

        List<Person> persons1 = new ArrayList<>();
        persons1.add(new Person("Саша Муравей",0,0,10,3,1994,0));
        persons1.add(new Person("Алина Праведная",1,1,10,3,1994,1));
        //do not forgot about this line
        List<Person> personsRevers = Lists.reverse(persons);
        mAdapter = new PersonsAdapter(persons1, getContext(), new CallBackPerson() {
            @Override
            public void successTest(int id, int type) {

            }

            @Override
            public void successUser(int id, int type) {

            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }
}