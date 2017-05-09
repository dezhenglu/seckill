package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ludz on 2017/5/7/007.
 * 配置Spring和junit整合,junit启动时加载SpringIOC容器
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit,spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    //注入Dao实现类依赖
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void queryById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
        /**
         * 1000元秒杀iPhone6s
         Seckill{seckillId=1000, name='1000元秒杀iPhone6s', number=100, startTime=Sat May 06 00:00:00 CST 2017, endTime=Sun May 07 00:00:00 CST 2017, createTime=Sat May 06 23:52:23 CST 2017}
         */
    }

    @Test
    public void reduceNumber() throws Exception {
        Date killDate = new Date();
        int updateCount = seckillDao.reduceNumber(1000L,killDate);
        System.out.println("updateCount:" + updateCount);
    }

    @Test
    public void queryAll() throws Exception {
        List<Seckill> seckills = seckillDao.queryAll(0,100);
        for (Seckill seckill: seckills) {
            System.out.println(seckill);
        }
        /**
         * Seckill{seckillId=1000, name='1000元秒杀iPhone6s', number=100, startTime=Sat May 06 00:00:00 CST 2017, endTime=Sun May 07 00:00:00 CST 2017, createTime=Sat May 06 23:52:23 CST 2017}
         Seckill{seckillId=1001, name='500元秒杀iPad Air2', number=200, startTime=Sat May 06 00:00:00 CST 2017, endTime=Sun May 07 00:00:00 CST 2017, createTime=Sat May 06 23:52:23 CST 2017}
         Seckill{seckillId=1002, name='300元秒杀小米5', number=300, startTime=Sat May 06 00:00:00 CST 2017, endTime=Sun May 07 00:00:00 CST 2017, createTime=Sat May 06 23:52:23 CST 2017}
         Seckill{seckillId=1003, name='200元秒杀红米Node', number=400, startTime=Sat May 06 00:00:00 CST 2017, endTime=Sun May 07 00:00:00 CST 2017, createTime=Sat May 06 23:52:23 CST 2017}
         */
    }

}