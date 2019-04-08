 /*******************************************************************************
 * 	Copyright 2018 Huawei Technologies Co.,Ltd.                                         
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
package com.huawei.openstack4j.openstack.message.notification.options;

import java.util.Locale;
import java.util.Map;

import com.google.common.collect.Maps;

import com.huawei.openstack4j.openstack.message.notification.constant.Protocol;

/**
 *
 * @author QianBiao.NG
 * @date   2017-07-13 10:21:04
 */
public class MessageTemplateListOptions {

	private Map<String, Object> queryParams = Maps.newHashMap();

	/**
	 * setup pagination limit filter option
	 * 
	 * @param limit limit value
	 * @return
	 */
	public MessageTemplateListOptions limit(Integer limit) {
		return add("limit", limit);
	}

	/**
	 * setup pagination offset filter option
	 * @param offset offset value
	 * @return
	 */
	public MessageTemplateListOptions offset(Integer offset) {
		return add("offset", offset);
	}

	/**
	 * setup message template name filter option
	 * @param messageTemplateName name value
	 * @return
	 */
	public MessageTemplateListOptions name(String messageTemplateName) {
		return add("message_template_name", messageTemplateName);
	}

	/**
	 * setup message template protocol filter option
	 * @param protocol
	 * @return
	 */
	public MessageTemplateListOptions protocol(Protocol protocol) {
		return add("protocol", protocol.value());
	}

	/**
	 * setup message template locale filter option
	 * @param locale
	 * @return
	 */
	public MessageTemplateListOptions locale(String locale) {
		return add("locale", locale);
	}

	private MessageTemplateListOptions add(String param, Object value) {
		if (value != null)
			this.queryParams.put(param, value);
		return this;
	}

	public Map<String, Object> getOptions() {
		return queryParams;
	}

	public static MessageTemplateListOptions create() {
		return new MessageTemplateListOptions();
	}
	
	public static void main(String[] args) {
		Locale x = new Locale("en", "us");
		String language = x.getLanguage();
		System.out.println(x);
	}

}
