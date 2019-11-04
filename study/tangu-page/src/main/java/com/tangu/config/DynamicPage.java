package com.tangu.config;

/**
 * FileName: DynaticPage
 * Author: yeyang
 * Date: 2019/9/6 10:14
 */
public class DynamicPage {
    private static final ThreadLocal<Page> PAGE_THREAD_LOCAL = new ThreadLocal<>();

    public static Page get(){
        return PAGE_THREAD_LOCAL.get();
    }

    public static void set(Page page){
        PAGE_THREAD_LOCAL.set(page);
    }

    public static void clear(){
        PAGE_THREAD_LOCAL.remove();
    }
}
