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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.map.reduce.DataSource;
import com.huawei.openstack4j.model.map.reduce.DataSourceCredentials;
import com.huawei.openstack4j.model.map.reduce.builder.DataSourceBuilder;
import com.huawei.openstack4j.openstack.common.ListResult;

import lombok.ToString;

/**
 * Model represent attributes of DataSource
 *
 * @author ekasit.kijsipongse@nectec.or.th
 * @author siwat.pru@outlook.com
 */
@ToString
@JsonRootName("data_source")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MapReduceDataSource implements DataSource {

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
	@JsonProperty("type")
	private DataSourceType type;
	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	private MapReduceDataSourceCredentials credentials;

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
	public String getURL() {
		return url;
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
	public DataSourceType getType() {
		return type;
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
	public DataSourceCredentials getCredentials() {
		return credentials;
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	@JsonProperty(value="is_protected") 
	public Boolean isProtected() {
		return isProtected;
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	@JsonProperty(value="is_public") 
	public Boolean isPublic() {
		return isPublic;
	}


	public static class DataSources extends ListResult<MapReduceDataSource> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("data_sources")
		private List<MapReduceDataSource> datasources;

		public List<MapReduceDataSource> value() {
			return datasources;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DataSourceBuilder toBuilder() {
		return new ConcreteDataSourceBuilder(this);
	}

	/**
	 * @return the data source Builder
	 */
	public static DataSourceBuilder builder() {
		return new ConcreteDataSourceBuilder();
	}

	public static class ConcreteDataSourceBuilder implements DataSourceBuilder {

		MapReduceDataSource m;

		ConcreteDataSourceBuilder() {
			this(new MapReduceDataSource());
		}

		ConcreteDataSourceBuilder(MapReduceDataSource m) {
			this.m = m;
		}
		
		/* 
		 * {@inheritDoc}
		 */
		@Override
		public DataSourceBuilder id(String dataSourceId) {
			m.id = dataSourceId;
			return this;
		}


		@Override
		public DataSourceBuilder description(String description) {
			m.description = description;
			return this;
		}

		@Override
		public DataSourceBuilder url(String url) {
			m.url = url;
			return this;
		}

		@Override
		public DataSourceBuilder type(DataSourceType type) {
			m.type = type;
			return this;
		}

		@Override
		public DataSourceBuilder name(String name) {
			m.name = name;
			return this;
		}
		
		/* 
		 * {@inheritDoc}
		 */
		@Override
		public DataSourceBuilder isPublic(boolean isPublic) {
			m.isPublic = isPublic;
			return this;
		}

		/* 
		 * {@inheritDoc}
		 */
		@Override
		public DataSourceBuilder isProtect(boolean isProtected) {
			m.isProtected = isProtected;
			return this;
		}

		@Override
		public DataSourceBuilder credentials(String user, String password) {
			m.credentials = new MapReduceDataSourceCredentials(user, password);
			return this;
		}

		@Override
		public DataSource build() {
			return m;
		}

		@Override
		public DataSourceBuilder from(DataSource in) {
			m = (MapReduceDataSource) in;
			return this;
		}


	}

}
