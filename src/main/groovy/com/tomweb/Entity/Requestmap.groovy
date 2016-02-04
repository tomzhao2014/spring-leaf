package com.tomweb.Entity

import org.springframework.util.StringUtils
/**
 * 权限配置
 */
class Requestmap {

    /**
     * 标题
     */
    String title

    /**
     * url
     */
    String url

    /**
     * 角色
     */
    String configAttribute

    /**
     * 备注
     */
    String remark

    /**
     * 分组名称
     */
    String groupName


    Long createdBy = 0

    Date dateCreated = new Date()

    Long updatedBy = 0

    Date lastUpdated = new Date()



    /**
     * 获取角色数组
     */
    public Set<String> getConfigAttributeSet() {
        Set<String> set = new HashSet<String>()

        if (this.configAttribute) {
            String[] arrayOfStrings = this.configAttribute.split(",")

            arrayOfStrings.each {
                set.add(it)
            }
        }

        return set
    }


    String[] getUrls() {
        return StringUtils.commaDelimitedListToStringArray(url);
    }

    String[] getAuthorities() {
        return StringUtils.commaDelimitedListToStringArray(configAttribute)
    }
}
