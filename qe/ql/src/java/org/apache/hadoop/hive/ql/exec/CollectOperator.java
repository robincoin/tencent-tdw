/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.hive.ql.exec;

import java.util.*;
import java.io.*;

import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.plan.collectDesc;
import org.apache.hadoop.hive.serde2.objectinspector.InspectableObject;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorUtils;
import org.apache.hadoop.conf.Configuration;

public class CollectOperator extends Operator<collectDesc> implements
    Serializable {

  private static final long serialVersionUID = 1L;
  transient protected ArrayList<Object> rowList;
  transient protected ObjectInspector standardRowInspector;
  transient int maxSize;

  protected void initializeOp(Configuration hconf) throws HiveException {
    super.initializeOp(hconf);
    rowList = new ArrayList<Object>();
    maxSize = conf.getBufferSize().intValue();
  }

  boolean firstRow = true;

  public void process(Object row, int tag) throws HiveException {
    ObjectInspector rowInspector = inputObjInspectors[tag];
    if (firstRow) {
      firstRow = false;
      this.standardRowInspector = ObjectInspectorUtils
          .getStandardObjectInspector(rowInspector);
    }

    if (rowList.size() < maxSize) {
      Object o = ObjectInspectorUtils.copyToStandardObject(row, rowInspector);
      rowList.add(o);
    }
    forward(row, rowInspector);
  }

  public void retrieve(InspectableObject result) {
    assert (result != null);
    if (rowList.isEmpty()) {
      result.o = null;
      result.oi = null;
    } else {
      result.o = rowList.remove(0);
      result.oi = standardRowInspector;
    }
  }

}
