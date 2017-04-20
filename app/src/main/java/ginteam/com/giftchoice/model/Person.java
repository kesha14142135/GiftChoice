package ginteam.com.giftchoice.model;

import com.orm.SugarRecord;

/**
 * Created by sergej on 07.02.17.
 */
public class Person extends SugarRecord {
    private String mName;
    private int mType;
    private boolean mPassedTheTest;
    private int mDay;
    private int mMonth;
    private int mYear;

    public Person() {

    }

    public Person(String name, int type, boolean passedTheTest, int day, int month, int year) {
        mName = name;
        mType = type;
        mPassedTheTest = passedTheTest;
        mDay = day;
        mMonth = month;
        mYear = year;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setType(int type) {
        mType = type;
    }

    public int getType() {
        return mType;
    }

    public boolean getPassedTheTest() {
        return mPassedTheTest;
    }

    public void setPassedTheTest(boolean passedTheTest) {
        mPassedTheTest = passedTheTest;
    }

    public int getDay() {
        return mDay;
    }

    public void setDay(int day) {
        mDay = day;
    }

    public int getMonth() {
        return mMonth;
    }

    public void setMonth(int month) {
        mMonth = month;
    }

    public int getYear() {
        return mYear;
    }

    public void setYear(int year) {
        mYear = year;
    }
}
