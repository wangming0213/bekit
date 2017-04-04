/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2017-04-04 19:09 创建
 */
package top.bekit.common.method;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 方法执行器
 */
public abstract class MethodExecutor {
    // 目标方法
    private Method targetMethod;
    // 是否有入参
    private boolean hasParameter;

    public MethodExecutor(Method targetMethod) {
        this.targetMethod = targetMethod;
        this.hasParameter = targetMethod.getParameterTypes().length > 0;
    }

    /**
     * 执行方法
     *
     * @param obj  被执行的对象
     * @param args 需传入目标方法的参数（当目标方法无入参时，会忽略args）
     * @return 目标方法返回的结果
     * @throws Throwable 执行过程中发生任何异常都会往外抛
     */
    protected Object execute(Object obj, Object[] args) throws Throwable {
        try {
            if (hasParameter) {
                return targetMethod.invoke(obj, args);
            } else {
                return targetMethod.invoke(obj);
            }
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

    /**
     * 获取目标方法入参类型
     */
    public Class[] getParameterTypes() {
        return targetMethod.getParameterTypes();
    }

    /**
     * 获取目标方法返回类型
     */
    public Class getReturnType() {
        return targetMethod.getReturnType();
    }
}