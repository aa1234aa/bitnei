package controlcenter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我全部的get方法")
public class MyGetMethod {

    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    @ApiOperation(value = "通过这个方法可以获取到Cookies",httpMethod = "GET")
    public String getCookies(HttpServletResponse response){
        //HttpServerletRequest 装请求信息的类
        //HttpServerletResponse  装响应信息的类
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "恭喜你获得cookies信息成功";
    }
    /*携带cookie*/
    @RequestMapping(value = "/get/with/cookies",method = RequestMethod.GET)
    @ApiOperation(value = "携带cookie",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        if (Objects.isNull(cookies)){
            return "必须带";
        }
        for (Cookie cookie:cookies){
            return "带了";
        }
        return "必须带来";
    }


}
