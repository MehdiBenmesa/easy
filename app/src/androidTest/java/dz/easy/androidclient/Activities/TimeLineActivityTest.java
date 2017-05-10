package dz.easy.androidclient.Activities;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Abderahmane on 03/05/2017.
 */
public class TimeLineActivityTest {
    @Test
    public void initView() throws Exception {
        TimeLineActivity time = new TimeLineActivity();
        boolean result;
        try {
            time.initView();
            result = true;
        }
        catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        assertTrue(result);
    }

    @Test
    public void setDataListItems() throws Exception {
        TimeLineActivity time = new TimeLineActivity();
        boolean result;
        try {
            time.setDataListItems();
            result = true;
        }
        catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        assertTrue(result);
    }

}