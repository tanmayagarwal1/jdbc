package com.uhg.opioid.loader;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public interface OpioidDao {
    ArrayList<HashMap<String, Object>> getStage();
    int postStage(ArrayList<Opioid> list);
    int postStageMain();
    ArrayList<HashMap<String, Object>> getProd();
    int postProd(ArrayList<Opioid> list);
    int postProdMain();

}
