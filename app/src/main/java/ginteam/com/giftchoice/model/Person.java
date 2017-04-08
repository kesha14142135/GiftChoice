package ginteam.com.giftchoice.model;
import com.orm.SugarRecord;

/**
 * Created by sergej on 07.02.17.
 */
public class Person extends SugarRecord{

    private String mName;
    private int mType;
    private int mPassedTheTest;
    private int mDay;
    private int mMonth;
    private int mYear;
    private int mId;

    public Person() {
        this("", 0, 0, 0, 0, 1940, -1);
    }

    public Person(String name, int type, int passedTheTest, int day, int month, int year, int id) {
        mName = name;
        mType = type;
        mPassedTheTest = passedTheTest;
        mDay = day;
        mMonth = month;
        mYear = year;
        mId = id;
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

    public int getPassedTheTest() {
        return mPassedTheTest;
    }

    public void setPassedTheTest(int passedTheTest) {
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

    public int getIdPerson() {
        return mId;
    }

    public void setIdPerson(int id) {
        mId = id;
    }


}
