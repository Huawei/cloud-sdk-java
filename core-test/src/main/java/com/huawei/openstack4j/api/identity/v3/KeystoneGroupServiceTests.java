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
package com.huawei.openstack4j.api.identity.v3;

import static org.testng.AssertJUnit.assertNull;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.model.identity.v3.Group;

/**
 * Tests the Identity/Keystone API version 3 GroupService
 */
@Test(groups = "idV3", suiteName = "Identity/Keystone_V3")
public class KeystoneGroupServiceTests extends AbstractTest {

    private static final String JSON_GROUPS_CREATE = "/identity/v3/groups_create_response.json";
    private static final String JSON_GROUPS_GET_BYID = "/identity/v3/groups_get_byId.json";
    private static final String JSON_GROUPS_UPDATE = "/identity/v3/groups_update_response.json";
    private static final String JSON_GROUPS_EMPTY_LIST = "/identity/v3/groups_getByName_empty.json";
    private static final String GROUP_NAME = "GROUP_CRUD";
    private static final String GROUP_DOMAIN_ID = "default";
    private static final String GROUP_DESCRIPTION = "Group used for CRUD tests";
    private static final String GROUP_DESCRIPTION_UPDATE = "An updated group used for CRUD tests";

    @Override
    protected Service service() {
        return Service.IDENTITY;
    }

    // ------------ Group Tests ------------

    // The following tests are to verify the update() method of the GroupService
    // using HTTP PATCH, which is not supported by betamax.
    // Find more tests in KeystoneGroupServiceSpec in core-integration-test
    // module.
/*
    public void group_crud_test() throws Exception {

        Group group = Builders.group()
                .name(GROUP_NAME)
                .description(GROUP_DESCRIPTION)
                .domainId(GROUP_DOMAIN_ID)
                .build();

        respondWith(JSON_GROUPS_CREATE);

        Group newGroup = osv3().identity().groups().create(group);

        assertEquals(newGroup.getName(), GROUP_NAME);
        assertEquals(newGroup.getDomainId(), GROUP_DOMAIN_ID);
        assertEquals(newGroup.getDescription(), GROUP_DESCRIPTION);

        String GROUP_ID = newGroup.getId();

        respondWith(JSON_GROUPS_GET_BYID);

        Group group_setToUpdate = osv3().identity().groups().get(GROUP_ID);

        respondWith(JSON_GROUPS_UPDATE);

        Group updatedGroup = osv3.identity().groups().update(group_setToUpdate.toBuilder().description(GROUP_DESCRIPTION_UPDATE).build());

        assertEquals(updatedGroup.getId(), GROUP_ID);
        assertEquals(updatedGroup.getName(), GROUP_NAME);
        assertEquals(updatedGroup.getDomainId(), GROUP_DOMAIN_ID);
        assertEquals(updatedGroup.getDescription(), GROUP_DESCRIPTION_UPDATE);

    }
    
    
    public void group_get_byName_byDomainId_NotExist_Test() throws Exception {    	
    	respondWith(JSON_GROUPS_EMPTY_LIST);    
    	Group group = osv3().identity().groups().getByName(GROUP_NAME, GROUP_DOMAIN_ID);    	
    	assertNull(group);
    	
    }
*/
}
