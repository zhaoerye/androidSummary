package com.example.sunshine.myapplication.http.Model;



/**
 * Created by liyu on 2016/9/20.
 */

public class HisUser {

    /**
     * USER_ID : U002259
     * USER_NAME : 牟立佳
     * USER_DEPT : 1325257
     * INPUT_CODE : MLJ
     * USER_JOB : 护士
     * RESERVED01 : 1
     * CREATE_DATE : 2011/10/21 8:24:42
     */

    private String USER_ID;
    private String USER_NAME;
    private String USER_DEPT;
    private String INPUT_CODE;
    private String USER_JOB;
    private String RESERVED01;
    private String CREATE_DATE;

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getUSER_DEPT() {
        return USER_DEPT;
    }

    public void setUSER_DEPT(String USER_DEPT) {
        this.USER_DEPT = USER_DEPT;
    }

    public String getINPUT_CODE() {
        return INPUT_CODE;
    }

    public void setINPUT_CODE(String INPUT_CODE) {
        this.INPUT_CODE = INPUT_CODE;
    }

    public String getUSER_JOB() {
        return USER_JOB;
    }

    public void setUSER_JOB(String USER_JOB) {
        this.USER_JOB = USER_JOB;
    }

    public String getRESERVED01() {
        return RESERVED01;
    }

    public void setRESERVED01(String RESERVED01) {
        this.RESERVED01 = RESERVED01;
    }

    public String getCREATE_DATE() {
        return CREATE_DATE;
    }

    public void setCREATE_DATE(String CREATE_DATE) {
        this.CREATE_DATE = CREATE_DATE;
    }
}
