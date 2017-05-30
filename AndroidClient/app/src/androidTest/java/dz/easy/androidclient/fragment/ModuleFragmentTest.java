package dz.easy.androidclient.fragment;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Boukhetta on 03/05/2017.
 */
public class ModuleFragmentTest {
    @Test
    public void getModulesByStudent() throws Exception {
        ModuleFragment moduleFragment = new ModuleFragment();
        boolean test;
        try {
            moduleFragment.getModulesByStudent();
            test = true;
        }catch (Exception e){
            e.printStackTrace();
            test=false;
        }
        assertTrue(test);
    }

}
