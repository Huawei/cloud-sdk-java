/*******************************************************************************
 * 	Copyright 2017 HuaWei TLD
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
package com.huawei.openstack4j.openstack.dns.v2.internal;

import static com.google.common.base.Preconditions.*;
import static com.huawei.openstack4j.core.transport.ClientConstants.*;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.api.dns.v2.RecordsetService;
import com.huawei.openstack4j.model.dns.v2.RecordSetType;
import com.huawei.openstack4j.model.dns.v2.Recordset;
import com.huawei.openstack4j.openstack.dns.v2.domain.DesignateRecordset;
import com.huawei.openstack4j.openstack.dns.v2.options.RecordsetListOptions;

public class RecordsetServiceImpl extends BaseDNSServices implements RecordsetService {

	@Override
	public Recordset get(String zoneId, String recordsetId) {
		checkNotNull(zoneId, "Zone Id should not be Null.");
		checkNotNull(recordsetId, "The recordset Id should not be Null.");
		return get(DesignateRecordset.class, PATH_ZONES, "/", zoneId, PATH_RECORDSETS, "/", recordsetId).execute();
	}

	@Override
	public Recordset delete(String zoneId, String recordsetId) {
		checkNotNull(zoneId, "Zone Id should not be Null.");
		checkNotNull(recordsetId, "The recordset Id should not be Null");
		return delete(DesignateRecordset.class, PATH_ZONES, "/", zoneId, PATH_RECORDSETS, "/", recordsetId).execute();
	}

	@Override
	public Recordset create(String zoneId, Recordset recordset) {
		checkNotNull(zoneId);
		checkNotNull(recordset);
		return post(DesignateRecordset.class, PATH_ZONES, "/", zoneId, PATH_RECORDSETS).entity(recordset).execute();
	}

	@Override
	public Recordset create(String zoneId, String name, String description, String type, Integer ttl,
			List<String> records) {
		checkNotNull(zoneId, "ZoneId should not be Null.");
		checkNotNull(name, "Name should not be Null.");
		checkNotNull(type, "Type should not be Null.");
		checkNotNull(ttl, "TTL should not be Null.");
		checkNotNull(records, "Records should not be Null.");
		return create(zoneId, DesignateRecordset.builder().name(name).type(RecordSetType.value(type))
				.description(description).ttl(ttl).records(records).build());
	}

	@Override
	public List<? extends Recordset> list(RecordsetListOptions options) {
		Map<String, Object> params = (options == null) ? null : options.getOptions();
		return get(DesignateRecordset.Recordsets.class, uri(PATH_RECORDSETS)).params(params).execute().getList();
	}


	@Override
	public List<? extends Recordset> list() {
		return get(DesignateRecordset.Recordsets.class, uri(PATH_RECORDSETS)).execute().getList();
	}

	@Override
	public List<? extends Recordset> list(String zoneId) {
		checkNotNull(zoneId, "Zone Id should not be null.");
		return get(DesignateRecordset.Recordsets.class, PATH_ZONES, "/", zoneId, PATH_RECORDSETS).execute().getList();
	}

	@Override
	public List<? extends Recordset> list(String zoneId, Integer limit, String marker) {
		checkNotNull(zoneId, "Zone Id should not be null.");
		Invocation<DesignateRecordset.Recordsets> invocation = get(DesignateRecordset.Recordsets.class, PATH_ZONES, "/",
				zoneId, PATH_RECORDSETS);
		invocation.param("marker", marker);
		invocation.param("limit", limit);
		return invocation.execute().getList();
	}

}
