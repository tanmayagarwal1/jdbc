package com.uhg.opioid.loader;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

public class MyMapper implements RowMapper<HashMap<String, Object>> {

    @Override
    public HashMap<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int cols = md.getColumnCount();
        HashMap<String, Object> map = new HashMap<>();
        for(int i = 1; i <= cols; ++i){
            map.put(md.getColumnName(i), rs.getObject(i));
        }
        return map;

    }
}
