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

package com.aipo.orm.model.account.auto;

/** Class _EipMUserPosition was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public class _EipMUserPosition extends org.apache.cayenne.CayenneDataObject {

    public static final String POSITION_PROPERTY = "position";
    public static final String TURBINE_USER_PROPERTY = "turbineUser";

    public static final String ID_PK_COLUMN = "ID";

    public void setPosition(Integer position) {
        writeProperty("position", position);
    }
    public Integer getPosition() {
        return (Integer)readProperty("position");
    }
    
    
    public void setTurbineUser(com.aipo.orm.model.security.TurbineUser turbineUser) {
        setToOneTarget("turbineUser", turbineUser, true);
    }

    public com.aipo.orm.model.security.TurbineUser getTurbineUser() {
        return (com.aipo.orm.model.security.TurbineUser)readProperty("turbineUser");
    } 
    
    
}
