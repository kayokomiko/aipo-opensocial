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

/** Class _EipTMailFolder was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public class _EipTMailFolder extends org.apache.cayenne.CayenneDataObject {

    public static final String CREATE_DATE_PROPERTY = "createDate";
    public static final String FOLDER_NAME_PROPERTY = "folderName";
    public static final String UPDATE_DATE_PROPERTY = "updateDate";
    public static final String EIP_MMAIL_ACCOUNT_PROPERTY = "eipMMailAccount";

    public static final String FOLDER_ID_PK_COLUMN = "FOLDER_ID";

    public void setCreateDate(java.util.Date createDate) {
        writeProperty("createDate", createDate);
    }
    public java.util.Date getCreateDate() {
        return (java.util.Date)readProperty("createDate");
    }
    
    
    public void setFolderName(String folderName) {
        writeProperty("folderName", folderName);
    }
    public String getFolderName() {
        return (String)readProperty("folderName");
    }
    
    
    public void setUpdateDate(java.util.Date updateDate) {
        writeProperty("updateDate", updateDate);
    }
    public java.util.Date getUpdateDate() {
        return (java.util.Date)readProperty("updateDate");
    }
    
    
    public void setEipMMailAccount(com.aipo.orm.model.portlet.EipMMailAccount eipMMailAccount) {
        setToOneTarget("eipMMailAccount", eipMMailAccount, true);
    }

    public com.aipo.orm.model.portlet.EipMMailAccount getEipMMailAccount() {
        return (com.aipo.orm.model.portlet.EipMMailAccount)readProperty("eipMMailAccount");
    } 
    
    
}
