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

package org.apache.hadoop.hive.ql.udf;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.serde2.io.ByteWritable;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.io.ShortWritable;
import org.apache.hadoop.hive.serde2.io.TimestampWritable;
import org.apache.hadoop.hive.serde2.lazy.LazyLong;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;

public class UDFToLong extends UDF {

  private static Log LOG = LogFactory.getLog(UDFToLong.class.getName());

  LongWritable longWritable = new LongWritable();

  public UDFToLong() {
  }

  public LongWritable evaluate(NullWritable i) {
    return null;
  }

  public LongWritable evaluate(BooleanWritable i) {
    if (i == null) {
      return null;
    } else {
      longWritable.set(i.get() ? (long) 1 : (long) 0);
      return longWritable;
    }
  }

  public LongWritable evaluate(ByteWritable i) {
    if (i == null) {
      return null;
    } else {
      longWritable.set((long) i.get());
      return longWritable;
    }
  }

  public LongWritable evaluate(ShortWritable i) {
    if (i == null) {
      return null;
    } else {
      longWritable.set((long) i.get());
      return longWritable;
    }
  }

  public LongWritable evaluate(IntWritable i) {
    if (i == null) {
      return null;
    } else {
      longWritable.set((long) i.get());
      return longWritable;
    }
  }

  public LongWritable evaluate(LongWritable i) {
    return i;
  }

  public LongWritable evaluate(FloatWritable i) {
    if (i == null) {
      return null;
    } else {
      longWritable.set((long) i.get());
      return longWritable;
    }
  }

  public LongWritable evaluate(DoubleWritable i) {
    if (i == null) {
      return null;
    } else {
      longWritable.set((long) i.get());
      return longWritable;
    }
  }

  public LongWritable evaluate(Text i) {
    if (i == null) {
      return null;
    } else {
      try {
        longWritable
            .set(LazyLong.parseLong(i.getBytes(), 0, i.getLength(), 10));
        return longWritable;
      } catch (NumberFormatException e) {
        return null;
      }
    }
  }

  public LongWritable evaluate(TimestampWritable i) {
    if (i == null) {
      return null;
    } else {
      longWritable.set(i.getSeconds());
      return longWritable;
    }
  }

}
