package com.jasrsir.logintable.controllers;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by usuario on 5/10/16.
 */

public class LoginTable_Controller implements ILoginMvc {

    public static final int OK = 0;
    public static final int PASSWORD_DIGIT = 1;
    public static final int PASSWORD_CASE = 2;
    public static final int PASSWORD_LENGTH = 3;

    /**
     *
     * @param user Username
     * @param pass Password
     * @return  0 -> OK
     *          1 -> PASSWORD_DIGIT
     *          2 -> PASSWORD_CAsE
     *          3 -> PASSWORD_LENGTH
     */

    public int validateCredentials(String user, String pass) {
        int result = 0;
        if (!pass.matches("^.{0,}([0-9])+.{0,}$")) {
            result = PASSWORD_DIGIT;
        } else if (!pass.matches("^.+[a-zA-Z]+.+$")) {
            result = PASSWORD_CASE;
        } else if (pass.length() <= 7) {
            result = PASSWORD_LENGTH;
        }
        return result;
    }
}
