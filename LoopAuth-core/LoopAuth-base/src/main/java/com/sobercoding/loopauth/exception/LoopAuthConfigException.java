package com.sobercoding.loopauth.exception;


/**
 * 配置异常
 * @author: Sober
 */
public class LoopAuthConfigException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public LoopAuthConfigException(LoopAuthExceptionEnum loopAuthExceptionEnum) {
        super(loopAuthExceptionEnum.getMsg());
    }

    public LoopAuthConfigException(LoopAuthExceptionEnum loopAuthExceptionEnum, String detailMsg) {
        super(String.format(loopAuthExceptionEnum.getMsg(),detailMsg));
    }


}
