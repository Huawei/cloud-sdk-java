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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A model represent SMN message 
 *
 * @author QianBiao.NG
 * @date   2017-07-18 10:41:47
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class StructuredMessage extends TracableRequest implements ModelEntity {

	private static final long serialVersionUID = -6764087311133427927L;
	
	@JsonIgnore
	String subject;
	
	/**
	 * default message
	 */
	@JsonProperty("default")
	String defaultMessage;
	
	/**
	 * message for EMAIL protocol subscriber
	 */
	@JsonProperty("email")
	String emailMessage;
	
	/**
	 * message for SMS protocol subscriber
	 */
	@JsonProperty("sms")
	String smsMessage;

	/**
	 * message for HTTP protocol subscriber
	 */
	@JsonProperty("http")
	String httpMessage;
	
	/**
	 * message for HTTPS protocol subscriber
	 */
	@JsonProperty("https")
	String httpsMessage;
	
}
