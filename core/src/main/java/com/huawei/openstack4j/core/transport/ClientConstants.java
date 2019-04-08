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
package com.huawei.openstack4j.core.transport;

/**
 * Common String Constants
 *
 * @author Jeremy Unruh
 */
public final class ClientConstants {

    public static final String HEADER_X_AUTH_TOKEN = "X-Auth-Token";
    public static final String HEADER_X_SUBJECT_TOKEN = "X-Subject-Token";
    public static final String HEADER_X_PROJECT_ID = "X-Project-Id";
    public static final String HEADER_X_PROJECT_NAME = "X-Project-Name";
    public static final String HEADER_X_PROJECT_DOMAIN_ID = "X-Project-Domain-Id";
    public static final String HEADER_X_PROJECT_DOMAIN_NAME = "X-Project-Domain-Name";
    public static final String HEADER_X_DOMAIN_ID = "X-Domain-Id";
    public static final String HEADER_X_DOMAIN_NAME = "X-Domain-Name";
    public static final String HEADER_CONTENT_LANGUAGE = "Content-Language";
    public static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_OS4J_AUTH = "OS4J-Auth-Command";
    public static final String HEADER_ACCEPT = "Accept";
    public static final String HEADER_USER_AGENT = "User-Agent";
    public static final String USER_AGENT = "OpenStack4j / OpenStack Client";

    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_STREAM = "application/stream";
    public static final String CONTENT_TYPE_DIRECTORY = "application/directory";
    public static final String CONTENT_TYPE_OCTECT_STREAM = "application/octet-stream";
    public static final String CONTENT_TYPE_TEXT = "text/plain";
    public static final String CONTENT_TYPE_TEXT_HTML = "text/html";
    public static final String CONTENT_TYPE_IMAGE_V2_PATCH = "application/openstack-images-v2.1-json-patch";
    public static final String CONTENT_TYPE_ARTIFACT_PATCH = "application/json-patch+json";
    
    public static final String X_OPENSTACK_REQUEST_ID = "x-openstack-request-id";
    public static final String X_COMPUTE_REQUEST_ID = "X-Compute-Request-Id";


    // Paths
    public static final String URI_SEP = "/";
    public static final String PATH_PROJECTS = "/projects";
    public static final String PATH_ROLES = "/roles";
    public static final String PATH_USERS = "/users";
    public static final String PATH_SERVICES = "/services";
    public static final String PATH_DOMAINS = "/domains";
    public static final String PATH_ENDPOINTS = "/endpoints";
    public static final String PATH_EXTENSIONS = "/extensions";
    public static final String PATH_GROUPS = "/groups";
    public static final String PATH_POLICIES = "/policies";
    public static final String PATH_REGIONS = "/regions";
    public static final String PATH_CREDENTIALS = "/credentials";
    public static final String PATH_TOKENS = "/auth/tokens";
    public static final String PATH_PROJECT_SCOPES = "auth/projects";
    public static final String PATH_DOMAIN_SCOPES = "auth/domains";
    public static final String PATH_SERVICE_CATALOGS = "auth/catalog";
    public static final String PATH_TENANTS = "/tenants";
    public static final String PATH_ARTIFACTS = "/artifacts";

    // DNS/Designate
    public static final String PATH_ZONES = "/zones";
    public static final String PATH_RECORDSETS = "/recordsets";
    public static final String PATH_NAMESERVERS = "/nameservers";
    public static final String PATH_PTR = "/reverse/floatingips";
    public static final String PATH_ASSOCIATE = "/associaterouter";
    public static final String PATH_DISASSOCIATE = "/disassociaterouter";

    //CloudEye/Metrics
    public static final String PATH_METRICS = "/metrics";
    public static final String PATH_FAVORITE_METRICS = "/favorite-metrics";

    //tagmanagement
    public static final String PATH_PREDEFINE_TAG = "/predefine_tags";
    public static final String PATH_PREDEFINE_TAG_ACTION = "/action";
    
    //CloudEye/Alarm
    public static final String PATH_ALARMS = "/alarms";
    public static final String PATH_ALARMS_ACTION = "/action";

    //CloudEye/Metric Datas
    public static final String PATH_METRIC_DATAS = "/metric-data";

    //CloudEye/Quota
    public static final String PATH_QUOTA = "/quotas";
    
    //VolumeBackup
    public static final String PATH_ASYNC_VOLUME_BACKUP = "/cloudbackups";
    public static final String PATH_VOLUME_BACKUP_POLICY = "/backuppolicy";
    

    //Magnum APIs
    // list all Magnum Services
    public static final String MAGNUM_MSERVICES = "/mservices";
    // list baymodels
    public static final String MAGNUM_BAYMODELS = "/baymodels";
    // bays
    public static final String MAGNUM_BAYS = "/bays";
    public static final String MAGNUM_CONTAINERS = "/containers";
    public static final String MAGNUM_CERTIFICATES = "/certificates";
    public static final String MAGNUM_CLUSTERS = "/clusters";
    public static final String MAGNUM_CLUSTERTEMPLATES = "/clustertemplates";
    public static final String MAGNUM_PODS = "/pods";

    //FunctionGraph path
    public static final String FGS_PATH = "/fss/functions";
    public static final String FGS_CONFIG = "/config";
    public static final String FGS_CODE = "/code";
    public static final String FGS_VERSION = "/versions";
    public static final String FGS_MARKER = "marker";
    public static final String FGS_MAXITEMS = "maxitems";
    public static final String FGS_ALIASES = "/aliases";
    public static final String FGS_TRIGGERS = "/fss/triggers";
    public static final String FGS_INVOCATIONS = "/invocations";
    public static final String FGS_INVOCATIONS_ASYNC = "/invocations-async";

    //FunctionGraph Runtime
    public static final String FGS_RUNGTIME_NODEJS6 = "Node.js6.10";
    public static final String FGS_RUNGTIME_NODEJS8 = "Node.js8.10";
    public static final String FGS_RUNGTIME_PYTHON2 = "Python2.7";
    public static final String FGS_RUNGTIME_PYTHON3 = "Python3.6";
    public static final String FGS_RUNGTIME_JAVA8 = "Java8";
    public static final String FGS_RUNGTIME_GO = "Go1.8";
    public static final String FGS_RUNGTIME_CSHARP2 = "CSharp2.0";
    public static final String FGS_RUNGTIME_CSHARP2_1 = "CSharp2.1";

    //micro version
    public static final String HEADER_X_OPENSTACK_NOVA_API_VERSION= "X-OpenStack-Nova-API-Version";
    public static final String HEADER_OPENSTACK_API_VERSION= "OpenStack-API-Version";
}
