package com.sobercoding.loopauth.rbac.annotation;

import com.sobercoding.loopauth.model.LoopAuthVerifyMode;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 角色认证：必须具有指定角色标识才能进入该方法 
 * <p> 可标注在函数、类上（效果等同于标注在此类的所有方法上） 
 *
 * @author Yun
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface CheckRole {

	/**
	 * 需要校验的角色标识
	 * @return 需要校验的角色标识
	 */
	String[] value() default {};

	/**
	 * 验证模式：AND | OR | non，默认AND
	 * @return 验证模式
	 */
	LoopAuthVerifyMode mode() default LoopAuthVerifyMode.AND;

    /**
     * 账号类型 
     * <p> 建议使用常量，避免因错误拼写带来的bug 
     * @return see note 
     */
	String type() default "";
	
}
