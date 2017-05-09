package org.seckill.exception;

/**
 * Created by ludz on 2017/5/7/007.
 */
public class SeckillException extends  RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }

}
