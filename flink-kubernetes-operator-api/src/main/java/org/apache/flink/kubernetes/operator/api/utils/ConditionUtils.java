/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.kubernetes.operator.api.utils;

import io.fabric8.kubernetes.api.model.Condition;
import io.fabric8.kubernetes.api.model.ConditionBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

/** Status of CR. */
public class ConditionUtils {

    public static Condition ready(final String message) {
        return crCondition("Ready", "True", message, "Ready");
    }

    public static Condition notReady(final String message) {
        return crCondition("Ready", "False", message, "Progressing");
    }

    public static Condition error(final String message) {
        return crCondition("Error", "True", message, "The job terminally failed");
    }

    public static Condition crCondition(
            final String type, final String status, final String message, final String reason) {
        return new ConditionBuilder()
                .withType(type)
                .withStatus(status)
                .withMessage(message)
                .withReason(reason)
                .withLastTransitionTime(
                        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(new Date()))
                .build();
    }
}