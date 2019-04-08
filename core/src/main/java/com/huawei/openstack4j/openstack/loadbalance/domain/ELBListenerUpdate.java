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
package com.huawei.openstack4j.openstack.loadbalance.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.loadbalance.Listener;
import com.huawei.openstack4j.model.loadbalance.ListenerUpdate;
import com.huawei.openstack4j.model.loadbalance.Listener.LbAlgorithm;
import com.huawei.openstack4j.model.loadbalance.Listener.SSLCiphers;
import com.huawei.openstack4j.model.loadbalance.Listener.SSLProtocols;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ELBListenerUpdate implements ListenerUpdate {

	private static final long serialVersionUID = 2402527926955142969L;

	@JsonProperty
	private String name;

	@JsonProperty
	private String description;

	@JsonProperty
	private Integer port;

	@JsonProperty("backend_port")
	private Integer backendPort;

	@JsonProperty("lb_algorithm")
	private LbAlgorithm lbAlgorithm;

	@JsonProperty("tcp_timeout")
	private Integer tcpTimeout;

	@JsonProperty("tcp_draining")
	private Boolean tcpDraining;

	@JsonProperty("tcp_draining_timeout")
	private Integer tcpDrainingTimeout;

	@JsonProperty("udp_timeout")
	private Integer udpTimeout;

	@JsonProperty("ssl_protocols")
	private SSLProtocols sslProtocols;

	@JsonProperty("ssl_ciphers")
	private SSLCiphers sslCiphers;
	
	@JsonProperty("certificate_id")
	private String certificateId;
	
	private List<String> certificates;
	
	public static ELBListenerUpdate fromListener(Listener listener) {
		return ELBListenerUpdate.builder()
					.name(listener.getName())
					.description(listener.getDescription())
					.port(listener.getPort())
					.backendPort(listener.getBackendPort())
					.lbAlgorithm(listener.getLbAlgorithm())
					.tcpDraining(listener.getTcpDraining())
					.tcpDrainingTimeout(listener.getTcpDrainingTimeout())
					.build();
	}
}
