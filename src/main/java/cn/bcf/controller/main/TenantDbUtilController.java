package cn.bcf.controller.main;

import cn.bcf.conf.tenant.TenantDbConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/main/tenant/db")
@Tag(name = "客户数据库操作", description = "配置中心-客户数据库操作")
public class TenantDbUtilController {

    @Autowired
    TenantDbConfig tenantDbConfig;

    @GetMapping("updateSchema")
    @Operation(summary = "自动更新数据库表结构")
    public Boolean updateSchema(@RequestParam int tenantId) throws Exception {
        return tenantDbConfig.updateSchema(tenantId);
    }

    @Operation(summary = "测试数据库连接")
    @GetMapping("isConnection")
    public Boolean isConnection(@RequestParam int tenantId) throws Exception {
        return tenantDbConfig.isConnection(tenantId);
    }


}
