package com.uhg.opioid.loader;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class OpioidService implements OpioidDao {

    @Autowired
    @Qualifier("jdbcTemplate2")
    JdbcTemplate stage;

    @Autowired
    @Qualifier("jdbcTemplate2")
    JdbcTemplate prod;

    @Override
    public ArrayList<HashMap<String, Object>> getStage() {
        String query = "SELECT * from connecture_opioid_stage";
        ArrayList<HashMap<String, Object>> list = new ArrayList<>(stage.query(query, new MyMapper()));
        return list;
    }

    @Override
    public int postStage(ArrayList<Opioid> list) {
        String query = "INSERT INTO connecture_opioid(Proxy_NDC, Marketing_Opioid_1_Month_Supply, Marketing_Opioid_7_Day_Supply, Creation_date, Last_updated_date, Last_Modified_date) VALUES(?, ?, ?, ?, ?, ?)";
        for(Opioid i : list){
            int r = stage.update(query, i.getValue1(), i.getValue2(), i.getValue3(), Auditor.dateProvider(), Auditor.dateProvider(), Auditor.dateProvider());
        }
        return postStageMain();

    }

    @Override
    public int postStageMain() {
        String query = "INSERT INTO connecture_opioid_stage(Proxy_NDC, Marketing_Opioid_1_Month_Supply, Marketing_Opioid_7_Day_Supply, Creation_date, Last_updated_date, Last_Modified_date) select * from connecture_opioid";
        int r = stage.update(query);
        return r;
    }

    @Override
    public ArrayList<HashMap<String, Object>> getProd() {
        String query = "SELECT * from connecture_opioid_stage";
        ArrayList<HashMap<String, Object>> list = new ArrayList<>(prod.query(query, new MyMapper()));
        return list;
    }

    @Override
    public int postProd(ArrayList<Opioid> list) {
        String query = "INSERT INTO connecture_opioid(Proxy_NDC, Marketing_Opioid_1_Month_Supply, Marketing_Opioid_7_Day_Supply, Creation_date, Last_updated_date, Last_Modified_date) VALUES(?, ?, ?, ?, ?, ?)";
        for(Opioid i : list){
            int r = prod.update(query, i.getValue1(), i.getValue2(), i.getValue3(), Auditor.dateProvider(), Auditor.dateProvider(), Auditor.dateProvider());
        }
        return postProdMain();

    }

    @Override
    public int postProdMain() {
        String query = "INSERT INTO connecture_opioid_stage(Proxy_NDC, Marketing_Opioid_1_Month_Supply, Marketing_Opioid_7_Day_Supply, Creation_date, Last_updated_date, Last_Modified_date) select * from connecture_opioid";
        int r = prod.update(query);
        return r;

    }

}
