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
import org.apache.hadoop.hive.ql.exec.description;
import org.apache.hadoop.hive.serde2.io.ByteWritable;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.io.ShortWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;

@description(name = "*", value = "a _FUNC_ b - Multiplies a by b")
public class UDFOPMultiply extends UDFBaseNumericOp {

  private static Log LOG = LogFactory
      .getLog("org.apache.hadoop.hive.ql.udf.UDFOPMultiply");

  public UDFOPMultiply() {
  }

  @Override
  public ByteWritable evaluate(ByteWritable a, ByteWritable b) {
    if ((a == null) || (b == null))
      return null;

    byteWritable.set((byte) (a.get() * b.get()));
    return byteWritable;
  }

  @Override
  public ShortWritable evaluate(ShortWritable a, ShortWritable b) {
    if ((a == null) || (b == null))
      return null;

    shortWritable.set((short) (a.get() * b.get()));
    return shortWritable;
  }

  @Override
  public IntWritable evaluate(IntWritable a, IntWritable b) {
    if ((a == null) || (b == null))
      return null;

    intWritable.set((int) (a.get() * b.get()));
    return intWritable;
  }

  @Override
  public LongWritable evaluate(LongWritable a, LongWritable b) {
    if ((a == null) || (b == null))
      return null;

    longWritable.set(a.get() * b.get());
    return longWritable;
  }

  @Override
  public FloatWritable evaluate(FloatWritable a, FloatWritable b) {
    if ((a == null) || (b == null))
      return null;

    floatWritable.set(a.get() * b.get());
    return floatWritable;
  }

  @Override
  public DoubleWritable evaluate(DoubleWritable a, DoubleWritable b) {
    if ((a == null) || (b == null))
      return null;

    doubleWritable.set(a.get() * b.get());
    return doubleWritable;
  }
}
