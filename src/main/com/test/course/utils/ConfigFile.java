package test.course.utils;

import org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig;
import test.course.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * xx模块
 */
public class ConfigFile {
    private static ResourceBundle bundle=ResourceBundle.getBundle("application",Locale.CHINA);
    public static String getUrl(InterfaceName name){
        String address=bundle.getString("test.url");
        String uri="";
        String testUrl;
        if(name==InterfaceName.GETUSERLIST){
            uri=bundle.getString("getUserList.uri");
        }
        testUrl=address+uri;
        return  testUrl;
    }
}
