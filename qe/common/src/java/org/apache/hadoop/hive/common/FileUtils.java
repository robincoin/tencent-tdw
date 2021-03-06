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

package org.apache.hadoop.hive.common;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.conf.Configuration;
import java.io.IOException;
import java.net.URI;

public class FileUtils {

  public static Path makeQualified(Path path, Configuration conf)
      throws IOException {

    if (!path.isAbsolute()) {
      return path.makeQualified(FileSystem.get(conf));
    }

    URI fsUri = FileSystem.getDefaultUri(conf);
    URI pathUri = path.toUri();

    String scheme = pathUri.getScheme();
    String authority = pathUri.getAuthority();

    if (scheme != null && (authority != null || fsUri.getAuthority() == null))
      return path;

    if (scheme == null) {
      scheme = fsUri.getScheme();
    }

    if (authority == null) {
      authority = fsUri.getAuthority();
      if (authority == null) {
        authority = "";
      }
    }

    return new Path(scheme + ":" + "//" + authority + pathUri.getPath());
  }
}
