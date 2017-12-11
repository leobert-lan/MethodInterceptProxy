package osp.leobert.android.proxy.method.interceptor;


import osp.leobert.android.proxy.method.MethodProxy;

public interface NoOp {

    MethodInterceptor INSTANCE = new MethodInterceptor() {

        @Override
        public Object intercept(Object object, Object[] args, MethodProxy methodProxy) throws Exception {
            return methodProxy.invokeSuper(object, args);
        }
    };
    MethodInterceptor INSTANCE_EMPTY = new MethodInterceptor() {

        @Override
        public Object intercept(Object object, Object[] args, MethodProxy methodProxy) throws Exception {
            //methodProxy.invokeSuper(object,args)
            return null;
        }
    };
}
