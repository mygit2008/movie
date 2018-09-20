package example.com.utilslibrary.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public class VerifyUtils {
    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,20}$";

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_TEL = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 正则：邮箱
     */
    public static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    /**
     * 手机号验证
     *
     * @param context
     * @param tel
     * @return
     */
    public static boolean isMobile(Context context, String tel) {
        if (TextUtils.isEmpty(tel)) {
            Toast.makeText(context, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return false;
        } else if (tel.matches(REGEX_TEL)) {

        } else {
            Toast.makeText(context, "手机号不合法", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * 验证密码
     *
     * @param context
     * @param pwd
     * @return
     */
    public static boolean isPassword(Context context, String pwd) {
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        } else if (pwd.matches(REGEX_PASSWORD)) {

        } else {
            Toast.makeText(context, "密码必须在6-20位并且不包含特殊字符", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * 判断邮箱是否合法
     *
     * @param email
     * @return
     */
    public static boolean isEmail(Context context, String email) {
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(context, "邮箱地址不能为空", Toast.LENGTH_SHORT).show();
            return false;
        } else if (email.matches(REGEX_EMAIL)) {

        } else {
            Toast.makeText(context, "邮箱格式不正确", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
