package com.zzqfsy.cloud.config.client.schedule;

import com.zzqfsy.cloud.config.client.properties.JpushCenterProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.context.support.StandardServletEnvironment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by john on 16-10-17.
 */
//@Component
public class PropertiesSchedule {
    private Set<String> standardSources = new HashSet<String>(Arrays.asList(
        StandardEnvironment.SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME,
        StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME,
        StandardServletEnvironment.JNDI_PROPERTY_SOURCE_NAME,
        StandardServletEnvironment.SERVLET_CONFIG_PROPERTY_SOURCE_NAME,
        StandardServletEnvironment.SERVLET_CONTEXT_PROPERTY_SOURCE_NAME));

    @Autowired
    private RefreshScope refreshScope;
    @Autowired
    private ConfigurableApplicationContext context;
    @Autowired
    private JpushCenterProperties jpushCenterProperties;

    @Scheduled(cron="0/20 * *  * * ? ")   //每20秒执行一次
    public void refreshConfigProperties(){
        refresh();
        System.out.println("refresh title: " + jpushCenterProperties.getTitle());
    }

    /**
     * 手动刷新git更新方案，每20S执行一次，可以修改成手动执行，同/refresh
     * @link RefreshEndpoint
     * */
    public void refresh() {
        Map<String, Object> before = extract(context.getEnvironment().getPropertySources());
        addConfigFilesToEnvironment();
        Set<String> keys = changes(before, extract(context.getEnvironment().getPropertySources())).keySet();
        refreshScope.refreshAll();

        context.publishEvent(new EnvironmentChangeEvent(keys));
    }

    @Configuration
    protected static class Empty {

    }

    private void addConfigFilesToEnvironment() {
        ConfigurableApplicationContext capture = null;
        try {
            capture = new SpringApplicationBuilder(Empty.class)
                .web(false).environment(context.getEnvironment()).run();
            MutablePropertySources target = context.getEnvironment().getPropertySources();
            for (PropertySource<?> source : capture.getEnvironment().getPropertySources()) {
                String name = source.getName();
                if (!standardSources.contains(name)) {
                    if (target.contains(name)) {
                        target.replace(name, source);
                    }
                    else {
                        if (target.contains("defaultProperties")) {
                            target.addBefore("defaultProperties", source);
                        }
                        else {
                            target.addLast(source);
                        }
                    }
                }
            }
        }
        finally {
            while (capture != null) {
                capture.close();
                ApplicationContext parent = capture.getParent();
                if (parent instanceof ConfigurableApplicationContext) {
                    capture = (ConfigurableApplicationContext) parent;
                } else {
                    capture = null;
                }
            }
        }
    }

    private Map<String, Object> changes(Map<String, Object> before,
                                        Map<String, Object> after) {
        Map<String, Object> result = new HashMap<String, Object>();
        for (String key : before.keySet()) {
            if (!after.containsKey(key)) {
                result.put(key, null);
            }
            else if (!equal(before.get(key), after.get(key))) {
                result.put(key, after.get(key));
            }
        }
        for (String key : after.keySet()) {
            if (!before.containsKey(key)) {
                result.put(key, after.get(key));
            }
        }
        return result;
    }

    private boolean equal(Object one, Object two) {
        if (one == null && two == null) {
            return true;
        }
        if (one == null || two == null) {
            return false;
        }
        return one.equals(two);
    }

    private Map<String, Object> extract(MutablePropertySources propertySources) {
        Map<String, Object> result = new HashMap<String, Object>();
        for (PropertySource<?> parent : propertySources) {
            if (!standardSources.contains(parent.getName())) {
                extract(parent, result);
            }
        }
        return result;
    }

    private void extract(PropertySource<?> parent, Map<String, Object> result) {
        if (parent instanceof CompositePropertySource) {
            try {
                for (PropertySource<?> source : ((CompositePropertySource) parent)
                    .getPropertySources()) {
                    extract(source, result);
                }
            }
            catch (Exception e) {
                return;
            }
        }
        else if (parent instanceof EnumerablePropertySource) {
            for (String key : ((EnumerablePropertySource<?>) parent).getPropertyNames()) {
                result.put(key, parent.getProperty(key));
            }
        }
    }
}
