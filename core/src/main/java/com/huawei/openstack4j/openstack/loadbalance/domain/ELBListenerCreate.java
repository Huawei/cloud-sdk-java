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

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.huawei.openstack4j.model.loadbalance.ListenerCreate;
import com.huawei.openstack4j.model.loadbalance.Listener.BackendProtocol;
import com.huawei.openstack4j.model.loadbalance.Listener.LbAlgorithm;
import com.huawei.openstack4j.model.loadbalance.Listener.Protocol;
import com.huawei.openstack4j.model.loadbalance.Listener.SSLCiphers;
import com.huawei.openstack4j.model.loadbalance.Listener.SSLProtocols;
import com.huawei.openstack4j.model.loadbalance.Listener.StickySessionType;
import com.huawei.openstack4j.model.loadbalance.LoadBalancer.Status;
import com.huawei.openstack4j.openstack.common.DateTimeUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class ELBListenerCreate implements ListenerCreate {

	private static final long serialVersionUID = 2402527926955142969L;

	@JsonProperty
	private String name;

	@JsonProperty
	private String description;

	@JsonProperty("loadbalancer_id")
	private String loadBalancerId;

	@JsonProperty
	private Protocol protocol;

	@JsonProperty
	private Integer port;

	@JsonProperty("backend_protocol")
	private BackendProtocol backendProtocol;

	@JsonProperty("backend_port")
	private Integer backendPort;

	@JsonProperty("lb_algorithm")
	private LbAlgorithm lbAlgorithm;

	@JsonProperty("session_sticky")
	private Boolean sessionSticky;

	@JsonProperty("sticky_session_type")
	private StickySessionType stickySessionType;

	@JsonProperty("cookie_timeout")
	private Integer cookieTimeout;

	@JsonProperty("tcp_timeout")
	private Integer tcpTimeout;

	@JsonProperty("tcp_draining")
	private Boolean tcpDraining;

	@JsonProperty("tcp_draining_timeout")
	private Integer tcpDrainingTimeout;

	@JsonProperty("certificate_id")
	private String certificateId;

	@JsonProperty("udp_timeout")
	private Integer udpTimeout;

	@JsonProperty("ssl_protocols")
	private SSLProtocols sslProtocols;

	@JsonProperty("ssl_ciphers")
	private SSLCiphers sslCiphers;

	@JsonInclude(value = Include.NON_NULL)
	private String id;

	@JsonInclude(value = Include.NON_NULL)
	private Status status;

	@JsonProperty("admin_state_up")
	@JsonInclude(value = Include.NON_NULL)
	private Boolean adminStateUp;

	@JsonProperty("create_time")
	@JsonInclude(value = Include.NON_NULL)
	@JsonFormat(pattern = DateTimeUtils.FORMAT_YMDHMS)
	private Date createTime;

	@JsonProperty("update_time")
	@JsonInclude(value = Include.NON_NULL)
	@JsonFormat(pattern = DateTimeUtils.FORMAT_YMDHMS)
	private Date updateTime;
	
	List<String>  certificates;
}
