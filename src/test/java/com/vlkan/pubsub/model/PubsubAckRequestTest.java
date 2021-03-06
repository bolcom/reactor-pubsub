/*
 * Copyright 2019-2020 Volkan Yazıcı
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permits and
 * limitations under the License.
 */

package com.vlkan.pubsub.model;

import com.vlkan.pubsub.jackson.JacksonHelpers;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PubsubAckRequestTest {

    @Test
    public void test_ctor_with_null_ackIds() {
        Assertions
                .assertThatThrownBy(() -> new PubsubAckRequest(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("ackIds");
    }

    @Test
    public void test_ctor_with_empty_ackIds() {
        Assertions
                .assertThatThrownBy(() -> new PubsubAckRequest(Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("empty ackIds");
    }

    @Test
    public void test_serialization() {
        List<String> ackIds = Collections.singletonList("some-ack-id");
        PubsubAckRequest request = new PubsubAckRequest(ackIds);
        Map<String, Object> actualRequestMap = JacksonHelpers.writeValueAsMap(request);
        Map<String, Object> expectedRequestMap = Collections.singletonMap(
                PubsubAckRequest.JsonFieldName.ACK_IDS, ackIds);
        Assertions.assertThat(actualRequestMap).isEqualTo(expectedRequestMap);
    }

}
