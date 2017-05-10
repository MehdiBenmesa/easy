package dz.easy.androidclient;

/**
 * Created by Mon pc on 04/04/2017.
 */

import java.util.ArrayList;

public class HeaderInfo {

    private String name;
    private ArrayList<ModuleInfo> productList = new ArrayList<ModuleInfo>();;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<ModuleInfo> getProductList() {
        return productList;
    }
    public void setProductList(ArrayList<ModuleInfo> productList) {
        this.productList = productList;
    }

}