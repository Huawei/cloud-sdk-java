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
package com.huawei.openstack4j.api;

import com.huawei.openstack4j.model.artifact.builder.ArtifactUpdateBuilder;
import com.huawei.openstack4j.model.artifact.builder.ToscaTemplatesArtifactBuilder;
import com.huawei.openstack4j.model.barbican.builder.ContainerCreateBuilder;
import com.huawei.openstack4j.model.barbican.builder.ContainerSecretBuilder;
import com.huawei.openstack4j.model.common.builder.LinkBuilder;
import com.huawei.openstack4j.model.compute.builder.BlockDeviceMappingBuilder;
import com.huawei.openstack4j.model.compute.builder.ComputeBuilders;
import com.huawei.openstack4j.model.compute.builder.FlavorBuilder;
import com.huawei.openstack4j.model.compute.builder.FloatingIPBuilder;
import com.huawei.openstack4j.model.compute.builder.QuotaSetUpdateBuilder;
import com.huawei.openstack4j.model.compute.builder.SecurityGroupRuleBuilder;
import com.huawei.openstack4j.model.compute.builder.ServerCreateBuilder;
import com.huawei.openstack4j.model.dns.v2.builder.DNSV2Builders;
import com.huawei.openstack4j.model.dns.v2.builder.RecordsetBuilder;
import com.huawei.openstack4j.model.dns.v2.builder.ZoneBuilder;
import com.huawei.openstack4j.model.gbp.builder.ExternalPolicyBuilder;
import com.huawei.openstack4j.model.gbp.builder.ExternalRoutesBuilder;
import com.huawei.openstack4j.model.gbp.builder.ExternalSegmentBuilder;
import com.huawei.openstack4j.model.gbp.builder.L2PolicyBuilder;
import com.huawei.openstack4j.model.gbp.builder.L3PolicyBuilder;
import com.huawei.openstack4j.model.gbp.builder.NatPoolBuilder;
import com.huawei.openstack4j.model.gbp.builder.NetworkServicePolicyBuilder;
import com.huawei.openstack4j.model.gbp.builder.PolicyActionCreateBuilder;
import com.huawei.openstack4j.model.gbp.builder.PolicyActionUpdateBuilder;
import com.huawei.openstack4j.model.gbp.builder.PolicyClassifierBuilder;
import com.huawei.openstack4j.model.gbp.builder.PolicyClassifierUpdateBuilder;
import com.huawei.openstack4j.model.gbp.builder.PolicyRuleBuilder;
import com.huawei.openstack4j.model.gbp.builder.PolicyRuleSetBuilder;
import com.huawei.openstack4j.model.gbp.builder.PolicyTargetBuilder;
import com.huawei.openstack4j.model.gbp.builder.PolicyTargetGroupBuilder;
import com.huawei.openstack4j.model.heat.ResourceHealth;
import com.huawei.openstack4j.model.heat.SoftwareConfig;
import com.huawei.openstack4j.model.heat.StackCreate;
import com.huawei.openstack4j.model.heat.StackUpdate;
import com.huawei.openstack4j.model.heat.Template;
import com.huawei.openstack4j.model.heat.builder.OrchestrationBuilders;
import com.huawei.openstack4j.model.heat.builder.ResourceHealthBuilder;
import com.huawei.openstack4j.model.heat.builder.SoftwareConfigBuilder;
import com.huawei.openstack4j.model.heat.builder.StackCreateBuilder;
import com.huawei.openstack4j.model.heat.builder.StackUpdateBuilder;
import com.huawei.openstack4j.model.heat.builder.TemplateBuilder;
import com.huawei.openstack4j.model.identity.v2.builder.IdentityV2Builders;
import com.huawei.openstack4j.model.identity.v3.builder.CredentialBuilder;
import com.huawei.openstack4j.model.identity.v3.builder.DomainBuilder;
import com.huawei.openstack4j.model.identity.v3.builder.EndpointBuilder;
import com.huawei.openstack4j.model.identity.v3.builder.GroupBuilder;
import com.huawei.openstack4j.model.identity.v3.builder.IdentityV3Builders;
import com.huawei.openstack4j.model.identity.v3.builder.PolicyBuilder;
import com.huawei.openstack4j.model.identity.v3.builder.ProjectBuilder;
import com.huawei.openstack4j.model.identity.v3.builder.RegionBuilder;
import com.huawei.openstack4j.model.identity.v3.builder.RoleBuilder;
import com.huawei.openstack4j.model.identity.v3.builder.ServiceBuilder;
import com.huawei.openstack4j.model.identity.v3.builder.UserBuilder;
import com.huawei.openstack4j.model.image.builder.ImageBuilder;
import com.huawei.openstack4j.model.image.v2.builder.ImageUpdateBuilder;
import com.huawei.openstack4j.model.image.v2.builder.TaskBuilder;
import com.huawei.openstack4j.model.magnum.BaymodelBuilder;
import com.huawei.openstack4j.model.manila.builder.SecurityServiceCreateBuilder;
import com.huawei.openstack4j.model.manila.builder.ShareCreateBuilder;
import com.huawei.openstack4j.model.manila.builder.ShareManageBuilder;
import com.huawei.openstack4j.model.manila.builder.ShareNetworkCreateBuilder;
import com.huawei.openstack4j.model.manila.builder.ShareSnapshotCreateBuilder;
import com.huawei.openstack4j.model.manila.builder.ShareTypeCreateBuilder;
import com.huawei.openstack4j.model.manila.builder.SharedFileSystemBuilders;
import com.huawei.openstack4j.model.map.reduce.builder.ClusterBuilder;
import com.huawei.openstack4j.model.map.reduce.builder.ClusterTemplateBuilder;
import com.huawei.openstack4j.model.map.reduce.builder.DataProcessingBuilders;
import com.huawei.openstack4j.model.map.reduce.builder.DataSourceBuilder;
import com.huawei.openstack4j.model.map.reduce.builder.JobBinaryBuilder;
import com.huawei.openstack4j.model.map.reduce.builder.JobBuilder;
import com.huawei.openstack4j.model.map.reduce.builder.JobConfigBuilder;
import com.huawei.openstack4j.model.map.reduce.builder.JobExecutionBuilder;
import com.huawei.openstack4j.model.map.reduce.builder.NodeGroupBuilder;
import com.huawei.openstack4j.model.map.reduce.builder.NodeGroupTemplateBuilder;
import com.huawei.openstack4j.model.map.reduce.builder.ServiceConfigBuilder;
import com.huawei.openstack4j.model.murano.v1.builder.AppCatalogBuilders;
import com.huawei.openstack4j.model.murano.v1.builder.EnvironmentBuilder;
import com.huawei.openstack4j.model.network.builder.ExtraDhcpOptBuilder;
import com.huawei.openstack4j.model.network.builder.NetFloatingIPBuilder;
import com.huawei.openstack4j.model.network.builder.NetQuotaBuilder;
import com.huawei.openstack4j.model.network.builder.NetSecurityGroupBuilder;
import com.huawei.openstack4j.model.network.builder.NetSecurityGroupRuleBuilder;
import com.huawei.openstack4j.model.network.builder.NetSecurityGroupUpdateBuilder;
import com.huawei.openstack4j.model.network.builder.NetworkBuilder;
import com.huawei.openstack4j.model.network.builder.NetworkBuilders;
import com.huawei.openstack4j.model.network.builder.NetworkUpdateBuilder;
import com.huawei.openstack4j.model.network.builder.PortBuilder;
import com.huawei.openstack4j.model.network.builder.RouterBuilder;
import com.huawei.openstack4j.model.network.builder.SubnetBuilder;
import com.huawei.openstack4j.model.network.ext.builder.FirewallBuilder;
import com.huawei.openstack4j.model.network.ext.builder.FirewallPolicyBuilder;
import com.huawei.openstack4j.model.network.ext.builder.FirewallPolicyUpdateBuilder;
import com.huawei.openstack4j.model.network.ext.builder.FirewallRuleBuilder;
import com.huawei.openstack4j.model.network.ext.builder.FirewallRuleUpdateBuilder;
import com.huawei.openstack4j.model.network.ext.builder.FirewallUpdateBuilder;
import com.huawei.openstack4j.model.network.ext.builder.HealthMonitorAssociateBuilder;
import com.huawei.openstack4j.model.network.ext.builder.HealthMonitorBuilder;
import com.huawei.openstack4j.model.network.ext.builder.HealthMonitorUpdateBuilder;
import com.huawei.openstack4j.model.network.ext.builder.HealthMonitorV2Builder;
import com.huawei.openstack4j.model.network.ext.builder.HealthMonitorV2UpdateBuilder;
import com.huawei.openstack4j.model.network.ext.builder.LbPoolBuilder;
import com.huawei.openstack4j.model.network.ext.builder.LbPoolUpdateBuilder;
import com.huawei.openstack4j.model.network.ext.builder.LbPoolV2Builder;
import com.huawei.openstack4j.model.network.ext.builder.LbPoolV2UpdateBuilder;
import com.huawei.openstack4j.model.network.ext.builder.ListenerV2Builder;
import com.huawei.openstack4j.model.network.ext.builder.ListenerV2UpdateBuilder;
import com.huawei.openstack4j.model.network.ext.builder.LoadBalancerV2Builder;
import com.huawei.openstack4j.model.network.ext.builder.LoadBalancerV2UpdateBuilder;
import com.huawei.openstack4j.model.network.ext.builder.MemberBuilder;
import com.huawei.openstack4j.model.network.ext.builder.MemberUpdateBuilder;
import com.huawei.openstack4j.model.network.ext.builder.MemberV2Builder;
import com.huawei.openstack4j.model.network.ext.builder.MemberV2UpdateBuilder;
import com.huawei.openstack4j.model.network.ext.builder.SessionPersistenceBuilder;
import com.huawei.openstack4j.model.network.ext.builder.VipBuilder;
import com.huawei.openstack4j.model.network.ext.builder.VipUpdateBuilder;
import com.huawei.openstack4j.model.storage.block.builder.BlockQuotaSetBuilder;
import com.huawei.openstack4j.model.storage.block.builder.StorageBuilders;
import com.huawei.openstack4j.model.storage.block.builder.VolumeBackupCreateBuilder;
import com.huawei.openstack4j.model.storage.block.builder.VolumeBuilder;
import com.huawei.openstack4j.model.storage.block.builder.VolumeSnapshotBuilder;
import com.huawei.openstack4j.model.storage.block.builder.VolumeTypeBuilder;
import com.huawei.openstack4j.model.tacker.builder.NfvBuilders;
import com.huawei.openstack4j.model.telemetry.builder.AlarmBuilder;
import com.huawei.openstack4j.model.telemetry.builder.TelemetryBuilders;
import com.huawei.openstack4j.model.workflow.builder.WorkflowBuilders;
import com.huawei.openstack4j.openstack.artifact.domain.ArtifactUpdateModel;
import com.huawei.openstack4j.openstack.artifact.domain.ToscaTemplates;
import com.huawei.openstack4j.openstack.barbican.domain.BarbicanContainer;
import com.huawei.openstack4j.openstack.barbican.domain.BarbicanContainerSecret;
import com.huawei.openstack4j.openstack.common.GenericLink;
import com.huawei.openstack4j.openstack.compute.builder.NovaBuilders;
import com.huawei.openstack4j.openstack.compute.domain.NovaBlockDeviceMappingCreate;
import com.huawei.openstack4j.openstack.compute.domain.NovaFlavor;
import com.huawei.openstack4j.openstack.compute.domain.NovaFloatingIP;
import com.huawei.openstack4j.openstack.compute.domain.NovaQuotaSetUpdate;
import com.huawei.openstack4j.openstack.compute.domain.NovaSecGroupExtension.SecurityGroupRule;
import com.huawei.openstack4j.openstack.compute.domain.NovaServerCreate;
import com.huawei.openstack4j.openstack.dns.v2.builder.DesignateV2Builders;
import com.huawei.openstack4j.openstack.dns.v2.domain.DesignatePTR;
import com.huawei.openstack4j.openstack.dns.v2.domain.DesignatePTR.DesignatePTRBuilder;
import com.huawei.openstack4j.openstack.dns.v2.domain.DesignateRecordset;
import com.huawei.openstack4j.openstack.dns.v2.domain.DesignateZone;
import com.huawei.openstack4j.openstack.gbp.domain.GbpExternalPolicyCreate;
import com.huawei.openstack4j.openstack.gbp.domain.GbpExternalRoutes;
import com.huawei.openstack4j.openstack.gbp.domain.GbpExternalSegment;
import com.huawei.openstack4j.openstack.gbp.domain.GbpL2Policy;
import com.huawei.openstack4j.openstack.gbp.domain.GbpL3Policy;
import com.huawei.openstack4j.openstack.gbp.domain.GbpNatPool;
import com.huawei.openstack4j.openstack.gbp.domain.GbpNetworkServicePolicy;
import com.huawei.openstack4j.openstack.gbp.domain.GbpPolicyAction;
import com.huawei.openstack4j.openstack.gbp.domain.GbpPolicyActionUpdate;
import com.huawei.openstack4j.openstack.gbp.domain.GbpPolicyClassifier;
import com.huawei.openstack4j.openstack.gbp.domain.GbpPolicyClassifierUpdate;
import com.huawei.openstack4j.openstack.gbp.domain.GbpPolicyRule;
import com.huawei.openstack4j.openstack.gbp.domain.GbpPolicyRuleSet;
import com.huawei.openstack4j.openstack.gbp.domain.GbpPolicyTarget;
import com.huawei.openstack4j.openstack.gbp.domain.GbpPolicyTargetGroupCreate;
import com.huawei.openstack4j.openstack.heat.builder.HeatBuilders;
import com.huawei.openstack4j.openstack.heat.domain.HeatResourceHealth;
import com.huawei.openstack4j.openstack.heat.domain.HeatSoftwareConfig;
import com.huawei.openstack4j.openstack.heat.domain.HeatStackCreate;
import com.huawei.openstack4j.openstack.heat.domain.HeatStackUpdate;
import com.huawei.openstack4j.openstack.heat.domain.HeatTemplate;
import com.huawei.openstack4j.openstack.identity.v2.builder.KeystoneV2Builders;
import com.huawei.openstack4j.openstack.identity.v3.builder.KeystoneV3Builders;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneCredential;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneDomain;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneEndpoint;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneGroup;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystonePolicy;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneProject;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneRegion;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneRole;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneService;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneUser;
import com.huawei.openstack4j.openstack.image.domain.GlanceImage;
import com.huawei.openstack4j.openstack.image.v2.domain.GlanceImageUpdate;
import com.huawei.openstack4j.openstack.image.v2.domain.GlanceTask;
import com.huawei.openstack4j.openstack.magnum.MagnumBaymodel;
import com.huawei.openstack4j.openstack.manila.builder.ManilaBuilders;
import com.huawei.openstack4j.openstack.manila.domain.ManilaSecurityServiceCreate;
import com.huawei.openstack4j.openstack.manila.domain.ManilaShareCreate;
import com.huawei.openstack4j.openstack.manila.domain.ManilaShareManage;
import com.huawei.openstack4j.openstack.manila.domain.ManilaShareNetworkCreate;
import com.huawei.openstack4j.openstack.manila.domain.ManilaShareSnapshotCreate;
import com.huawei.openstack4j.openstack.manila.domain.ManilaShareTypeCreate;
import com.huawei.openstack4j.openstack.map.reduce.builder.MapReduceBuilders;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceCluster;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceClusterTemplate;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceDataSource;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceJob;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceJobBinary;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceJobConfig;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceJobExecution;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceNodeGroup;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceNodeGroupTemplate;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceServiceConfig;
import com.huawei.openstack4j.openstack.murano.v1.builder.MuranoBuilders;
import com.huawei.openstack4j.openstack.murano.v1.domain.MuranoEnvironment;
import com.huawei.openstack4j.openstack.networking.builder.NeutronBuilders;
import com.huawei.openstack4j.openstack.networking.domain.NeutronExtraDhcpOptCreate;
import com.huawei.openstack4j.openstack.networking.domain.NeutronFloatingIP;
import com.huawei.openstack4j.openstack.networking.domain.NeutronNetQuota;
import com.huawei.openstack4j.openstack.networking.domain.NeutronNetwork;
import com.huawei.openstack4j.openstack.networking.domain.NeutronNetworkUpdate;
import com.huawei.openstack4j.openstack.networking.domain.NeutronPort;
import com.huawei.openstack4j.openstack.networking.domain.NeutronRouter;
import com.huawei.openstack4j.openstack.networking.domain.NeutronSecurityGroup;
import com.huawei.openstack4j.openstack.networking.domain.NeutronSecurityGroupRule;
import com.huawei.openstack4j.openstack.networking.domain.NeutronSecurityGroupUpdate;
import com.huawei.openstack4j.openstack.networking.domain.NeutronSubnet;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronFirewall;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronFirewallPolicy;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronFirewallPolicyUpdate;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronFirewallRule;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronFirewallRuleUpdate;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronFirewallUpdate;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronHealthMonitor;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronHealthMonitorAssociate;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronHealthMonitorUpdate;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronHealthMonitorV2;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronHealthMonitorV2Update;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronLbPool;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronLbPoolUpdate;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronLbPoolV2;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronLbPoolV2Update;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronListenerV2;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronListenerV2Update;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronLoadBalancerV2;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronLoadBalancerV2Update;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronMember;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronMemberUpdate;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronMemberV2;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronMemberV2Update;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronSessionPersistence;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronVip;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronVipUpdate;
import com.huawei.openstack4j.openstack.storage.block.builder.CinderBuilders;
import com.huawei.openstack4j.openstack.storage.block.domain.CinderBlockQuotaSet;
import com.huawei.openstack4j.openstack.storage.block.domain.CinderVolume;
import com.huawei.openstack4j.openstack.storage.block.domain.CinderVolumeBackupCreate;
import com.huawei.openstack4j.openstack.storage.block.domain.CinderVolumeSnapshot;
import com.huawei.openstack4j.openstack.storage.block.domain.CinderVolumeType;
import com.huawei.openstack4j.openstack.storage.block.domain.VBSVolumeBackupCreate;
import com.huawei.openstack4j.openstack.storage.block.domain.VBSVolumeBackupCreate.VBSVolumeBackupCreateBuilder;
import com.huawei.openstack4j.openstack.tacker.builders.TackerBuilders;
import com.huawei.openstack4j.openstack.telemetry.builder.CeilometerBuilders;
import com.huawei.openstack4j.openstack.telemetry.domain.CeilometerAlarm;
import com.huawei.openstack4j.openstack.trove.builder.TroveBuilders;
import com.huawei.openstack4j.openstack.workflow.builder.MistralBuilders;

/**
 * A utility class to quickly access available Builders within the OpenStack API
 *
 * @author Jeremy Unruh
 */
public class Builders {

	/**
	 * The builder to create a Link
	 *
	 * @return the link builder
	 */
	public static LinkBuilder link() {
		return GenericLink.builder();
	}

	/**
	 * The builder to create a Server
	 *
	 * @return the server create builder
	 */
	public static ServerCreateBuilder server() {
		return NovaServerCreate.builder();
	}

	public static BlockDeviceMappingBuilder blockDeviceMapping() {
		return NovaBlockDeviceMappingCreate.builder();
	}

	public static ExtraDhcpOptBuilder extraDhcpOpt() {
		return NeutronExtraDhcpOptCreate.builder();
	}

	/**
	 * The builder to create a Flavor.
	 *
	 * @return the flavor builder
	 */
	public static FlavorBuilder flavor() {
		return NovaFlavor.builder();
	}

	/**
	 * The builder to create a ToscaTemplatesArtifact
	 *
	 * @return the ToscaTemplatesArtifactBuilder
	 */
	public static ToscaTemplatesArtifactBuilder toscaTemplatesArtifact() {
		return ToscaTemplates.builder();
	}

	/**
	 * The builder to update an Artifact
	 *
	 * @return the ArtifactUpdateBuilder
	 */
	public static ArtifactUpdateBuilder artifactUpdate() {
		return ArtifactUpdateModel.builder();
	}

	/**
	 * The builder to create a Network
	 *
	 * @return the network builder
	 */
	public static NetworkBuilder network() {
		return NeutronNetwork.builder();
	}

	/**
	 * The builder to create a Subnet
	 *
	 * @return the subnet builder
	 */
	public static SubnetBuilder subnet() {
		return NeutronSubnet.builder();
	}

	/**
	 * The builder to create a Port
	 *
	 * @return the port builder
	 */
	public static PortBuilder port() {
		return NeutronPort.builder();
	}

	/**
	 * The builder to create a Router
	 *
	 * @return the router builder
	 */
	public static RouterBuilder router() {
		return NeutronRouter.builder();
	}

	/**
	 * The builder to create a Glance Image
	 *
	 * @return the image builder
	 */
	public static ImageBuilder image() {
		return GlanceImage.builder();
	}

	/**
	 * The builder to create a Block Volume
	 *
	 * @return the volume builder
	 */
	public static VolumeBuilder volume() {
		return CinderVolume.builder();
	}

	/**
	 * The builder to create a Volume Type
	 * 
	 * @return the volume type builder
	 */
	public static VolumeTypeBuilder volumeType() {
		return CinderVolumeType.builder();
	}

	/**
	 * The builder to create a Block Volume Snapshot
	 *
	 * @return the snapshot builder
	 */
	public static VolumeSnapshotBuilder volumeSnapshot() {
		return CinderVolumeSnapshot.builder();
	}

	/**
	 * The builder to create a Block Volume Backup
	 * @return the volume backup builder
	 */
	public static VolumeBackupCreateBuilder volumeBackupCreate() {
		return CinderVolumeBackupCreate.builder();
	}

	/**
	 * The builder to create a Compute/Nova Floating IP
	 *
	 * @return the floating ip builder
	 */
	public static FloatingIPBuilder floatingIP() {
		return NovaFloatingIP.builder();
	}

	/**
	 * A Builder which creates a Security Group Rule
	 *
	 * @return the security group rule builder
	 */
	public static SecurityGroupRuleBuilder secGroupRule() {
		return SecurityGroupRule.builder();
	}

	/**
	 * The builder to create a Neutron Security Group
	 *
	 * @return the security group builder
	 */
	public static NetSecurityGroupBuilder securityGroup() {
		return NeutronSecurityGroup.builder();
	}

	/**
	 * The builder to update a security group
	 *
	 * @return the security group update builder
	 */
	public static NetSecurityGroupUpdateBuilder securityGroupUpdate() {
		return NeutronSecurityGroupUpdate.builder();
	}

	/**
	 * The builder to create a Neutron Security Group Rule
	 *
	 * @return the security group builder
	 */
	public static NetSecurityGroupRuleBuilder securityGroupRule() {
		return NeutronSecurityGroupRule.builder();
	}

	/**
	 * The builder to create a Neutron Floating IP Address
	 *
	 * @return the floating ip builder
	 */
	public static NetFloatingIPBuilder netFloatingIP() {
		return NeutronFloatingIP.builder();
	}

	/**
	 * The builder to create a {@link Template}
	 *
	 * @return the TemplateBuilder
	 */
	public static TemplateBuilder template() {
		return HeatTemplate.build();
	}

	/**
	 * The builder to create a {@link StackCreate}
	 *
	 * @return the StackCreate builder
	 */
	public static StackCreateBuilder stack() {
		return HeatStackCreate.build();
	}

	/**
	 * The builder to create a {@link SoftwareConfig}
	 *
	 * @return the software config builder
	 */
	public static SoftwareConfigBuilder softwareConfig() {
		return new HeatSoftwareConfig.Builder();
	}

	/**
	 * The builder to create a {@link StackUpdate}
	 *
	 * @return the StackUpdate builder
	 */
	public static StackUpdateBuilder stackUpdate() {
		return HeatStackUpdate.builder();
	}

	/**
	 * The builder to create a {@link ResourceHealth}
	 * @return
	 */
	public static ResourceHealthBuilder resourceHealth() {
		return HeatResourceHealth.builder();
	}

	/**
	 * The builder to create NetQuota entities
	 *
	 * @return the NetQuota builder
	 */
	public static NetQuotaBuilder netQuota() {
		return NeutronNetQuota.builder();
	}

	/**
	 * The builder to update a network
	 *
	 * @return the NetworkUpdateBuilder
	 */
	public static NetworkUpdateBuilder networkUpdate() {
		return NeutronNetworkUpdate.builder();
	}

	/**
	 * The builder to create a lb member
	 *
	 * @return the Member Builder
	 */
	public static MemberBuilder member() {
		return NeutronMember.builder();
	}

	/**
	 * The builder to update a lb member
	 *
	 * @return the MemberUpdate Builder
	 */
	public static MemberUpdateBuilder memberUpdate() {
		return NeutronMemberUpdate.builder();
	}

	/**
	 * The builder to create and update a sessionPersistence
	 *
	 * @return SessionPersistenceBuilder
	 */
	public static SessionPersistenceBuilder sessionPersistence() {
		return NeutronSessionPersistence.builder();
	}

	/**
	 * The builder to create a vip.
	 *
	 * @return VipBuilder the vip builder
	 */
	public static VipBuilder vip() {
		return NeutronVip.builder();
	}

	/**
	 * The builder to update a vip.
	 *
	 * @return VipUpdateBuilder
	 */
	public static VipUpdateBuilder vipUpdate() {
		return NeutronVipUpdate.builder();
	}

	/**
	 * The builder to create a healthMonitor
	 *
	 * @return HealthMonitorBuilder
	 */
	public static HealthMonitorBuilder healthMonitor() {
		return NeutronHealthMonitor.builder();
	}

	/**
	 * The builder to update a healthMonitor
	 *
	 * @return HealthMonitorUpdateBuilder
	 */
	public static HealthMonitorUpdateBuilder healthMonitorUpdate() {
		return NeutronHealthMonitorUpdate.builder();
	}

	/**
	 * The builder to create a firewall
	 *
	 * @return FirewallBuilder
	 */
	public static FirewallBuilder firewall() {
		return NeutronFirewall.builder();
	}

	/**
	 * The builder to update a healthMonitor
	 *
	 * @return FirewallUpdateBuilder
	 */
	public static FirewallUpdateBuilder firewallUpdate() {
		return NeutronFirewallUpdate.builder();
	}

	/**
	 * The builder to create a firewallRule
	 *
	 * @return FirewallRuleBuilder
	 */
	public static FirewallRuleBuilder firewallRule() {
		return NeutronFirewallRule.builder();
	}

	/**
	 * The builder to update a firewallRule
	 *
	 * @return FirewallUpdateBuilder
	 */
	public static FirewallRuleUpdateBuilder firewallRuleUpdate() {
		return NeutronFirewallRuleUpdate.builder();
	}

	/**
	 * The builder to create a firewallPolicy
	 *
	 * @return FirewallPolicyBuilder
	 */
	public static FirewallPolicyBuilder firewallPolicy() {
		return NeutronFirewallPolicy.builder();
	}

	/**
	 * The builder to update a firewallPolicy
	 *
	 * @return FirewallPolicyUpdateBuilder
	 */
	public static FirewallPolicyUpdateBuilder firewallPolicyUpdate() {
		return NeutronFirewallPolicyUpdate.builder();
	}

	/**
	 * The builder to create a lbPool
	 *
	 * @return LbPoolBuilder
	 */
	public static LbPoolBuilder lbPool() {
		return NeutronLbPool.builder();
	}

	/**
	 * The builder to update a lbPool
	 *
	 * @return LbPoolUpdateBuilder
	 */
	public static LbPoolUpdateBuilder lbPoolUpdate() {
		return NeutronLbPoolUpdate.builder();
	}

	/**
	 * The builder to create a lbPool
	 *
	 * @return HealthMonitorAssociateBuilder
	 */
	public static HealthMonitorAssociateBuilder lbPoolAssociateHealthMonitor() {
		return NeutronHealthMonitorAssociate.builder();
	}

	/**
	 * The builder to create a map reduce cluster
	 *
	 * @return the cluster builder
	 */
	public static ClusterBuilder cluster() {
		return MapReduceCluster.builder();
	}

	/**
	 * The builder to create a map reduce cluster template
	 *
	 * @return the cluster template builder
	 */
	public static ClusterTemplateBuilder clusterTemplate() {
		return MapReduceClusterTemplate.builder();
	}

	/**
	 * The builder to create a map reduce node group
	 *
	 * @return the node group builder
	 */
	public static NodeGroupBuilder nodeGroup() {
		return MapReduceNodeGroup.builder();
	}

	/**
	 * The builder to create a map reduce node group template
	 *
	 * @return the node group template builder
	 */
	public static NodeGroupTemplateBuilder nodeGroupTemplate() {
		return MapReduceNodeGroupTemplate.builder();
	}

	/**
	 * The builder to create a map reduce service configuration
	 *
	 * @return the service configuration builder
	 */
	public static ServiceConfigBuilder serviceConfig() {
		return MapReduceServiceConfig.builder();
	}

	/**
	 * This builder which creates a QuotaSet for updates
	 *
	 * @return the QuotaSet update builder
	 */
	public static QuotaSetUpdateBuilder quotaSet() {
		return NovaQuotaSetUpdate.builder();
	}

	/**
	 * The builder to create an Alarm
	 *
	 * @return the image builder
	 */
	public static AlarmBuilder alarm() {
		return CeilometerAlarm.builder();
	}

	/**
	 * The builder which creates a BlockQuotaSet
	 *
	 * @return the block quota-set builder
	 */
	public static BlockQuotaSetBuilder blockQuotaSet() {
		return CinderBlockQuotaSet.builder();
	}

	/**
	 * The builder which creates a map reduce Data Source
	 *
	 * @return the data source builder
	 */
	public static DataSourceBuilder dataSource() {
		return MapReduceDataSource.builder();
	}

	/**
	 * The builder which creates a map reduce Job Binary
	 *
	 * @return the job binary builder
	 */
	public static JobBinaryBuilder jobBinary() {
		return MapReduceJobBinary.builder();
	}

	/**
	 * The builder which creates a map reduce Job
	 *
	 * @return the job builder
	 */
	public static JobBuilder job() {
		return MapReduceJob.builder();
	}

	/**
	 * The builder which creates a job configuration for map reduce job execution
	 *
	 * @return the job config builder
	 */
	public static JobConfigBuilder jobConfig() {
		return MapReduceJobConfig.builder();
	}

	/**
	 * The builder which creates a map reduce job execution
	 *
	 * @return the job execution builder
	 */
	public static JobExecutionBuilder jobExecution() {
		return MapReduceJobExecution.builder();
	}

	/**
	 * The builder which creates manila security services
	 *
	 * @return the security service builder
	 */
	public static SecurityServiceCreateBuilder securityService() {
		return ManilaSecurityServiceCreate.builder();
	}

	/**
	 * The builder which creates manila share networks.
	 *
	 * @return the share network builder
	 */
	public static ShareNetworkCreateBuilder shareNetwork() {
		return ManilaShareNetworkCreate.builder();
	}

	/**
	 * The builder which creates manila shares.
	 *
	 * @return the share builder
	 */
	public static ShareCreateBuilder share() {
		return ManilaShareCreate.builder();
	}

	/**
	 * The builder which creates share types.
	 *
	 * @return the shae type builder
	 */
	public static ShareTypeCreateBuilder shareType() {
		return ManilaShareTypeCreate.builder();
	}

	/**
	 * The builder which creates manila share snapshots.
	 *
	 * @return the share builder
	 */
	public static ShareSnapshotCreateBuilder shareSnapshot() {
		return ManilaShareSnapshotCreate.builder();
	}

	/**
	 * The builder which creates manila share manages
	 *
	 * @return the share manage builder
	 */
	public static ShareManageBuilder shareManage() {
		return ManilaShareManage.builder();
	}

	/**
	 * The builder to create a Region
	 *
	 * @return the region builder
	 */
	public static RegionBuilder region() {
		return KeystoneRegion.builder();
	}

	/**
	 * The builder to create a Credential.
	 *
	 * @return the credential builder
	 */
	public static CredentialBuilder credential() {
		return KeystoneCredential.builder();
	}

	/**
	 * The builder to create a Domain.
	 *
	 * @return the domain builder
	 */
	public static DomainBuilder domain() {
		return KeystoneDomain.builder();
	}

	/**
	 * The builder to create a Endpoint.
	 *
	 * @return the endpoint builder
	 */
	public static EndpointBuilder endpoint() {
		return KeystoneEndpoint.builder();
	}

	/**
	 * The builder to create a Group.
	 *
	 * @return the group builder
	 */
	public static GroupBuilder group() {
		return KeystoneGroup.builder();
	}

	/**
	 * The builder to create a Policy.
	 *
	 * @return the policy builder
	 */
	public static PolicyBuilder policy() {
		return KeystonePolicy.builder();
	}

	/**
	 * The builder to create a Project.
	 *
	 * @return the project builder
	 */
	public static ProjectBuilder project() {
		return KeystoneProject.builder();
	}

	/**
	 * The builder to create a Role.
	 *
	 * @return the role builder
	 */
	public static RoleBuilder role() {
		return KeystoneRole.builder();
	}

	/**
	 * The builder to create a Service.
	 *
	 * @return the service builder
	 */
	public static ServiceBuilder service() {
		return KeystoneService.builder();
	}

	/**
	 * The builder to create a User.
	 *
	 * @return the user builder
	 */
	public static UserBuilder user() {
		return KeystoneUser.builder();
	}

	/**
	 * The builder which creates external policy for gbp
	 *
	 * @return the external policy builder
	 */
	public static ExternalPolicyBuilder externalPolicy() {
		return GbpExternalPolicyCreate.builder();
	}

	/**
	 * The builder which creates external segment for gbp
	 *
	 * @return the external segment builder
	 */
	public static ExternalSegmentBuilder externalSegment() {
		return GbpExternalSegment.builder();
	}

	/**
	 * The builder which creates L2 policy for gbp
	 *
	 * @return the L2 policy builder
	 */
	public static L2PolicyBuilder l2Policy() {
		return GbpL2Policy.builder();
	}

	/**
	 * The builder which creates L3 policy for gbp
	 *
	 * @return the L3 policy builder
	 */
	public static L3PolicyBuilder l3Policy() {
		return GbpL3Policy.builder();
	}

	/**
	 * The builder which creates nat pool for gbp
	 *
	 * @return the nat pool builder
	 */
	public static NatPoolBuilder natPool() {
		return GbpNatPool.builder();
	}

	/**
	 * The builder which creates network service policy for gbp
	 *
	 *
	 * @return
	 */
	public static NetworkServicePolicyBuilder networkServicePolicy() {
		return GbpNetworkServicePolicy.builder();
	}

	/**
	 * The builder which creates policy action for gbp
	 *
	 * @return the policy action builder
	 */
	public static PolicyActionCreateBuilder policyAction() {
		return GbpPolicyAction.builder();
	}

	/**
	 * The builder which updates policy action for gbp
	 *
	 * @return the policy action builder
	 */
	public static PolicyActionUpdateBuilder policyActionUpdate() {
		return GbpPolicyActionUpdate.builder();
	}

	/**
	 * The builder which creates policy classifier for gbp
	 *
	 * @return the policy classifier builder
	 */
	public static PolicyClassifierBuilder policyClassifier() {
		return GbpPolicyClassifier.builder();
	}

	/**
	 * The builder which updates policy classifier for gbp
	 *
	 * @return the policy classifier builder
	 */
	public static PolicyClassifierUpdateBuilder policyClassifierUpdate() {
		return GbpPolicyClassifierUpdate.builder();
	}

	/**
	 * The builder which creates policy rule for gbp
	 *
	 * @return the policy rule builder
	 */
	public static PolicyRuleBuilder policyRule() {
		return GbpPolicyRule.builder();
	}

	/**
	 * The builder which creates policy rule set for gbp
	 *
	 * @return the policy rule set builder
	 */
	public static PolicyRuleSetBuilder policyRuleSet() {
		return GbpPolicyRuleSet.builder();
	}

	/**
	 * The builder which creates policy target for gbp
	 *
	 * @return the policy target builder
	 */
	public static PolicyTargetBuilder policyTarget() {
		return GbpPolicyTarget.builder();
	}

	/**
	 * The builder which creates policy target group for gbp
	 *
	 * @return the policy target group builder
	 */
	public static PolicyTargetGroupBuilder policyTargetGroup() {
		return GbpPolicyTargetGroupCreate.builder();
	}

	/**
	 * The builder which creates external routes for gbp
	 *
	 * @return the external routes builder
	 */
	public static ExternalRoutesBuilder externalRoutes() {
		return GbpExternalRoutes.builder();
	}

	// Builders.<service>().<object>() ..

	/**
	 * Identity V2 builders
	 *
	 * @return the keystone v2 builders
	 */
	public static IdentityV2Builders identityV2() {
		return new KeystoneV2Builders();
	}

	/**
	 * The Identity V3 builders
	 *
	 * @return the keystone v3 builders
	 */
	public static IdentityV3Builders identityV3() {
		return new KeystoneV3Builders();
	}

	/**
	 * The Compute builders
	 *
	 * @return the nova builders
	 */
	public static ComputeBuilders compute() {
		return new NovaBuilders();
	}

	/**
	 * The Storage builders
	 *
	 * @return the cinder builders
	 */
	public static StorageBuilders storage() {
		return new CinderBuilders();
	}

	/**
	 * The Orchestration builders
	 *
	 * @return the heat builders
	 */
	public static OrchestrationBuilders heat() {
		return new HeatBuilders();
	}

	/**
	 * The Network builders
	 *
	 * @return the neutron builders
	 */
	public static NetworkBuilders neutron() {
		return new NeutronBuilders();
	}

	/**
	 * The MapReduce builders
	 *
	 * @return the MapReduce builders
	 */
	public static DataProcessingBuilders mapReduce() {
		return new MapReduceBuilders();
	}

	/**
	 * The Ceilometer builders
	 *
	 * @return the ceilometer builders
	 */
	public static TelemetryBuilders ceilometer() {
		return new CeilometerBuilders();
	}

	/**
	 * The Manila builders
	 *
	 * @return the manila builders
	 */
	public static SharedFileSystemBuilders manila() {
		return new ManilaBuilders();
	}

	/**
	 * The Trove builders
	 *
	 * @return the trove builders
	 */
	public static TroveBuilders trove() {
		return new TroveBuilders();
	}

	/**
	 * LbaasV2 pool builder
	 *
	 * @return the lb pool v2 builder
	 */
	public static LbPoolV2Builder lbpoolV2() {
		return NeutronLbPoolV2.builder();
	}

	/**
	 * LbaasV2 pool update builder
	 *
	 *
	 * @return the lb pool v2 update builder
	 */
	public static LbPoolV2UpdateBuilder lbPoolV2Update() {
		return NeutronLbPoolV2Update.builder();
	}

	/**
	 * LbaasV2 member builder
	 *
	 *
	 * @return the member v2 builder
	 */
	public static MemberV2Builder memberV2() {
		return NeutronMemberV2.builder();
	}

	/**
	 * LbaasV2 member update builder
	 *
	 *
	 * @return the member v2 update builder
	 */
	public static MemberV2UpdateBuilder memberV2Update() {
		return NeutronMemberV2Update.builder();
	}

	/**
	 * LbaasV2 listener builder
	 *
	 *
	 * @return the listener builder
	 */
	public static ListenerV2Builder listenerV2() {
		return NeutronListenerV2.builder();
	}

	/**
	 * LbaasV2 listener update builder
	 *
	 *
	 * @return the listener v2 update builder
	 */
	public static ListenerV2UpdateBuilder listenerV2Update() {
		return NeutronListenerV2Update.builder();
	}

	/**
	 * LbaasV2 health monitor builder
	 *
	 *
	 * @return the health monitor v2 builder
	 */
	public static HealthMonitorV2Builder healthmonitorV2() {
		return NeutronHealthMonitorV2.builder();
	}

	/**
	 * LbaasV2 healthmonitor update builder
	 *
	 *
	 * @return the health monitor v2 update builder
	 */
	public static HealthMonitorV2UpdateBuilder healthMonitorV2Update() {
		return NeutronHealthMonitorV2Update.builder();
	}

	/**
	 * LbaasV2 loadbalancer builder
	 *
	 *
	 * @return the loadbalancer v2 builder
	 */
	public static LoadBalancerV2Builder loadbalancerV2() {
		return NeutronLoadBalancerV2.builder();
	}

	/**
	 * LbaasV2 loadbalancer update builder
	 *
	 *
	 * @return the loadbalancer v2 update builder
	 */
	public static LoadBalancerV2UpdateBuilder loadBalancerV2Update() {
		return NeutronLoadBalancerV2Update.builder();
	}

	/**
	 * Magnum builder
	 * @return the magnum builder
	 */

	public static BaymodelBuilder baymodel() {
		return MagnumBaymodel.builder();
	}

	/**
	 * Barbican container builder
	 * @return the container builder
	 */
	public static ContainerCreateBuilder container() {
		return BarbicanContainer.builder();
	}

	/**
	 * Barbican secret builder
	 * @return the secret builder
	 */
	public static ContainerSecretBuilder containerSecret() {
		return BarbicanContainerSecret.builder();
	}

	/**
	 * The Tacker builders
	 * @return the tacker builders
	 */
	public static NfvBuilders tacker() {
		return new TackerBuilders();
	}

	/**
	 * Images V2 builder
	 * @return the glance v2 image builder
	 */
	public static com.huawei.openstack4j.model.image.v2.builder.ImageBuilder imageV2() {
		return com.huawei.openstack4j.openstack.image.v2.domain.GlanceImage.builder();
	}

	/**
	 * Image V2 task builder
	 * @return the glance v2 task builder
	 */
	public static TaskBuilder taskBuilder() {
		return GlanceTask.builder();
	}

	/**
	 * Image V2 json patch update builder
	 * @return the image patch update builder
	 */
	public static ImageUpdateBuilder imageUpdateV2() {
		return GlanceImageUpdate.builder();
	}

	/**
	 * The Murano builders
	 *
	 * @return the murano builders
	 */
	public static AppCatalogBuilders murano() {
		return new MuranoBuilders();
	}

	/**
	 * The builder to create a murano environment
	 *
	 * @return the environment builder
	 */
	public static EnvironmentBuilder environment() {
		return MuranoEnvironment.builder();
	}

	/**
	 * The DNS/Designate V2 builders
	 *
	 * @return the dns/designate v2 builders
	 */
	public static DNSV2Builders dnsV2() {
		return new DesignateV2Builders();
	}

	/**
	 * The builder to create a Zone.
	 *
	 * @return the zone builder
	 */
	public static ZoneBuilder zone() {
		return DesignateZone.builder();
	}

	/**
	 * The builder to create a Recordset.
	 *
	 * @return the recordset builder
	 */
	public static RecordsetBuilder recordset() {
		return DesignateRecordset.builder();
	}

	public static WorkflowBuilders workflow() {
		return new MistralBuilders();
	}

	/**
	 * The builder to create a PTR build
	 * @return
	 */
	public static DesignatePTRBuilder ptr() {
		return DesignatePTR.builder();
	}
	
	/**
	 * The builder to create a PTR build
	 * @return
	 */
	public static VBSVolumeBackupCreateBuilder asyncVolumeBackupCreate() {
		return VBSVolumeBackupCreate.builder();
	}

	
	
}
