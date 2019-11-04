package com.tangu.config;

import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * FileName: HttpRequestWrapper
 * Author: yeyang
 * Date: 2019/9/5 17:58
 */
public class HttpRequestWrapper extends HttpServletRequestWrapper {
    private final String body;
    public HttpRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        Charset charset = Charset.forName(request.getCharacterEncoding());
        body = StreamUtils.copyToString(request.getInputStream(),charset);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(body.getBytes());
        ServletInputStream stream = new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return in.read();
            }
        };
        return stream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String getBody() {
        return body;
    }
}
