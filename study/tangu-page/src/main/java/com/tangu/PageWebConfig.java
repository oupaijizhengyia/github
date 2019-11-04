package com.tangu;

import com.tangu.config.DynamicPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * FileName: WebConfig
 * Author: yeyang
 * Date: 2019/9/5 16:57
 */
@Component
public class PageWebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public MappingJackson2HttpMessageConverter buildJson(){
        return new MappingJackson2HttpMessageConverter(){
            @Override
            protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
                if(object instanceof Map){
                    Map responseModel = (Map)object;
                    responseModel.put("page", DynamicPage.get());
                    DynamicPage.clear();
                    super.writeInternal(responseModel, type, outputMessage);
                }else {
                    super.writeInternal(object, type, outputMessage);
                }
            }
        };
    }

}
