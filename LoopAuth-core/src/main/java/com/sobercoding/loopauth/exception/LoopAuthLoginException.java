package com.sobercoding.loopauth.exception;

import com.sobercoding.loopauth.util.LoopAuthUtil;

import java.util.Optional;

/**
 * @author: Sober
 */
public class LoopAuthLoginException extends LoopAuthException{

    private static final long serialVersionUID = 1L;

    public LoopAuthLoginException(LoopAuthExceptionEnum loopAuthExceptionEnum) {
        super(loopAuthExceptionEnum);
    }

    public static void isEmpty(Object obj, LoopAuthExceptionEnum loopAuthExceptionEnum){
        Optional.ofNullable(obj)
                .filter(LoopAuthUtil::isNotEmpty)
                .orElseThrow(() -> new LoopAuthLoginException(loopAuthExceptionEnum));
    }

    public static void isTrue(boolean obj, LoopAuthExceptionEnum loopAuthExceptionEnum){
        if (!obj){
            throw new LoopAuthLoginException(loopAuthExceptionEnum);
        }
    }


}
