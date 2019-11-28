package test.course.cases;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.course.config.TestConfig;
import test.course.model.InterfaceName;
import test.course.model.LoginCase;
import test.course.utils.ConfigFile;
import test.course.utils.DatabaseUtil;

import java.io.IOException;

/**
 * xx模块
 */
public class LoginTest {
    @BeforeTest(groups = "loginTrue",description = "测试准备工作，获取httpcliect对象")
    private void beforeTest(){
        TestConfig.defaultHttpClient=new DefaultHttpClient();
        //TestConfig.getUserInfoUrl=ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.addUserUrl=ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
        TestConfig.loginUrl=ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.getUserListUrl=ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.getUserInfoUrl=ConfigFile.getUrl(InterfaceName.GETUSERINFO);
    }
    @Test(groups = "loginTrue",description = "用户成功登陆接口")
    public void loginTrue() throws IOException {

        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase",3);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);

        //下边的代码为写完接口的测试代码
        String result = getResult(loginCase);
        //处理结果，就是判断返回结果是否符合预期
        Assert.assertEquals(loginCase.getExpected(),result);

    }
    @Test(groups = "loginFalse",description = "用户失败登陆接口")
    public void loginFalse() throws IOException {

        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase",1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);

        //下边的代码为写完接口的测试代码
        String result = getResult(loginCase);
        //处理结果，就是判断返回结果是否符合预期
        Assert.assertEquals(loginCase.getExpected(),result);

    }
    private String getResult(LoginCase loginCase) throws IOException {
        //下边的代码为写完接口的测试代码
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        JSONObject param = new JSONObject();
        param.put("username",loginCase.getUsername());
        param.put("password",loginCase.getPassword());
        //设置请求头信息 设置header
        post.setHeader("content-type","application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //声明一个对象来进行响应结果的存储
        String result;
        //执行post方法
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        //获取响应结果
        result= EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        TestConfig.store = TestConfig.defaultHttpClient.getCookieStore();
        return result;
    }



}
