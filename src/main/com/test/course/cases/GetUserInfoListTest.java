package test.course.cases;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.course.config.TestConfig;
import test.course.model.GetUserListCase;
import test.course.model.User;
import test.course.utils.DatabaseUtil;

import java.io.IOException;
import java.util.List;

/**
 * xx模块
 */
public class GetUserInfoListTest {
    @Test(dependsOnGroups="loginTrue",description = "获取的用户信息")
    public void getUserListInfo() throws IOException, InterruptedException {

        SqlSession session = DatabaseUtil.getSqlSession();
        GetUserListCase getUserListCase = session.selectOne("getUserListCase",1);
        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getUserListUrl);





        //下边为写完接口的代码

        String resultJson = getJsonResult(getUserListCase);
        /**
         * 可以先讲
         */
        Thread.sleep(2000);
        List<User> userList = session.selectList(getUserListCase.getExpected(),getUserListCase);
        for(User u : userList){
           System.out.println("list获取的user:"+u.toString());
        }
        List<User> userListJson = JSON.parseArray(resultJson,User.class);
        Assert.assertEquals(userListJson.size(),userList.size());
       for(int i = 0;i<userListJson.size();i++){
           User user = userList.get(i);
           User user1 = userListJson.get(i);
            Assert.assertEquals(user1.toString(), user.toString());
        }
    }

    private String getJsonResult(GetUserListCase getUserListCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserListUrl);
        JSONObject param = new JSONObject();
        param.put("username",getUserListCase.getUsername());
        param.put("sex",getUserListCase.getSex());
        param.put("age",getUserListCase.getAge());
        //设置请求头信息 设置header
        post.setHeader("content-type","application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //设置cookies
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        //声明一个对象来进行响应结果的存储
        String result;
        //执行post方法
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");


        return result;

    }
}
