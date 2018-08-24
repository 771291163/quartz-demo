package com.hai.commons.process.quartz.task.util;

import com.thoughtworks.xstream.XStream;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by o-zhengzhenhai on 2018/8/23.
 * 转换的工具类,将xml转化为java类
 */
public class XsteamUtil  {

    /**
     * @param clazz 指定我们需要转换的对象
     * @param xml 需要转的xml字符串
     * @return
     */
    public static Object toBean(Class<?> clazz, InputStream xml) {
        XStream xstream = new XStream();
        xstream.processAnnotations(clazz);
        xstream.autodetectAnnotations(true);
        xstream.setClassLoader(clazz.getClassLoader());
        return xstream.fromXML(xml);
    }

}
