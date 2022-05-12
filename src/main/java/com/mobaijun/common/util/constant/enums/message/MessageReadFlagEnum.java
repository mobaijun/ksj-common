package com.mobaijun.common.util.constant.enums.message;

import lombok.Getter;

/**
 * software：IntelliJ IDEA 2022.1
 * enum name: MessageReadFlagEnum
 * enum description：消息阅读状态
 *
 * @author MoBaiJun 2022/5/12 14:07
 */
@Getter
public enum MessageReadFlagEnum {
    /**
     * 未读
     */
    UNREAD(0, "未读"),

    /**
     * 已读
     */
    READ(1, "已读");

    private final Integer code;

    private final String name;

    MessageReadFlagEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(Integer code) {
        if (code == null) {
            return null;
        }
        for (MessageReadFlagEnum flagEnum : MessageReadFlagEnum.values()) {
            if (flagEnum.getCode().equals(code)) {
                return flagEnum.name;
            }
        }
        return null;
    }
}
