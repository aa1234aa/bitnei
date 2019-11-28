package test.course.model;

import lombok.Data;

/**
 * xx模块
 */
@Data
public class GetUserInfoCase {
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }

    private int userid;
    private String expected;
}
