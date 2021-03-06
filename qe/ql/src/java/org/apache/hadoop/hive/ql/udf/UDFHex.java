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

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.ql.exec.description;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

@description(name = "hex", value = "_FUNC_(n or str) - Convert the argument to hexadecimal ", extended = "If the argument is a string, returns two hex digits for each "
    + "character in the string.\n"
    + "If the argument is a number, returns the hexadecimal representation.\n"
    + "Example:\n"
    + "  > SELECT _FUNC_(17) FROM src LIMIT 1;\n"
    + "  'H1'\n"
    + "  > SELECT _FUNC_('Facebook') FROM src LIMIT 1;\n"
    + "  '46616365626F6F6B'")
public class UDFHex extends UDF {
  private Text result = new Text();
  byte[] value = new byte[16];

  private Text evaluate(long num) {
    int len = 0;
    do {
      len++;
      value[value.length - len] = (byte) Character.toUpperCase(Character
          .forDigit((int) (num & 0xF), 16));
      num >>>= 4;
    } while (num != 0);

    result.set(value, value.length - len, len);
    return result;
  }

  public Text evaluate(LongWritable n) {
    if (n == null) {
      return null;
    }
    return evaluate((long) n.get());
  }

  public Text evaluate(IntWritable n) {
    if (n == null) {
      return null;
    }
    return evaluate(n.get());
  }

  public Text evaluate(Text s) {
    if (s == null) {
      return null;
    }

    if (value.length < s.getLength() * 2) {
      value = new byte[s.getLength() * 2];
    }

    byte[] str = s.getBytes();
    for (int i = 0; i < s.getLength(); i++) {
      value[i * 2] = (byte) Character.toUpperCase(Character.forDigit(
          (str[i] & 0xF0) >>> 4, 16));
      value[i * 2 + 1] = (byte) Character.toUpperCase(Character.forDigit(
          str[i] & 0x0F, 16));
    }

    result.set(value, 0, s.getLength() * 2);
    return result;
  }
}
