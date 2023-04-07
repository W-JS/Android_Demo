package com.wjs.android.demo.utils;

import android.util.Log;

import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件读取
 */
public class PropertiesUtils {

    //1、配置文件的位置在assets资源目录下
    private final static String mPropertiesPath = "/assets/version-commit.properties";
    //2、配置文件的位置在源代码根目录(src下)
    //private final static String mPropertiesPath = "/global.properties";

    public static Properties getProperties(String propertiesPath) {
        Properties props = new Properties();
        try {
            InputStream in = PropertiesUtils.class.getResourceAsStream(propertiesPath);
            props.load(in);
        } catch (Exception e) {
            Log.e("PropertiesUtils", "getProperties: ", e);
        }
        return props;
    }

    public static String getGitCommitInfo() {
        Properties properties = PropertiesUtils.getProperties(mPropertiesPath);
        return "branch:" + properties.getProperty("branch") + "," +
                "commitId:" + properties.getProperty("commitId") + "," +
                "commitText:" + properties.getProperty("commitText") + "," +
                "commitTime:" + properties.getProperty("commitTime") + "," +
                "commitAuthorName:" + properties.getProperty("commitAuthorName") + "," +
                "commitAuthorEmail:" + properties.getProperty("commitAuthorEmail") + "," +
                "buildPackageTime:" + properties.getProperty("buildPackageTime");
    }

}
