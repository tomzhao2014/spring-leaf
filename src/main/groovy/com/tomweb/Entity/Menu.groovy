package com.tomweb.Entity
/**
 * 菜单
 */
class Menu {

    /**名称*/
    String title

    /**图标*/
    String icon

    /**连接*/
    String link


    /**排序*/
    int seq

    /**父菜单*/
    Menu parent

    /**子菜单*/

    Date dateCreated = new Date()
    Date lastUpdated = new Date()
    Long createdBy =0
    Long updatedBy = 0

    static List<Menu> sortChildren(Set<Menu> child){
        def temp = child.toList();
        temp.sort(){Menu a,Menu b ->
            return (a.seq?:0)<=>(b.seq?:0)
        }
        return temp;
    }
}
