/**
* Tencent is pleased to support the open source community by making TDW available.
* Copyright (C) 2014 THL A29 Limited, a Tencent company. All rights reserved.
* Licensed under the Apache License, Version 2.0 (the "License"); you may not use 
* this file except in compliance with the License. You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software distributed 
* under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS 
* OF ANY KIND, either express or implied. See the License for the specific language governing
* permissions and limitations under the License.
*/
package org.apache.hadoop.hive.ql.dataImport;

import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.conf.HiveConf;

public class ExtractConfig {
  private Log log = LogFactory.getLog(ExtractConfig.class);
  private String driver = "org.postgresql.Driver";
  private String dbType = "pg";
  private String host;
  private String port;
  private String url;
  private String dbName;
  private String user;
  private String pwd;
  private HiveConf conf;
  private String fd = "";
  private String rd = System.getProperty("line.separator");
  private int bufferLimit = 50;
  private String sql;
  private String filePath;
  private Path hdfsfilePath = null;

  public Path getHdfsfilePath() {
    return hdfsfilePath;
  }

  public void setHdfsfilePath(Path hdfsfilePath) {
    this.hdfsfilePath = hdfsfilePath;
  }

  public ExtractConfig() {
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public String getSql() {
    return sql;
  }

  public void setSql(String sql) {
    this.sql = sql;
  }

  public String getFd() {
    return fd;
  }

  public void setFd(String fd) {
    this.fd = fd;
  }

  public String getRd() {
    return rd;
  }

  public void setRd(String rd) {
    this.rd = rd;
  }

  public void setConf(HiveConf conf) {
    this.conf = conf;
  }

  public HiveConf getConf() {
    return this.conf;
  }

  public String getDriver() {
    return driver;
  }

  public void setDriver(String driver) {
    this.driver = driver;
  }

  public String getDbType() {
    return dbType;
  }

  public void setDbType(String dbType) {
    this.dbType = dbType;
    if (dbType.equals("pgsql") || dbType.equals("pg")) {
      this.setDriver("org.postgresql.Driver");
    } else if (dbType.equals("oracle") || dbType.equals("ora")) {
      this.setDriver("oracle.jdbc.driver.OracleDriver");
    }
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getPort() {
    return port;
  }

  public void setPort(String port) {
    this.port = port;
  }

  public String getUrl() {
    url = null;
    log.debug("dbtype : " + dbType);
    if (dbType.equals("pgsql") || dbType.equals("pg")) {
      url = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;
    } else if (dbType.equals("oracle") || dbType.equals("ora")) {
      url = "jdbc:oracle:thin:@" + host + ":" + port + ":" + dbName;
    }
    log.debug("+++++++++++" + url);
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getDbName() {
    return dbName;
  }

  public void setDbName(String dbName) {
    this.dbName = dbName;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  public int getBufferLimit() {
    return bufferLimit;
  }

  public void setBufferLimit(int bufferLimit) {
    this.bufferLimit = bufferLimit;
  }

  public static void main(String[] args) {
    for (Entry<Object, Object> test : System.getProperties().entrySet()) {
      System.out.println(test.getKey());
    }
  }
}
