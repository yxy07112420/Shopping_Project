package com.neuedu.common;

/**
 * 状态码
 */
public class ResponseCord {

    public static final Integer SUCCESS = 0;
    public static final Integer ERROR = 1;
    public static final String CURRENTUSER="current_user";
    //定义用户权限（枚举类型）
    public enum UserEnum{
        USER_ADMIN(0,"管理员"),
        USER_COMMON(1,"普通用户")
        ;
        private Integer code;
        private String desc;

        private UserEnum(){

        }
        private UserEnum(Integer code,String desc){
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
