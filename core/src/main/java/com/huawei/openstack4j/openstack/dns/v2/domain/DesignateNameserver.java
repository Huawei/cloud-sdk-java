/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
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
/******************************************************************************* 	                                                                                 
 *  Huawei has modified this source file.
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
 * *******************************************************************************/
package com.huawei.openstack4j.openstack.dns.v2.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.dns.v2.Nameserver;
import com.huawei.openstack4j.model.dns.v2.builder.NameserverBuilder;
import com.huawei.openstack4j.openstack.common.ListResult;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.List;

/**
 * model class for designate/v2 nameserver
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DesignateNameserver implements Nameserver {

	private static final long serialVersionUID = 1L;
	private String hostname;
	private Integer priority;
	private String address;

	/**
	 * @return the nameserver builder
	 */
	public static NameserverBuilder builder() {
		return new NameserverConcreteBuilder();
	}

	@Override
	public NameserverBuilder toBuilder() {
		return new NameserverConcreteBuilder(this);
	}

	@Override
	public String getHostname() {
		return hostname;
	}

	@Override
	public Integer getPriority() {
		return priority;
	}

	@Override
	public String getAddress() {
		return address;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
				.add("hostname", hostname)
				.add("priority", priority)
				.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(hostname, priority);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		DesignateNameserver that = DesignateNameserver.class.cast(obj);
		return Objects.equal(this.hostname, that.hostname)
				&& Objects.equal(this.priority, that.priority);
	}

	public static class NameserverConcreteBuilder implements NameserverBuilder {

		DesignateNameserver model;

		NameserverConcreteBuilder() {
			this(new DesignateNameserver());
		}

		NameserverConcreteBuilder(DesignateNameserver model) {
			this.model = model;
		}

		@Override
		public Nameserver build() {
			return model;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public NameserverBuilder from(Nameserver in) {
			if (in != null)
				this.model = (DesignateNameserver) in;
			return this;
		}

		/**
		 * @see DesignateNameserver#getHostname()
		 */
		@Override
		public NameserverBuilder hostname(String hostname) {
			model.hostname = hostname;
			return this;
		}

		/**
		 * @see DesignateNameserver#getPriority()
		 */
		@Override
		public NameserverBuilder priority(Integer priority) {
			model.priority = priority;
			return this;
		}

	}

	public static class Nameservers extends ListResult<DesignateNameserver> {

		private static final long serialVersionUID = 1L;
		@JsonProperty("nameservers")
		protected List<DesignateNameserver> list;

		@Override
		public List<DesignateNameserver> value() {
			return list;
		}
	}

}
