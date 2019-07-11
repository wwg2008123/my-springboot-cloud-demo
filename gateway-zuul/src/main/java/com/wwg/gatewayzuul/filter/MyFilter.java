package com.wwg.gatewayzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import utils.StringUtil;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        context.set("startTime",System.currentTimeMillis());
        /*HttpServletRequest request = context.getRequest();
        String token = request.getParameter("token");*/
       /* if (StringUtils.isNotBlank(token)) {
            context.setSendZuulResponse(true); //对请求进行路由
            context.setResponseStatusCode(200);
            context.set("isSuccess", true);
            return null;
        } else {
            context.setSendZuulResponse(false); //不对其进行路由
            context.setResponseStatusCode(400);
            context.setResponseBody("token is empty");
            context.set("isSuccess", false);
            return null;
        }*/
       return null;
    }
}