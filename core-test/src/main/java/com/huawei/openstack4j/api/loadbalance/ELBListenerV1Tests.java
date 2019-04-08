/*******************************************************************************
 * 	Copyright 2017 HuaWei Tld                                     
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
package com.huawei.openstack4j.api.loadbalance;

import static org.testng.Assert.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.loadbalance.Listener;
import com.huawei.openstack4j.model.loadbalance.Listener.BackendProtocol;
import com.huawei.openstack4j.model.loadbalance.Listener.LbAlgorithm;
import com.huawei.openstack4j.model.loadbalance.Listener.Protocol;
import com.huawei.openstack4j.model.loadbalance.Listener.SSLCiphers;
import com.huawei.openstack4j.model.loadbalance.Listener.SSLProtocols;
import com.huawei.openstack4j.model.loadbalance.Listener.Status;
import com.huawei.openstack4j.model.loadbalance.Listener.StickySessionType;
import com.huawei.openstack4j.model.loadbalance.ListenerCreate;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBListenerCreate;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBListenerUpdate;
import com.huawei.openstack4j.openstack.loadbalance.options.ELBListenerListOptions;

@Test(suiteName = "ELBLoadBalancer/ListenerV1")
public class ELBListenerV1Tests extends AbstractTest {

	private static final String JSON_LISTENER_CREATE = "/loadbalance/elb_listener_create.json";
	private static final String JSON_LISTENER = "/loadbalance/elb_listener.json";
	private static final String JSON_LISTENER_UPDATE = "/loadbalance/elb_listener_update.json";
	private static final String JSON_LISTENER_LIST = "/loadbalance/elb_listener_list.json";
	private static final String JSON_LISTENER_LIST2 = "/loadbalance/elb_listener_list2.json";

	public void createListenerTest() throws IOException {
		respondWith(JSON_LISTENER_CREATE);
		String loadBalancerId = "a650695bb9344a3fa24dec344116d261";
		ListenerCreate listener = ELBListenerCreate.builder().name("SDK-test-listener").loadBalancerId(loadBalancerId)
				.protocol(Protocol.TCP).port(12345).backendProtocol(BackendProtocol.TCP)
				.backendPort(54321).lbAlgorithm(LbAlgorithm.ROUND_ROBIN).build();
		Listener create = osv3().loadBalancer().listeners().create(listener);
		assertTrue("f5c566e27ebb4d5d8708fca77915a04b".equals(create.getId()));
	}

	public void deleteListenerTest() {
		respondWith(204);
		String listenerId = "f5c566e27ebb4d5d8708fca77915a04b";
		ActionResponse resp = osv3().loadBalancer().listeners().delete(listenerId);
		assertTrue(resp.isSuccess(), resp.getFault());
	}

	public void updateListenerTest() throws IOException {
		respondWith(JSON_LISTENER);
		respondWith(JSON_LISTENER_UPDATE);
		String listenerId = "f5c566e27ebb4d5d8708fca77915a04b";
		Listener listener = osv3().loadBalancer().listeners().get(listenerId);
		assertTrue("f5c566e27ebb4d5d8708fca77915a04b".equals(listener.getId()));

		String after = new StringBuilder(listener.getName()).reverse().toString();
		ELBListenerUpdate update = ELBListenerUpdate.fromListener(listener).toBuilder().name(after).build();

		Listener afterUpdate = osv3().loadBalancer().listeners().update(listenerId, update);
		assertTrue(after.equals(afterUpdate.getName()));
	}

	public void getListenerTest() throws IOException {
		respondWith(JSON_LISTENER);
		String listenerId = "f5c566e27ebb4d5d8708fca77915a04b";
		Listener listener = osv3().loadBalancer().listeners().get(listenerId);
		assertTrue(listener.getId().equals(listenerId));
		assertEquals(listener.getBackendPort(), Integer.valueOf(54321));
		assertEquals(listener.getBackendProtocol(), BackendProtocol.TCP);
		assertEquals(listener.getStickySessionType(), StickySessionType.INSERT);
		assertEquals(listener.getDescription(), "xxxxxx");
		assertEquals(listener.getLoadBalancerId(), "a650695bb9344a3fa24dec344116d261");
		assertEquals(listener.getStatus(), Status.ACTIVE);
		assertEquals(listener.getProtocol(), Protocol.TCP);
		assertEquals(listener.getPort(), Integer.valueOf(12345));
		assertEquals(listener.getCookieTimeout(), Integer.valueOf(1));
		assertEquals(listener.getClientCaTlsContainerRef(), "xxxxxx");
		assertEquals(listener.getAdminStateUp(), Boolean.FALSE);
		assertEquals(listener.getMemberNumber(), Integer.valueOf(1));
		assertEquals(listener.getHealthCheckId(), "xxxxxx");
		assertEquals(listener.getSessionSticky(), Boolean.FALSE);
		assertEquals(listener.getLbAlgorithm(), LbAlgorithm.ROUND_ROBIN);
		assertEquals(listener.getName(), "xxxxxx");
		assertEquals(listener.getCertificateId(), "xxxxxx");
		assertEquals(listener.getTcpTimeout(), Integer.valueOf(1));
		assertEquals(listener.getUdpTimeout(), Integer.valueOf(1));
		assertEquals(listener.getSslProtocols(), SSLProtocols.TLS_1_2);
		assertEquals(listener.getSslCiphers(), SSLCiphers.DEFAULT);
	}

	public void listListenerTest() throws IOException {
		respondWith(JSON_LISTENER_LIST);
		respondWith(JSON_LISTENER_LIST2);
		Listener[] all = osv3().loadBalancer().listeners().list();
		assertTrue(all.length == 5);

		String name = "SDK-test-listener";
		ELBListenerListOptions options = ELBListenerListOptions.create().name(name);
		Listener[] list = osv3().loadBalancer().listeners().list(options);
		assertTrue(list.length == 1);
		if (list != null) {
			for (Listener listener : list) {
				assertTrue(listener.getName().contains(name));
			}
		}
	}

	@Override
	protected Service service() {
		return Service.LOAD_BALANCER;
	}
}
