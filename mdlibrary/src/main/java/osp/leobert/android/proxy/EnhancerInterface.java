package osp.leobert.android.proxy;

import osp.leobert.android.proxy.method.interceptor.MethodInterceptor;

public interface EnhancerInterface {
	
	void setMethodInterceptor$Enhancer$(MethodInterceptor methodInterceptor);
	
	@SuppressWarnings("rawtypes")
	Object executeSuperMethod$Enhancer$(String methodName, Class[] argsType, Object[] argsValue);

	/**
	 *
     */
	void setCallBacksMethod$Enhancer$(MethodInterceptor[] methodInterceptor);

	/**
	 * filter
     */
	void setCallBackFilterMethod$Enhancer$(CallbackFilter callbackFilter);

}
