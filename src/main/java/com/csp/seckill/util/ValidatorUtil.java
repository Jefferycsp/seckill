package com.csp.seckill.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author csp
 * @description: TODO
 * @date 2019/10/14 9:36
 */
public class ValidatorUtil {

    private static final Pattern MOBILE_PATTERN = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        Matcher matcher = MOBILE_PATTERN.matcher(str);
        return matcher.matches();
    }
}
