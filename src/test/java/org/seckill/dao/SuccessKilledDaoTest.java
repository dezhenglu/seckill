package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by ludz on 2017/5/7/007.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Resource
    SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() throws Exception {
        // 第一次 1
        // 第二次 0
        long id = 1000L;
        long phone = 1881234123L;
        int insertCount = successKilledDao.insertSuccessKilled(id,phone);
        System.out.println("insertCount=" + insertCount);
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        long id = 1000L;
        long phone = 1881234123L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id,phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
        /**
         * SuccessKilled{seckillId=1000, userPhone=13312341234, state=-1, createTime=Sun May 07 03:41:58 CST 2017}
         Seckill{seckillId=1000, name='1000元秒杀iPhone6s', number=99, startTime=Sun May 07 03:13:35 CST 2017, endTime=Mon May 08 00:00:00 CST 2017, createTime=Sat May 06 23:52:23 CST 2017}
         */
    }

}