package com.up.iotbackend.entity;

public enum ResultEnum {
    SUCCESS(200,"success"),
    LOGIN_NONE(301,"not_login"),
    LOGIN_INVALID(302,"not_login"),
    LOGIN_OUT(303,"not_login"),
    PASSWORD_ERROR(304,"password_incorrect"),
    ACCOUNT_NOT(305,"account_not_exist"),
    ACCOUNT_EXISTS(306,"account_exist"),
    FAILURE(400,"failure"),
    PARAMS(401,"parameter_incorrect"),
    MESSAGE_NOT(404,"message_not_exist"),
    INSERT_FAILURE(405,"insert_failure"),
    UPDATE_FAILURE(406,"update_failure"),
    DELETE_FAILURE(407,"delete_failure"),
    CREATE_FAILURE(408,"create_failure"),
    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
