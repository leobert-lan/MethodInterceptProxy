package osp.leobert.android.proxy.method;

import java.lang.reflect.Method;

import osp.leobert.android.proxy.CallbackFilter;
import osp.leobert.android.proxy.Const;
import osp.leobert.android.proxy.exception.ProxyException;
import osp.leobert.android.proxy.method.interceptor.MethodInterceptor;

public class MethodProxyExecutor {

    @SuppressWarnings({"rawtypes"})
    public static Object executeInterceptor(MethodInterceptor[] interceptors, CallbackFilter callbackFilter, MethodInterceptor interceptor, Class superClass, String methodName,
                                            Class[] argsType, Object[] argsValue, Object object) {
        if (interceptor == null && interceptors == null && callbackFilter == null) {
//			throw new ProxyException("Did not set method interceptor !");
            MethodProxy methodProxy = new MethodProxy(superClass, methodName, argsType);
            return methodProxy.invokeSuper(object, argsValue);
        }
        try {
            if (interceptors != null && callbackFilter != null) {
                MethodProxy methodProxy = new MethodProxy(superClass, methodName, argsType);
                return interceptors[callbackFilter.accept(object.getClass().getDeclaredMethod(methodName, argsType))].intercept(object, argsValue, methodProxy);
            }
            MethodProxy methodProxy = new MethodProxy(superClass, methodName, argsType);
            return interceptor.intercept(object, argsValue, methodProxy);
        } catch (Exception e) {
            throw new ProxyException(e.getMessage());
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Object executeMethod(Class subClass, String methodName, Class[] argsType, Object[] argsValue, Object object) {
        try {
            Method method = subClass.getDeclaredMethod(methodName + Const.SUBCLASS_INVOKE_SUPER_SUFFIX, argsType);
            return method.invoke(object, argsValue);
        } catch (Exception e) {
            throw new ProxyException(e.getMessage());
        }
    }

}
