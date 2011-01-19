/*
 * Aipo is a groupware program developed by Aimluck,Inc.
 * Copyright (C) 2004-2011 Aimluck,Inc.
 * http://www.aipo.com/
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.aipo.orm.model.portlet.auto;

/** Class _EipTMsgboardCategoryMap was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public class _EipTMsgboardCategoryMap extends org.apache.cayenne.CayenneDataObject {

    public static final String CATEGORY_ID_PROPERTY = "categoryId";
    public static final String STATUS_PROPERTY = "status";
    public static final String USER_ID_PROPERTY = "userId";
    public static final String EIP_TMSGBOARD_CATEGORY_PROPERTY = "eipTMsgboardCategory";

    public static final String ID_PK_COLUMN = "ID";

    public void setCategoryId(Integer categoryId) {
        writeProperty("categoryId", categoryId);
    }
    public Integer getCategoryId() {
        return (Integer)readProperty("categoryId");
    }
    
    
    public void setStatus(String status) {
        writeProperty("status", status);
    }
    public String getStatus() {
        return (String)readProperty("status");
    }
    
    
    public void setUserId(Integer userId) {
        writeProperty("userId", userId);
    }
    public Integer getUserId() {
        return (Integer)readProperty("userId");
    }
    
    
    public void setEipTMsgboardCategory(com.aipo.orm.model.portlet.EipTMsgboardCategory eipTMsgboardCategory) {
        setToOneTarget("eipTMsgboardCategory", eipTMsgboardCategory, true);
    }

    public com.aipo.orm.model.portlet.EipTMsgboardCategory getEipTMsgboardCategory() {
        return (com.aipo.orm.model.portlet.EipTMsgboardCategory)readProperty("eipTMsgboardCategory");
    } 
    
    
}
