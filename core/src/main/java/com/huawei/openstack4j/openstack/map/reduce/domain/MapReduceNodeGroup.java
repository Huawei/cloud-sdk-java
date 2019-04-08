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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.map.reduce.Instance;
import com.huawei.openstack4j.model.map.reduce.NodeGroup;
import com.huawei.openstack4j.model.map.reduce.ServiceConfig;
import com.huawei.openstack4j.model.map.reduce.builder.NodeGroupBuilder;
import com.huawei.openstack4j.openstack.common.ListResult;

/**
 * Model represent attributes of NodeGroup
 *
 * @author Ekasit Kijsipongse
 */
@JsonRootName("node_group")
public class MapReduceNodeGroup implements NodeGroup {

	private static final long serialVersionUID = 1L;

        private String name;
	private Integer count;
        @JsonProperty("node_group_template_id")
	private String nodeGroupTemplateId;
        @JsonProperty("created_at")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private Date createdAt;
        @JsonProperty("updated_at")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private Date updatedAt;
        @JsonProperty("image_id")
        private String imageId;
        @JsonProperty("volumes_size")
        private Integer volumesSize;
        @JsonProperty("volumes_per_node")
        private Integer volumesPerNode;
        @JsonProperty("floating_ip_pool")
        private String floatingNetworkId;
        @JsonProperty("flavor_id")
        private String flavorId;
        @JsonProperty("volume_mount_prefix")
        private String volumeMountPrefix;
        @JsonProperty("auto_security_group")
        private Boolean autoSecurityGroup;

        @JsonProperty("security_groups")
        private List<String> securityGroups;

        @JsonProperty("node_processes")
        private List<String> nodeProcesses;

        @JsonProperty("node_configs")
        private Map<String, MapReduceServiceConfig> serviceConfigs;


        @JsonProperty("instances")
        private List<MapReduceInstance> instances; // only in cluster json response

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NodeGroupBuilder toBuilder() {
		return new ConcreteNodeGroupBuilder(this);
	}

	/**
	 * @return the node group Builder
	 */
	public static NodeGroupBuilder builder() {
		return new ConcreteNodeGroupBuilder();
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
	public Integer getCount() {
		return count;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getNodeGroupTemplateId() {
		return nodeGroupTemplateId;
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
        public String getFloatingNetworkId() {
                return floatingNetworkId;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Integer getVolumesPerNode() {
                return volumesPerNode;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Integer getVolumesSize() {
                return volumesSize;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getVolumeMountPrefix() {
                return volumeMountPrefix;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getImageId() {
                return imageId;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getFlavorId() {
                return flavorId;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public List<String> getSecurityGroups() {
                return securityGroups;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Boolean isAutoSecurityGroup() {
                return autoSecurityGroup;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public List<String> getNodeProcesses() {
                return nodeProcesses;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Map<String, ? extends ServiceConfig> getServiceConfigs() {
                return serviceConfigs;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public List<? extends Instance> getInstances() {
                return instances;
        }


	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
                     .add("name", name)
                     .add("count", count)
                     .add("created_at",createdAt)
                     .add("updated_at",updatedAt)
                     .add("image_id", imageId)
                     .add("volumes_size", volumesSize)
                     .add("volumes_per_node", volumesPerNode)
                     .add("float_ip_pool",floatingNetworkId)
                     .add("flavor_id",flavorId)
                     .add("volume_mount_prefix",volumeMountPrefix)
                     .add("node_group_template_id", imageId)
                     .add("security_groups",securityGroups)
                     .add("auto_security_group",autoSecurityGroup)
                     .add("node_processes",nodeProcesses)
                     .add("node_configs",serviceConfigs)
                     .add("instances",instances)
                     .toString();
	}

	public static class NodeGroups extends ListResult<MapReduceNodeGroup> {

		private static final long serialVersionUID = 1L;

                @JsonProperty("node_groups")
		private List<MapReduceNodeGroup> nodeGroups;

		@Override
		protected List<MapReduceNodeGroup> value() {
			return nodeGroups;
		}
	}

	public static class ConcreteNodeGroupBuilder implements NodeGroupBuilder {

		private MapReduceNodeGroup m;

		ConcreteNodeGroupBuilder() {
			this(new MapReduceNodeGroup());
		}

		ConcreteNodeGroupBuilder(MapReduceNodeGroup m) {
			this.m = m;
		}

		@Override
		public NodeGroup build() {
			return m;
		}

		@Override
		public NodeGroupBuilder from(NodeGroup in) {
			m = (MapReduceNodeGroup) in;
			return this;
                }

		@Override
		public NodeGroupBuilder name(String name) {
			m.name = name;
			return this;
		}

		@Override
		public NodeGroupBuilder nodeGroupTemplateId(String templateId) {
			m.nodeGroupTemplateId = templateId;
			return this;
		}

		@Override
		public NodeGroupBuilder count(int count) {
			m.count = count;
			return this;
		}

		@Override
		public NodeGroupBuilder floatingIpPool(String networkId) {
			m.floatingNetworkId = networkId;
			return this;
		}

		@Override
		public NodeGroupBuilder flavor(String flavorId) {
			m.flavorId = flavorId;
			return this;
		}

		@Override
		public NodeGroupBuilder setAutoSecurityGroup(boolean isAutoSecurityGroup) {
			m.autoSecurityGroup = isAutoSecurityGroup;
			return this;
		}

		@Override
		public NodeGroupBuilder addSecurityGroup(String id) {
                        if (id != null && !id.isEmpty()) {
                           if (m.securityGroups == null)
                              m.securityGroups = Lists.newArrayList();
                           m.securityGroups.add(id);
                        }
			return this;
		}

		@Override
		public NodeGroupBuilder addNodeProcess(String name) {
                        if (name != null && !name.isEmpty()) {
                           if (m.nodeProcesses == null)
                              m.nodeProcesses = Lists.newArrayList();
                           m.nodeProcesses.add(name);
                        }
			return this;
		}

		@Override
                public NodeGroupBuilder addServiceConfig(String name, ServiceConfig config) {
                        if (name != null && !name.isEmpty()) {
                           if (m.serviceConfigs == null)
                              m.serviceConfigs = new HashMap<String,MapReduceServiceConfig>();
                           m.serviceConfigs.put(name,(MapReduceServiceConfig) config);
                        }
                        return this;
                }

	}
}
