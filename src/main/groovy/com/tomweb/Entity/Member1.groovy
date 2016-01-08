package com.tomweb.Entity

import com.tomweb.core.entity.BaseEntity

/**
 *
 *
 * @author Jeff Huang
 *
 * @date 2015/12/24 0024 14:47
 *
 */
/**
 * Created with IntelliJ IDEA. 
 * Anthor: Administrator 
 * Date: 2015/12/24 0024 
 * Time: 14:47 
 */
class Member1 extends BaseEntity{

    Member1(){
        println(this)
    }

    /**
     * TYPE Enum
     */
    static enum TYPE {
        INVEST("投资人", "invest"),
        BORROW("借款人", "borrow"),
        SPECIAL("特约用户", "special"),
        ADMIN("管理员", "admin")

        public String description
        public String value

        public TYPE(String description, String value) {
            this.description = description
            this.value = value
        }

        public String toString() {
            return this.description
        }
    }

    /**
     * STATUS Enum
     */
    static enum VALIDATION_STATUS {
        NONE("未认证", "none"),
        PROCESS("认证中", "process"),
        VERIFIED("认证通过", "verified"),

        public String description
        public String value

        public VALIDATION_STATUS(String description, String value) {
            this.description = description
            this.value = value
        }

        public String toString() {
            return this.description
        }
    }

    /*登录账户*/
    String username

    /*登录密码*/
    String password

    /*支付密码*/
    String outPassword

    /*用户头像*/
    String avatar

    /*用户类型*/
    String type = TYPE.INVEST.value

    /*用户昵称*/
    String nickname

    /*邮箱*/
    String email

    /*手机*/
    String mobile

    /*真实姓名*/
    String realName

    /*身份证号码*/
    String idCard

    /*邮箱认证状态*/
    String emailStatus = VALIDATION_STATUS.NONE.value

    /*手机认证状态*/
    String mobileStatus = VALIDATION_STATUS.NONE.value

    /*身份认证状态*/
    String idCardStatus = VALIDATION_STATUS.NONE.value

    /*注册IP*/
    String regIp

    /*注册时间*/
    Date regTime = new Date();

    /*最后登录IP*/
    String lastLoginIp

    /*最后登录时间*/
    Date lastLoginTime

    /*是否启用*/
    boolean isEnabled = true

    /*是否内部用户*/
    boolean isInside = false

    /*盐值*/
    String salt

    /*是否是电销客户*/
    boolean isCustomer=false

    /**创建人*/
    Long createdBy = 0

    /**创建时间*/
    Date dateCreated = new Date()

    /**最后修改人*/
    Long updatedBy = 0

    /**最后修改时间*/
    Date lastUpdated = new Date()
}
