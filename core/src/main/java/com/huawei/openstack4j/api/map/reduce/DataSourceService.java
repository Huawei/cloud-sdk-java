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
package com.huawei.openstack4j.api.map.reduce;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.map.reduce.DataSource;
import com.huawei.openstack4j.model.map.reduce.options.DataSourceListOptions;

/**
 * The implementation of manipulation of {@link DataSource}
 *
 * @author ekasit.kijsipongse@nectec.or.th
 */
public interface DataSourceService extends RestService {

    /**
     * List all data sources
     * @param options filter options
     * @return list of data sources or empty
     */
     List<? extends DataSource> list(DataSourceListOptions options);

    /**
     * Get a data source by ID
     * @param datasourceId the data source identifier
     * @return the data source or null if not found
     */
     DataSource get(String datasourceId);

    /**
     * Create a new data source
     *
     * @param datasource the data source to create
     * @return the created data source
     */
     DataSource create(DataSource datasource);
     
     /**
      * Update an exists data source
      *
      * @param datasource the data source to update
      * @return the updated data source
      */
      DataSource update(DataSource datasource);

    /**
     * Delete the specified data source 
     * 
     * @param datasourceId the data source identifier
     * @return the action response
     */
     ActionResponse delete(String datasourceId);

}
