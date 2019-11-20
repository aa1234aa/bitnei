package test.course.model;

import lombok.Data;

/**
 * xx模块
 */
@Data
public class User {
    private int id;
    private String username;
    private String age;
    private String password;
    private String sex;
    private String permission;
    private String isDelete;

    @Override
    public  String toString(){
        return (
                        "{id:"+id+","+
                        "username:"+username+","+
                        "age:"+age+","+
                        "password:"+password+","+
                        "sex:"+sex+","+
                        "permission:"+permission+","+
                        "isDelete:"+isDelete+"}"
                );
    }

}
