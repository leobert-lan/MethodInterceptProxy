package osp.leobert.android.proxy.method.interceptor;

import osp.leobert.android.proxy.method.MethodProxy;

public interface MethodInterceptor {
	
	Object intercept(Object object, Object[] args, MethodProxy methodProxy)
			throws Exception;

}
