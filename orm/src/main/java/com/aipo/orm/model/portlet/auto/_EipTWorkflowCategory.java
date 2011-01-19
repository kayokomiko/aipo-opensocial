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

import java.util.List;

/** Class _EipTWorkflowCategory was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public class _EipTWorkflowCategory extends org.apache.cayenne.CayenneDataObject {

    public static final String CATEGORY_NAME_PROPERTY = "categoryName";
    public static final String CREATE_DATE_PROPERTY = "createDate";
    public static final String NOTE_PROPERTY = "note";
    public static final String TEMPLATE_PROPERTY = "template";
    public static final String UPDATE_DATE_PROPERTY = "updateDate";
    public static final String USER_ID_PROPERTY = "userId";
    public static final String EIP_TWORKFLOW_REQUEST_PROPERTY = "eipTWorkflowRequest";
    public static final String EIP_TWORKFLOW_ROUTE_PROPERTY = "eipTWorkflowRoute";

    public static final String CATEGORY_ID_PK_COLUMN = "CATEGORY_ID";

    public void setCategoryName(String categoryName) {
        writeProperty("categoryName", categoryName);
    }
    public String getCategoryName() {
        return (String)readProperty("categoryName");
    }
    
    
    public void setCreateDate(java.util.Date createDate) {
        writeProperty("createDate", createDate);
    }
    public java.util.Date getCreateDate() {
        return (java.util.Date)readProperty("createDate");
    }
    
    
    public void setNote(String note) {
        writeProperty("note", note);
    }
    public String getNote() {
        return (String)readProperty("note");
    }
    
    
    public void setTemplate(String template) {
        writeProperty("template", template);
    }
    public String getTemplate() {
        return (String)readProperty("template");
    }
    
    
    public void setUpdateDate(java.util.Date updateDate) {
        writeProperty("updateDate", updateDate);
    }
    public java.util.Date getUpdateDate() {
        return (java.util.Date)readProperty("updateDate");
    }
    
    
    public void setUserId(Integer userId) {
        writeProperty("userId", userId);
    }
    public Integer getUserId() {
        return (Integer)readProperty("userId");
    }
    
    
    public void addToEipTWorkflowRequest(com.aipo.orm.model.portlet.EipTWorkflowRequest obj) {
        addToManyTarget("eipTWorkflowRequest", obj, true);
    }
    public void removeFromEipTWorkflowRequest(com.aipo.orm.model.portlet.EipTWorkflowRequest obj) {
        removeToManyTarget("eipTWorkflowRequest", obj, true);
    }
    public List getEipTWorkflowRequest() {
        return (List)readProperty("eipTWorkflowRequest");
    }
    
    
    public void setEipTWorkflowRoute(com.aipo.orm.model.portlet.EipTWorkflowRoute eipTWorkflowRoute) {
        setToOneTarget("eipTWorkflowRoute", eipTWorkflowRoute, true);
    }

    public com.aipo.orm.model.portlet.EipTWorkflowRoute getEipTWorkflowRoute() {
        return (com.aipo.orm.model.portlet.EipTWorkflowRoute)readProperty("eipTWorkflowRoute");
    } 
    
    
}
