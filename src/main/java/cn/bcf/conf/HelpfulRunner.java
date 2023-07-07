package cn.bcf.conf;

import com.github.xiaoymin.knife4j.spring.configuration.Knife4jProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class HelpfulRunner implements ApplicationRunner {

    @Autowired
    ApplicationContext context;

    @Autowired(required = false)
    Knife4jProperties knife4jProperties;

    @Autowired
    ServerProperties serverProperties;


    @Override
    public void run(ApplicationArguments args) {
        String contentPath = serverProperties.getServlet().getContextPath();
        int port = serverProperties.getPort();

        if (Objects.nonNull(knife4jProperties) && knife4jProperties.isEnable()) {
            log.info("knife4j url = http://localhost:" + port  + "${contentPath}/doc.html".replace("${contentPath}", StringUtils.isBlank(contentPath) ? "" : contentPath));
            if (Objects.nonNull(knife4jProperties.getBasic()) && knife4jProperties.getBasic().isEnable()) {
                log.info("knife4j username/password = " + knife4jProperties.getBasic().getUsername() + "/" + knife4jProperties.getBasic().getPassword());
            }
        } else {
            log.info("knife4j is closed!");
        }

        log.info("druid: http://localhost:" + port + "${contentPath}/druid/doc.html".replace("${contentPath}", StringUtils.isBlank(contentPath) ? "" : contentPath));
        log.info("druid username/password = admin/admin");
    }
}
