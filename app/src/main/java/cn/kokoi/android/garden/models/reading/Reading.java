package cn.kokoi.android.garden.models.reading;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by zkl on 2015/5/15.
 */
public class Reading implements Serializable {

    public static final String TAG = "units";

    private String mRootDir = "";
    private ArrayList<Unit> mUnits;

    public Reading(){
        mUnits = new ArrayList<>();
    }

    public void addUnit(Unit unit){
        mUnits.add(unit);
    }

    public ArrayList<Unit> getUnits() {
        return mUnits;
    }

    public void setUnits(ArrayList<Unit> units) {
        mUnits = units;
    }

    public String getRootDir() {
        return mRootDir;
    }

    public void setRootDir(String rootDir) {
        mRootDir = rootDir;
    }

    @Override
    public String toString() {
        return "Reading{" +
                "mRootDir='" + mRootDir + '\'' +
                ", mUnits=" + mUnits +
                '}';
    }
}
