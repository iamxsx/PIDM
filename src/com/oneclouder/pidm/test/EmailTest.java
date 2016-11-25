package com.oneclouder.pidm.test;

import com.oneclouder.pidm.util.MailUtil;
import org.junit.Test;

/**
 * Created By IntelliJ IDEA
 *
 * @Author: AngryFeng
 * @Date: 16-10-12
 * @Time: 下午4:47
 */
public class EmailTest {
    @Test
    public void testSendEmail() throws Exception {
        MailUtil.sendMail("553783307@qq.com", "12345");
        System.out.println("hello");
    }
}
