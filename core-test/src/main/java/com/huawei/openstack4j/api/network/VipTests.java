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
package com.huawei.openstack4j.api.network;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.network.ext.Protocol;
import com.huawei.openstack4j.model.network.ext.SessionPersistenceType;
import com.huawei.openstack4j.model.network.ext.Vip;
import com.huawei.openstack4j.model.network.ext.VipUpdate;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * 
 * @author liujunpeng
 *
 */
@Test(suiteName="Network/vip", enabled=false)
public class VipTests extends AbstractTest{
	/*public void testListVip(){
		List<? extends Vip> list = osv3().networking().loadbalancers().vip().list();
		System.out.println("test lb vip List"+list);
		assertEquals(1, list.size());
	}
	public void testListVipFilter(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "vip");
		List<? extends Vip> list = osv3().networking().loadbalancers().vip().list(map);
		System.out.println("test lb vip List filter"+list);
		assertEquals(1, list.size());
	}
	public void testGetVip(){
		String id = "dfc5c198-dceb-4f99-8ed7-5ebfdf46946d";
		Vip vip = osv3().networking().loadbalancers().vip().get(id);
		System.out.println("test get a vip"+vip);
		assertEquals(id, vip.getId());

	}
	
	public void testCreateVip(){
		String address = "100.2.12.48";
		String name = "createVip";
		String poolId = "db083bf7-c455-4758-b1ad-203cf441a73a";
		String subnetId = "7d1dab60-cf8a-4f75-af5c-44aab98b0c42";
		String tenantId = "d7fd03242ffa4933863bc528ed884fb6";
		Integer port = 80;
		Vip create = Builders.vip().address(address).adminStateUp(true)
				.connectionLimit(100)
				.description("vip")
				.name(name)
				.poolId(poolId)
				.protocol(Protocol.HTTP)
				.protocolPort(port)
				.sessionPersistence(Builders
						.sessionPersistence()
						.cookieName("cookie")
						.type(SessionPersistenceType.APP_COOKIE)
						.build())
				.subnetId(subnetId)
				.tenantId(tenantId)
				.build();
		Vip result = osv3().networking().loadbalancers().vip().create(create);
		System.out.println(result);
		assertEquals(address, result.getAddress());
		assertEquals(name, result.getName());
		assertEquals(Protocol.HTTP, result.getProtocol());
		assertEquals(port, result.getProtocolPort());
	}
	
	public void testUpdateVip(){
		String vipId = "cb1d7958-232d-4daa-a5f5-16ba91a6362b";
		String name = "updateVip";
		String poolId = "db083bf7-c455-4758-b1ad-203cf441a73a";
		Integer connectionLimit = 101;
		VipUpdate update = Builders.vipUpdate().adminStateUp(true)
				.connectionLimit(connectionLimit)
				.description("vip")
				.name(name)
				.poolId(poolId)
				.sessionPersistence(Builders
						.sessionPersistence()
						.type(SessionPersistenceType.SOURCE_IP)
						.build())
				.description("description update")
				.build();
		Vip result = osv3().networking().loadbalancers().vip().update(vipId, update);
		System.out.println(result);
		assertEquals(poolId, result.getPoolId());
		assertEquals(connectionLimit, result.getConnectionLimit());
		assertEquals(name, result.getName());
		assertEquals(SessionPersistenceType.SOURCE_IP, result
				.getSessionPersistence().getType());
	
	}
	
	public void testDeleteVip(){
		String id = "50cbd265-fe4f-4c9c-b25c-bb6c773d0366";
		ActionResponse result = osv3().networking().loadbalancers().vip().delete(id);
		assertTrue(result.isSuccess());
		
	}*/
	@Override
	protected Service service() {
		return Service.NETWORK;
	}

}
