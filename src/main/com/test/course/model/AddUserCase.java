package test.course.model;

import lombok.Data;

/**
 * xx模块
 */
@Data
public class AddUserCase {
    private String username;
    private String password;
    private String sex;
    private String age;
    private String permission;
    private String isDelete;
    private String expected;



}