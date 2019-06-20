package com.key.common.constant;

import com.google.common.collect.Sets;

import java.util.Set;

public class Constants {
    public static final String CURRENT_USER = "currentUser";

    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    public static final String IDCARD = "idcard";
    public static final String PHONE = "phone";
    public static final String ITEMNAME = "itemname";
    public static final String ITEMVALUE = "itemvalue";
    public static final Integer DICTIONARY_ROOT_ID = 0;

    public interface JWTPayload {
        String EXP = "expiretime";
        String PAYLOAD = "payload";
    }

    public interface HolidayFeededutionType {
        // 平均扣费
        int AVERAGE = 0;
        // 固定扣费
        int FIXATION = 1;
    }

    public interface EmployeeState {
        // 签约中
        int CONTACTING = 0;
        // 没有签约
        int UNCONTACT = 1;
    }
    public interface EmployeeProtocolType {
        // 签约
        int CONTACT = 0;
        // 解约
        int UNCONTACT = 1;
    }
    public interface UnitProtocolType {
        // 签约
       int CONTACT = 0;
       // 解约
       int UN_CONTACT = 1;
    }
    public interface UnitStatus {
        int CONTRACTING = 0; // 合约中
        int UN_CONTRACTING = 1; // 无合约
        int FREEZE = 2; // 冻结
    }

    public enum LogType {
        LOGIN("登录"),
        SELECT("查询"),
        DELETE("删除"),
        UPDATE("更改"),
        INSERT("插入");

        private String name;

        private LogType(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
    public interface ProductListOrderBy {
        Set<String> PRICE_AEC_DESC = Sets.newHashSet("price_desc", "price_asc");
    }
    public interface Cart {
        int CHECKED = 1; // 选中状态
        int UN_CHECKED = 0; // 未选中状态

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    }
    public interface Roles {
        int ROLE_CUSTOMER = 0; //普通用户
        int ROLE_ADMIN = 1; // 管理员
    }

    public enum ProductStatusEnum {

        ON_SALE(1, "在线");

        private String value;
        private int code;

        private ProductStatusEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    public interface AbnormalStatus {
        // 异动状态
        int ABNORMAL = 0;
        // 解除异动状态
        int UN_ABNORMAL = 1;
    }

    public interface ProtocolAccessoryStatus {
        // 没有完成
        int un_accessory_status = 0;
        // 表示完成
        int accessory_statused = 1;
    }

    public interface InsuranceException {
        // 正常按照比例计算
        int no_exception = 0;
        // 不按照比例计算
        int exception = 1;
    }
}
