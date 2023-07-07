package cn.bcf.interceptor;

import cn.bcf.conf.TenantHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

public class TenantIdInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tenantIdStr = request.getHeader("tenantId");
        if (StringUtils.isBlank(tenantIdStr)) {
            TenantHolder.setTenantId(null);
        } else {
            TenantHolder.setTenantId(Integer.parseInt(tenantIdStr));
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
