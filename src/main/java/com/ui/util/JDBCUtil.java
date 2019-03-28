package com.ui.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class JDBCUtil {

    static Properties properties = new Properties();
    private static Connection conn;

    static {
        try {
            InputStream iStream = new FileInputStream(new File("src/test/resources/properties/jdbc.properties"));
            properties.load(iStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> query(String sql, List<Object> value) {
        Map<String, Object> labelAndValues = new HashMap<String, Object>();
        if (null == conn) {
            getDbInstance();
        }
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            while (resultSet.next()) {
                int size = metaData.getColumnCount();

                for (int i = 0; i < size; i++) {
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    String columnValue = resultSet.getString(columnLabel);
                    labelAndValues.put(columnLabel, columnValue);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return labelAndValues;
    }

    private static void getDbInstance() {
        try {
            String url = properties.getProperty("jdbc.url");
            String username = properties.getProperty("jdbc.username");
            String password = properties.getProperty("jdbc.password");

            conn = DriverManager.getConnection(url, username, password);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
