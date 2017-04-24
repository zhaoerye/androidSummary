package com.example.sunshine.myapplication.http.Model;

/**
 * Created by liyu on 2016/9/20.
 */

public class User {

    /**
     * USER_ID : U000862
     * LOGIN_NAME : ZQY
     * LOGIN_PWD : 202CB962AC59075B964B07152D234B70
     * USER_NAME : 周琼英
     * DEPT_ID : 426
     * CREATE_DATE : 2016/1/21 16:34:01
     * IS_VALID : null
     * MEMO :
     * DEPT_NAME : 麻醉科
     */

    private String USER_ID;
    private String LOGIN_NAME;
    private String LOGIN_PWD;
    private String USER_NAME;
    private String DEPT_ID;
    private String CREATE_DATE;
    private Object IS_VALID;
    private String MEMO;
    private String DEPT_NAME;

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getLOGIN_NAME() {
        return LOGIN_NAME;
    }

    public void setLOGIN_NAME(String LOGIN_NAME) {
        this.LOGIN_NAME = LOGIN_NAME;
    }

    public String getLOGIN_PWD() {
        return LOGIN_PWD;
    }

    public void setLOGIN_PWD(String LOGIN_PWD) {
        this.LOGIN_PWD = LOGIN_PWD;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getDEPT_ID() {
        return DEPT_ID;
    }

    public void setDEPT_ID(String DEPT_ID) {
        this.DEPT_ID = DEPT_ID;
    }

    public String getCREATE_DATE() {
        return CREATE_DATE;
    }

    public void setCREATE_DATE(String CREATE_DATE) {
        this.CREATE_DATE = CREATE_DATE;
    }

    public Object getIS_VALID() {
        return IS_VALID;
    }

    public void setIS_VALID(Object IS_VALID) {
        this.IS_VALID = IS_VALID;
    }

    public String getMEMO() {
        return MEMO;
    }

    public void setMEMO(String MEMO) {
        this.MEMO = MEMO;
    }

    public String getDEPT_NAME() {
        return DEPT_NAME;
    }

    public void setDEPT_NAME(String DEPT_NAME) {
        this.DEPT_NAME = DEPT_NAME;
    }
}
