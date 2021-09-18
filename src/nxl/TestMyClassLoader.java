package nxl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestMyClassLoader {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader loader = new MyClassLoader();
        Class clazz = loader.findClass("Hello.xlass");
        Method method = clazz.getMethod("hello");
        method.invoke(clazz.newInstance());
    }
}
