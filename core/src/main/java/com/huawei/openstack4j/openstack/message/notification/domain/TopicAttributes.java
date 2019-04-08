/*******************************************************************************
 * 	Copyright 2017 HuaWei tld and OTC                                
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
package com.huawei.openstack4j.openstack.message.notification.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TopicAttributes implements ModelEntity {

	private static final long serialVersionUID = -6764087311133427927L;

	/**
	 * access policy of the topic
	 */
	@JsonProperty("access_policy")
	String accessPolicy;

	/**
	 * the introduction of the topic
	 */
	@JsonProperty("introduction")
	String introduction;

	/**
	 * SMS sign identifier, only provided for enterprise user
	 */
	@JsonProperty("sms_sign_id")
	String smsSignId;

	public static class TopicAttributesUnwapper implements ModelEntity {

		private static final long serialVersionUID = 1L;

		@JsonProperty("attributes")
		private TopicAttributes attributes;
		
		public TopicAttributesUnwapper() {
		}

		public TopicAttributesUnwapper(TopicAttributes attributes) {
			this.attributes = attributes;
		}

		public TopicAttributes getTopicAttributes() {
			return attributes;
		}
	}

}
