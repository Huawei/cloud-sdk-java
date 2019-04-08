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
package com.huawei.openstack4j.openstack.map.reduce.domain;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.map.reduce.Job;
import com.huawei.openstack4j.model.map.reduce.JobBinary;
import com.huawei.openstack4j.model.map.reduce.builder.JobBuilder;
import com.huawei.openstack4j.openstack.common.ListResult;

import lombok.ToString;

/**
 * Model represent attributes of Job
 *
 * @author ekasit.kijsipongse@nectec.or.th
 * @author siwat.pru@outlook.com
 */
@ToString
@JsonRootName("job")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MapReduceJob implements Job {

	private static final long serialVersionUID = 1L;

	@JsonProperty("description")
	private String description;
	@JsonProperty("url")
	private String url;
	@JsonProperty("tenant_id")
	private String tenantId;
	@JsonProperty("created_at")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date createdAt;
	@JsonProperty("updated_at")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date updatedAt;
	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("type")
	private JobType type;
	@JsonProperty("mains")
	private List<MapReduceJobBinary> mains;
	@JsonProperty("libs")
	private List<MapReduceJobBinary> libs;

	private List<String> mainBinaryIds;
	private List<String> libBinaryIds;
	private List<String> interfaces;

	@JsonProperty("is_protected")
	Boolean isProtected;
	@JsonProperty("is_public")
	Boolean isPublic;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTenantId() {
		return tenantId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JobType getType() {
		return type;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends JobBinary> getFullMains() {
		return mains;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends JobBinary> getFullLibs() {
		return libs;
	}
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getMains() {
		return mainBinaryIds;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getLibs() {
		return libBinaryIds;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<String> getInterfaces() {
		return interfaces;
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	@JsonIgnore
	public Boolean isProtected() {
		return isProtected;
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	@JsonIgnore
	public Boolean isPublic() {
		return isPublic;
	}

	public static class Jobs extends ListResult<MapReduceJob> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("jobs")
		private List<MapReduceJob> jobs;

		@Override
		protected List<MapReduceJob> value() {
			return jobs;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JobBuilder toBuilder() {
		return new ConcreteJobBuilder(this);
	}

	/**
	 * @return the job Builder
	 */
	public static JobBuilder builder() {
		return new ConcreteJobBuilder();
	}


	public static class ConcreteJobBuilder implements JobBuilder {

		MapReduceJob m;

		ConcreteJobBuilder() {
			this(new MapReduceJob());
		}

		ConcreteJobBuilder(MapReduceJob m) {
			this.m = m;
		}

		@Override
		public Job build() {
			return m;
		}

		@Override
		public JobBuilder from(Job in) {
			m = (MapReduceJob) in;
			return this;
		}

		@Override
		public JobBuilder id(String id) {
			m.id = id;
			return this;
		}

		@Override
		public JobBuilder description(String description) {
			m.description = description;
			return this;
		}

		@Override
		public JobBuilder type(JobType type) {
			m.type = type;
			return this;
		}

		@Override
		public JobBuilder name(String name) {
			m.name = name;
			return this;
		}

		@Override
		@JsonProperty("mains")
		public JobBuilder addMain(String jobBinaryId) {
			if (m.mainBinaryIds == null)
				m.mainBinaryIds = Lists.newArrayList();
			m.mainBinaryIds.add(jobBinaryId);
			return this;
		}

		@Override
		@JsonProperty("libs")
		public JobBuilder addLib(String jobBinaryId) {
			if (m.libBinaryIds == null)
				m.libBinaryIds = Lists.newArrayList();
			m.libBinaryIds.add(jobBinaryId);
			return this;
		}
		
		@JsonProperty("interfaces")
		public JobBuilder addInterface(String interface_) {
			if (m.interfaces == null)
				m.interfaces = Lists.newArrayList();
			m.interfaces.add(interface_);
			return this;
		}

		/*
		 * {@inheritDoc}
		 */
		@Override
		public JobBuilder isPublic(boolean isPublic) {
			m.isPublic = isPublic;
			return this;
		}

		/*
		 * {@inheritDoc}
		 */
		@Override
		public JobBuilder isProtect(boolean isProtected) {
			m.isProtected = isProtected;
			return this;
		}

	}

}
