package cn.bcf.conf;

import java.util.Objects;

public class TenantHolder {

    private static final ThreadLocal<Integer> contextHolder = new ThreadLocal<>();
    private static final Integer DEFAULT_TENANT_ID = 0;

    public static void setTenantId(Integer tenantId) {
        contextHolder.set(tenantId);
    }

    public static int getTenantId() {
        Integer tenantId = contextHolder.get();
        if (Objects.isNull(tenantId)) {
            return DEFAULT_TENANT_ID;
        }
        return tenantId;
    }

    public static boolean isDefault() {
        return Objects.equals(getTenantId(), DEFAULT_TENANT_ID);
    }

}