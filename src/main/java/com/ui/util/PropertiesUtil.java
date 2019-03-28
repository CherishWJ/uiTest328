package com.ui.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
  public static Properties properties = new Properties();
  static {
  loadUrls();
  }

    public static void loadUrls() {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(new File("src/main/resources/config/config.properties"));
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

}
 public static String getCaseFilePath(){
     String caseFilePath=properties.getProperty("cases.url");
     return caseFilePath;
 }
 public static String getRootUrl(){
     String rootUrlFilePath=properties.getProperty("rootUrl");
     return rootUrlFilePath;

 }
    public static String getImportCaseUrl(){
        String importUrlFilePath=properties.getProperty("importCasefile.url");
        return importUrlFilePath;

    }



}
