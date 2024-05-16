package net.lab1024.smartadmin.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 系统业务相关配置，如系统名称、loggo、版权之类信息
 * 读取system.yml配置
 */
@Component
@ConfigurationProperties(prefix = "system.properties")
@PropertySource(value = {"classpath:system.yml"},encoding = "utf-8")
public class SystemConst {
    public String title;
    public String copyright;
    public String icpsecurity;
    public String plcecurity;
    public String hotmail;
    public String hotphone;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getIcpsecurity() {
        return icpsecurity;
    }

    public void setIcpsecurity(String icpsecurity) {
        this.icpsecurity = icpsecurity;
    }

    public String getPlcecurity() {
        return plcecurity;
    }

    public void setPlcecurity(String plcecurity) {
        this.plcecurity = plcecurity;
    }

    public String getHotmail() {
        return hotmail;
    }

    public void setHotmail(String hotmail) {
        this.hotmail = hotmail;
    }

    public String getHotphone() {
        return hotphone;
    }

    public void setHotphone(String hotphone) {
        this.hotphone = hotphone;
    }
}
