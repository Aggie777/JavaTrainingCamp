package nxl;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader{

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String myPath = "D://Downloads/" + name ;
        System.out.println(myPath);
        byte[] cLassBytes = null;
        Path path = null;
        try {
            path = Paths.get(myPath);
            cLassBytes = Files.readAllBytes(path);
        } catch (IOException  e) {
            e.printStackTrace();
        }
        //源文件编码字节经过255-x处理
        for (int i = 0; i < cLassBytes.length; i++) {
            cLassBytes[i] = (byte) (255 - cLassBytes[i]);
        }
        //查看是否处理正确
//      output(cLassBytes);
        Class clazz = defineClass("Hello", cLassBytes, 0, cLassBytes.length);
        return clazz;
    }

    //输出
    public static void output(byte [] data) {
        // 1,建立联系
        File file = new File("D:/Downloads/hello.class");
        // 2,选择流
        FileOutputStream os = null;
        try {
            // 此处还有 一个构造方法 FileOutputStream(File file, boolean append)
            // 当为true 时则为追加，默认为false
            os = new FileOutputStream(file);
            //3,操作 => 输出
            os.write(data, 0, data.length);
            //强制刷新，将数据全部输出
            os.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(os != null) {
                try {
                    // 4, 关闭流
                    os.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

}
