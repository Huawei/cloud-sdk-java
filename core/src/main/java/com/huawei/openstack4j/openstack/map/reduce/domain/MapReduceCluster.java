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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.compute.Image;
import com.huawei.openstack4j.model.map.reduce.Cluster;
import com.huawei.openstack4j.model.map.reduce.NodeGroup;
import com.huawei.openstack4j.model.map.reduce.ServiceConfig;
import com.huawei.openstack4j.model.map.reduce.ServiceInfo;
import com.huawei.openstack4j.model.map.reduce.builder.ClusterBuilder;
import com.huawei.openstack4j.openstack.common.ListResult;

/**
 * Model represent attributes of Cluster
 *
 * @author ekasit.kijsipongse@nectec.or.th
 */
@JsonRootName("cluster")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MapReduceCluster implements Cluster {

	private static final long serialVersionUID = 1L;

	private Status status;
	@JsonProperty("info")
	private Map<String, MapReduceServiceInfo> infos;
	@JsonProperty("cluster_template_id")
	private String clusterTemplateId;
	@JsonProperty("is_transient")
	private Boolean isTransient;
	private String description;
	@JsonProperty("cluster_configs")
	private Map<String, MapReduceServiceConfig> clusterConfigs;
	@JsonProperty("created_at")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date createdAt;
	@JsonProperty("default_image_id")
	private String defaultImageId;
	@JsonProperty("user_keypair_id")
	private String userKeypairId;
	@JsonProperty("updated_at")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date updatedAt;
	@JsonProperty("plugin_name")
	private String pluginName;
	@JsonProperty("neutron_management_network")
	private String managementNetworkId;
	@JsonProperty("anti_affinity")
	private List<String> antiAffinity;
	@JsonProperty("tenant_id")
	private String tenantId;
	@JsonProperty("node_groups")
	private List<MapReduceNodeGroup> nodeGroups;
	@JsonProperty("management_public_key")
	private String managementPublicKey;
	@JsonProperty("status_description")
	private String statusDescription;
	@JsonProperty("hadoop_version")
	private String hadoopVersion;
	private String id;
	@JsonProperty("trust_id")
	private String trustId; // TODO: What is trust_id?
	private String name;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Status getStatus() {
		return status;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, ? extends ServiceInfo> getInfos() {
		return infos;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getClusterTemplateId() {
		return clusterTemplateId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean isTransient() {
		return isTransient;
	}

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
	public Map<String, ? extends ServiceConfig> getClusterConfigs() {
		return clusterConfigs;
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
	public String getDefaultImageId() {
		return defaultImageId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getUserKeypairId() {
		return userKeypairId;
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
	public String getPluginName() {
		return pluginName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getManagementNetworkId() {
		return managementNetworkId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getAntiAffinity() {
		return antiAffinity;
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
	public List<? extends NodeGroup> getNodeGroups() {
		return nodeGroups;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getManagementPublicKey() {
		return managementPublicKey;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getStatusDescription() {
		return statusDescription;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getHadoopVersion() {
		return hadoopVersion;
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
	public String getTrustId() {
		return trustId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues().add("id", id).add("name", name)
				.add("description", description).add("status", status).add("created_at", createdAt)
				.add("updated_at", updatedAt).add("tenant_id", tenantId).add("cluster_template_id", clusterTemplateId)
				.add("is_transient", isTransient).add("default_image_id", defaultImageId)
				.add("user_keypair_id", userKeypairId).add("plugin_name", pluginName)
				.add("neutron_management_network", managementNetworkId).add("cluster_configs", clusterConfigs)
				.add("anti_affinity", antiAffinity).add("node_groups", nodeGroups)
				.add("management_public_key", managementPublicKey).add("status_description", statusDescription)
				.add("hadoop_version", hadoopVersion).add("trust_id", trustId).add("info", infos).toString();
	}

	public static class Clusters extends ListResult<MapReduceCluster> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("clusters")
		private List<MapReduceCluster> clusters;

		public List<MapReduceCluster> value() {
			return clusters;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ClusterBuilder toBuilder() {
		return new ConcreteClusterBuilder(this);
	}

	/**
	 * @return the cluster Builder
	 */
	public static ClusterBuilder builder() {
		return new ConcreteClusterBuilder();
	}

	public static class ConcreteClusterBuilder implements ClusterBuilder {

		MapReduceCluster m;

		ConcreteClusterBuilder() {
			this(new MapReduceCluster());
		}

		ConcreteClusterBuilder(MapReduceCluster m) {
			this.m = m;
		}

		@Override
		public ClusterBuilder name(String name) {
			m.name = name;
			return this;
		}

		@Override
		public ClusterBuilder hadoopVersion(String hadoopVersion) {
			m.hadoopVersion = hadoopVersion;
			return this;
		}

		@Override
		public ClusterBuilder pluginName(String pluginName) {
			m.pluginName = pluginName;
			return this;
		}

		@Override
		public ClusterBuilder template(String clusterTemplateId) {
			m.clusterTemplateId = clusterTemplateId;
			return this;
		}

		@Override
		public ClusterBuilder image(String imageId) {
			m.defaultImageId = imageId;
			return this;
		}

		public ClusterBuilder image(Image image) {
			m.defaultImageId = image.getId();
			return this;
		}

		@Override
		public ClusterBuilder keypairName(String keypairName) {
			m.userKeypairId = keypairName;
			return this;
		}

		@Override
		public ClusterBuilder managementNetworkId(String networkId) {
			m.managementNetworkId = networkId;
			return this;
		}

		@Override
		public ClusterBuilder addNodeGroup(NodeGroup nodeGroup) {
			if (m.nodeGroups == null)
				m.nodeGroups = Lists.newArrayList();
			m.nodeGroups.add((MapReduceNodeGroup) nodeGroup);
			return this;
		}

		@Override
		public ClusterBuilder addServiceConfig(String name, ServiceConfig config) {
			if (name != null && !name.isEmpty()) {
				if (m.clusterConfigs == null)
					m.clusterConfigs = new HashMap<String, MapReduceServiceConfig>();
				m.clusterConfigs.put(name, (MapReduceServiceConfig) config);
			}
			return this;
		}

		@Override
		public Cluster build() {
			return m;
		}

		@Override
		public ClusterBuilder from(Cluster in) {
			m = (MapReduceCluster) in;
			return this;
		}

	}

}
