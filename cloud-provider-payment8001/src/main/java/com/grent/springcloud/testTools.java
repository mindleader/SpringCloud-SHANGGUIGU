package com.grent.springcloud;

import org.apache.commons.lang.StringUtils;

public class testTools {
    public static void main(String[] args) {
        String s = desensitizedAddress("王家庄（轻轨站）");
        System.out.println(s);

    }
    public static String desensitizedAddress(String address) {
        if (StringUtils.isNotBlank(address)) {
            return StringUtils.left(address, 3).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(address, address.length() - 11), StringUtils.length(address), "*"), "***"));
        }
        return address;
    }
}
