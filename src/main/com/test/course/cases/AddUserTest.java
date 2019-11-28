package test.course.cases;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.course.config.TestConfig;
import test.course.model.AddUserCase;
import test.course.model.User;
import test.course.utils.DatabaseUtil;

import java.io.IOException;

public class AddUserTest {

    @Test(dependsOnGroups = "loginTrue",description = "新增成功")
    public void addUserTrue()throws IOException,InterruptedException{
        //数据库执行sql,拿case
        SqlSession session= DatabaseUtil.getSqlSession();
        AddUserCase addUserCase=session.selectOne("addUserCase",1);
        System.out.println(addUserCase.toString());
        System.out.println(TestConfig.addUserUrl);
        String result=getResult(addUserCase);
        //此处要抛异常
        Thread.sleep(2000);
        SqlSession session2= DatabaseUtil.getSqlSession();
        //验证返回的结果
       User user=session2.selectOne("addUser",addUserCase);

        System.out.println(user.toString());
        Assert.assertEquals(addUserCase.getExpected(),result);

    }
    private String getResult(AddUserCase addUserCase)throws IOException{
        //url
        HttpPost post=new HttpPost(TestConfig.addUserUrl);
        //json值
        JSONObject param=new JSONObject();
        param.put("username" ,addUserCase.getUsername());
        param.put("password",addUserCase.getPassword());
        param.put("sex",addUserCase.getSex());
        param.put("age",addUserCase.getAge());
        param.put("permission",addUserCase.getPermission());
        param.put("isDelete",addUserCase.getIsDelete());
        param.put("expect",addUserCase.getExpected());
        //信息头
        post.setHeader("content-type","application/json");
        //参数加到方法中
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //设置cookie
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        //声明方法存储结果
        String result;
        //执行post
        HttpResponse response=TestConfig.defaultHttpClient.execute(post);
        //获取响应结果
        result= EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        return result;
    }

}
