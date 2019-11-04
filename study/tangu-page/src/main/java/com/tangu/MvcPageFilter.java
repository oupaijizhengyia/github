package com.tangu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tangu.config.DynamicPage;
import com.tangu.config.HttpRequestWrapper;
import com.tangu.config.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * FileName: MvcPageFilter
 * Author: yeyang
 * Date: 2019/9/5 17:57
 */

@Component
public class MvcPageFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpRequestWrapper wrapper = new HttpRequestWrapper(httpServletRequest);
        JSONObject object ;
        try {
            if(!StringUtils.isEmpty(wrapper.getBody())){
                object = JSONObject.parseObject(wrapper.getBody());
                Page page = new Page();
                if(object.get("targetIndex") != null){
                    page.setTargetIndex(Integer.valueOf(object.get("targetIndex").toString()));
                    if(object.get("pageSize") != null){
                        page.setPageSize(Integer.valueOf(object.get("pageSize").toString()));
                    }
                    page.setFlag(true);
                    DynamicPage.set(page);
                }
            }
        }catch (Exception e){

        }
        doFilter(wrapper,httpServletResponse,filterChain);
    }

}
