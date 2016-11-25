package com.oneclouder.pidm.test;

import com.oneclouder.pidm.n_user.service.impl.NCompanyEmployeeServiceImpl;
import com.oneclouder.pidm.n_user.service.impl.NCompanyServiceImpl;
import com.oneclouder.pidm.util.PinYinUtil;
import org.apache.lucene.util.MathUtil;
import org.junit.Test;

import java.util.Calendar;
import java.util.Random;

/**
 * Created with IDEA
 * Author: AngryFeng
 * Date: 9/13/16
 * Time: 8:52 AM
 */
public class PinYinUtilTest {
    @Test
    public void testConverterToSpell() throws Exception {
        String pinyin = PinYinUtil.converterToSpell("恭喜发财");
        System.out.println(pinyin);
    }

    @Test
    public void testConverterToFirstSpell() throws Exception {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;//获取月份
        int day = calendar.get(Calendar.DATE);//获取日
        int hour = calendar.get(Calendar.HOUR);//小时
        String midfix = new String("" + month + day + hour);
        Random rand = new Random();
        int suffix = rand.nextInt(999)+1000;
        String pinyin = PinYinUtil.converterToFirstSpell("宝力华电力有限公司");
        System.out.println(pinyin.toUpperCase() + "-" + midfix + "-" + suffix);
        System.out.println(NCompanyServiceImpl.genCompanyIdentifiter("宝力华电力有限公司"));
    }
}
