package org.seckill.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ludz on 2017/5/7/007.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceImplTest {

    @Test
    public void executeSeckillProcedure() throws Exception {
        long id = 1003L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer.isExposed()){
            logger.info("exposer={}",exposer);
            long phone = 13080773902L;
            String md5 = exposer.getMd5();
            SeckillExecution seckillExecution = seckillService.executeSeckillProcedure(id,phone,md5);
            logger.info(seckillExecution.getStateInfo());
        }else{
            logger.warn("exposer={}",exposer);
        }
    }

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}",list);
    }

    @Test
    public void getById() throws Exception {
        long id = 1000L;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}",seckill);
    }

    @Test
    public void exportSeckillLogic() throws Exception {
        long id = 1000L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer.isExposed()){
            logger.info("exposer={}",exposer);
            long phone = 13080773902L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(id,phone,md5);
                logger.info("result:{}",seckillExecution);
                //seckillExecution:SeckillExecution{seckillId=1000, state=1, stateInfo='秒杀成功', successKilled=SuccessKilled{seckillId=1000, userPhone=13080773902, state=0, createTime=Sun May 07 22:40:25 CST 2017}}
            }catch (RepeatKillException e) {
                logger.error(e.getMessage());
                //org.seckill.exception.RepeatKillException: seckill repeated//org.seckill.exception.RepeatKillException: seckill repeated
            }catch (SeckillCloseException e){
                logger.error(e.getMessage());
                //org.seckill.exception.RepeatKillException: seckill repeated//org.seckill.exception.RepeatKillException: seckill repeated
            }catch (SeckillException e){
                logger.error(e.getMessage());
                //org.seckill.exception.RepeatKillException: seckill repeated//org.seckill.exception.RepeatKillException: seckill repeated
            }
        }else{
            logger.warn("exposer={}",exposer);
        }
    }
}