package osp.leobert.android.proxy;

import java.lang.reflect.Method;

public interface CallbackFilter {
    /**
     * 过滤方法
     * @param method
     * @return
     */
     int accept(Method method);
}
