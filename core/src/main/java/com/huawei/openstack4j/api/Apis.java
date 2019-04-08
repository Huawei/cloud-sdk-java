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

import java.util.ServiceLoader;

import com.huawei.openstack4j.api.artifact.ArtifactService;
import com.huawei.openstack4j.api.barbican.BarbicanService;
import com.huawei.openstack4j.api.cloudeye.CloudEyeService;
import com.huawei.openstack4j.api.compute.ComputeService;
import com.huawei.openstack4j.api.deh.DehService;
import com.huawei.openstack4j.api.dns.v2.DNSService;
import com.huawei.openstack4j.api.gbp.GbpService;
import com.huawei.openstack4j.api.heat.HeatService;
import com.huawei.openstack4j.api.image.ImageService;
import com.huawei.openstack4j.api.magnum.MagnumService;
import com.huawei.openstack4j.api.manila.ShareService;
import com.huawei.openstack4j.api.map.reduce.MapReduceService;
import com.huawei.openstack4j.api.murano.v1.AppCatalogService;
import com.huawei.openstack4j.api.nat.NatService;
import com.huawei.openstack4j.api.networking.NetworkingService;
import com.huawei.openstack4j.api.senlin.SenlinService;
import com.huawei.openstack4j.api.tacker.TackerService;
import com.huawei.openstack4j.api.workflow.WorkflowService;
import com.huawei.openstack4j.openstack.ecs.v1.internal.ElasticComputeService;
import com.huawei.openstack4j.openstack.fgs.v1.internal.FunctionGraphService;
import com.huawei.openstack4j.openstack.trove.internal.TroveService;

/**
 * Provides access to the Major APIs and Buildables
 *
 * @author Jeremy Unruh
 */
public class Apis {

	private static final APIProvider provider = initializeProvider();

	/**
	 * Gets the API implementation based on Type
	 *
	 * @param <T>
	 *            the API type
	 * @param api
	 *            the API implementation
	 * @return the API implementation
	 */
	public static <T> T get(Class<T> api) {
		return provider.get(api);
	}

	/**
	 * Gets the identity v3 services API
	 *
	 * @return the identity v3 services
	 */
	public static com.huawei.openstack4j.api.identity.v3.IdentityService getIdentityV3Services() {
		return get(com.huawei.openstack4j.api.identity.v3.IdentityService.class);
	}

	/**
	 * Gets the identity v2 services API
	 *
	 * @return the identity v2 services
	 */
	public static com.huawei.openstack4j.api.identity.v2.IdentityService getIdentityV2Services() {
		return get(com.huawei.openstack4j.api.identity.v2.IdentityService.class);
	}

	/**
	 * Gets the compute services API
	 *
	 * @return the compute services
	 */
	public static ComputeService getComputeServices() {
		return get(ComputeService.class);
	}

	/**
	 * Gets the Network services API
	 *
	 * @return the network services
	 */
	public static NetworkingService getNetworkingServices() {
		return get(NetworkingService.class);
	}

	/**
	 * Gets the Artifact services API
	 *
	 * @return the artifact services
	 */
	public static ArtifactService getArtifactServices() {
		return get(ArtifactService.class);
	}

	/**
	 * Gets the Tacker services API
	 *
	 * @return the tacker services
	 */
	public static TackerService getTackerServices() {
		return get(TackerService.class);
	}

	/**
	 * Gets the (Glance) Image services API
	 *
	 * @return the image services
	 */
	public static ImageService getImageService() {
		return get(ImageService.class);
	}

	/**
	 * Gets the (Glance) Image v2 services API
	 * @return the image v2 services
	 */
	public static com.huawei.openstack4j.api.image.v2.ImageService getImageV2Service() {
		return get(com.huawei.openstack4j.api.image.v2.ImageService.class);
	}

	/**
	 * Gets the (Heat) Orchestration services API
	 * 
	 * @return the heat services
	 */
	public static HeatService getHeatServices() {
		return get(HeatService.class);
	}

	/**
	 * Gets the (Murano) App Catalog services API
	 *
	 * @return the murano services
	 */
	public static AppCatalogService getMuranoServices() {
		return get(AppCatalogService.class);
	}

	/**
	 * Gets the FunctionGraph Service API
	 *
	 * @return the FunctionGraph services
	 */
	public static FunctionGraphService getFunctionGraphServices() {
		return get(FunctionGraphService.class);
	}

	/**
	 * Gets the (MapReduce) Data Processing services API
	 * 
	 * @return the MapReduce services
	 */
	public static MapReduceService getMapReduceServices() {
		return get(MapReduceService.class);
	}

	/**
	 * Gets the (Mistral) Workflow services API
	 *
	 * @return the workflow services
	 */
	public static WorkflowService getWorkflowServices() {
		return get(WorkflowService.class);
	}

	/**
	 * Gets the (Manila) Shared File Systems services API
	 * 
	 * @return the share services
	 */
	public static ShareService getShareServices() {
		return get(ShareService.class);
	}

	/**
	 * Gets the group based policy services API
	 * @return the gbp services 
	 */
	public static GbpService getGbpServices() {
		return get(GbpService.class);
	}
	
	/**
	 * Gets the trove services API
	 * @return the trove services
	 */
	public static TroveService getTroveServices() {
		return get(TroveService.class);
	}

	/**
	 * Gets the (Senlin) Orchestration services API
	 * @return the Senlin services
	 */
	public static SenlinService getSenlinServices() {
		return get(SenlinService.class);
	}

	/**
	 * Gets the Magnum services API
	 *
	 * @return the Magnum Service
	 */
	public static MagnumService getMagnumService() {
		return get(MagnumService.class);
	}

	/**
	 * Gets the (BarbicanService) Orchestration services API
	 * @return the BarbicanService services
	 */
	public static BarbicanService getBarbicanServices() {
		return get(BarbicanService.class);
	}

	/**
	 * Gets the dns services API
	 * @return the dns services
	 */
	public static DNSService getDNSService() {
		return get(DNSService.class);
	}

	/**
	 * Gets the Cloud Eye services API
	 * @return the Cloud Eye services
	 */
	public static CloudEyeService getCloudEyeService() {
		return get(CloudEyeService.class);
	}
	
	/**
	 * Gets the Elastic Compute services API
	 * @return the Elastic Compute services
	 */
 	public static ElasticComputeService getElasticComputeService() {
 		return get(ElasticComputeService.class);
 	}
 	/**
	 * Gets the nat services API
	 * @return the nat services 
	 */
	public static NatService getNatServices() {
		return get(NatService.class);
	}
	
	/**
	 * Gets the deh services API
	 * @return the DedicatedHost services 
	 */
	public static DehService getDehService() {
		return get(DehService.class);
	}
	
	private static APIProvider initializeProvider() {
		// No need to check for emptiness as there is default implementation registered
		APIProvider p = ServiceLoader.load(APIProvider.class, Apis.class.getClassLoader()).iterator().next();
		p.initialize();
		return p;
	}
}
