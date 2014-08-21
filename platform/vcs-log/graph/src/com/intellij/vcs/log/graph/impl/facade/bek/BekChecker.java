/*
 * Copyright 2000-2014 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.vcs.log.graph.impl.facade.bek;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.vcs.log.graph.api.LinearGraph;
import org.jetbrains.annotations.NotNull;

public class BekChecker {
  private final static Logger LOG = Logger.getInstance("#com.intellij.vcs.log.graph.impl.facade.bek.BekChecker");

  public static boolean checkLinearGraph(@NotNull LinearGraph linearGraph) {
    for (int i = 0;  i < linearGraph.nodesCount(); i++) {
      for (int downNode : linearGraph.getDownNodes(i)) {
        if (downNode <= i)
          LOG.error("Illegal node: " + i + ", with downNode: " + downNode);
      }

      for (int upNode : linearGraph.getUpNodes(i)) {
        if (upNode >= i)
          LOG.error("Illegal node: " + i + ", with upNode: " + upNode);
      }
    }
    return true;
  }

}
