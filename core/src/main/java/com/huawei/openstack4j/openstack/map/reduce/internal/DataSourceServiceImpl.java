/*******************************************************************************
 * 	Copyright 2018 Huawei Technologies Co., Ltd.                                 
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
package com.huawei.openstack4j.openstack.map.reduce.internal;

import static com.google.common.base.Preconditions.*;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.api.map.reduce.DataSourceService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.map.reduce.DataSource;
import com.huawei.openstack4j.model.map.reduce.options.DataSourceListOptions;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceDataSource;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceDataSource.DataSources;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceDataSourceUnwrapped;

/**
 * The implementation of manipulation of {@link DataSource}
 * 
 * @author ekasit.kijsipongse@nectec.or.th
 * @author siwat.pru@outlook.com
 */
public class DataSourceServiceImpl extends BaseMapReduceServices implements DataSourceService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends DataSource> list(DataSourceListOptions options) {
		Map<String, Object> params = (options == null) ? null : options.getOptions();
		return get(DataSources.class, uri("/data-sources")).params(params).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DataSource get(String datasourceId) {
		checkNotNull(datasourceId);
		return get(MapReduceDataSource.class, uri("/data-sources/%s", datasourceId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DataSource create(DataSource datasource) {
		checkNotNull(datasource);
		MapReduceDataSourceUnwrapped unwrapped = new MapReduceDataSourceUnwrapped(datasource);
		return post(MapReduceDataSource.class, uri("/data-sources")).entity(unwrapped) // setup request
				.execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse delete(String datasourceId) {
		checkNotNull(datasourceId);
		return deleteWithResponse(uri("/data-sources/%s", datasourceId)).execute();
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public DataSource update(DataSource datasource) {
		checkNotNull(datasource);
		checkNotNull(datasource.getId(), "data-source `id` attribute is required");
		checkNotNull(datasource.getType(), "data-source `type` attribute is required");
		checkNotNull(datasource.getURL(), "data-source `URL` attribute is required");
		MapReduceDataSourceUnwrapped unwrapped = new MapReduceDataSourceUnwrapped(datasource);
		return put(MapReduceDataSource.class, uri("/data-sources/%s", datasource.getId())).entity(unwrapped).execute();
	}

}
