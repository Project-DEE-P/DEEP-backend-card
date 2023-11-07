package com.dragonest.deepbackendcard.global.statics;

public class ValidMessageConstants {

    private ValidMessageConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String CARD_ID_NOT_BLANK = "명함 아이디는 Null, 공백, 띄어쓰기를 허용하지 않습니다.";

    public static final String TEMPLATE_NOT_BLANK = "템플릿은 Null, 공백, 띄어쓰기를 허용하지 않습니다.";
    public static final String NAME_NOT_BLANK = "이름은 Null, 공백, 띄어쓰기를 허용하지 않습니다.";
    public static final String POSITION_NOT_BLANK = "직책은 Null, 공백, 띄어쓰기를 허용하지 않습니다.";
    public static final String DEPARTMENT_NOT_BLANK = "부서는 Null, 공백, 띄어쓰기를 허용하지 않습니다.";
    public static final String PHONE_NOT_BLANK = "전화번호는 Null, 공백, 띄어쓰기를 허용하지 않습니다.";
    public static final String EMAIL_NOT_BLANK = "이메일은 Null, 공백, 띄어쓰기를 허용하지 않습니다.";
    public static final String COMPANY_NOT_BLANK = "회사는 Null, 공백, 띄어쓰기를 허용하지 않습니다.";
    public static final String ADDRESS_NOT_BLANK = "주소는 Null, 공백, 띄어쓰기를 허용하지 않습니다.";
    public static final String MOBILE_NOT_BLANK = "모바일은 Null, 공백, 띄어쓰기를 허용하지 않습니다.";
    public static final String TELL_NOT_BLANK = "전화는 Null, 공백, 띄어쓰기를 허용하지 않습니다.";
    public static final String FAX_NOT_BLANK = "팩스는 Null, 공백, 띄어쓰기를 허용하지 않습니다.";
    public static final String HOMEPAGE_NOT_BLANK = "홈페이지는 Null, 공백, 띄어쓰기를 허용하지 않습니다.";


    public static final String CARD_TYPE_NOT_NULL = "명함 유형은 Null을 허용하지 않습니다.";
    public static final String CARD_TYPE_ENUM_VALUE = "명함 클래스는 Enum 값 중 하나여야 합니다.";
}
