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

import java.io.*;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class ByteWritable implements WritableComparable {
  private int value;

  public void write(DataOutput out) throws IOException {
    out.writeByte(value);
  }

  public void readFields(DataInput in) throws IOException {
    value = (int) in.readByte();
  }

  public ByteWritable(int b) {
    value = b & 0xff;
  }

  public ByteWritable() {
    value = 0;
  }

  public void set(int b) {
    value = b & 0xff;
  }

  public int compareTo(Object o) {
    int thisValue = this.value;
    int thatValue = ((ByteWritable) o).value;
    return (thisValue < thatValue ? -1 : (thisValue == thatValue ? 0 : 1));
  }

  public boolean equals(Object o) {
    if (!(o instanceof ByteWritable)) {
      return false;
    }
    ByteWritable that = (ByteWritable) o;
    if (this == that)
      return true;

    if (this.value == that.value)
      return true;
    else
      return false;
  }

  public int hashCode() {
    return (value);
  }

  public static class Comparator extends WritableComparator {
    public Comparator() {
      super(ByteWritable.class);
    }

    public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {

      return 0;
    }
  }

  static {
    WritableComparator.define(ByteWritable.class, new Comparator());
  }
}
