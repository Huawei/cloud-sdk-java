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
package com.huawei.openstack4j.api.compute;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.compute.ext.Migration;
import com.huawei.openstack4j.model.compute.ext.Migration.Status;

/**
 * Migrations based Test Cases
 * 
 * @author Jeremy Unruh
 */
@Test(suiteName="Migrations", enabled=true)
public class MigrationTests extends AbstractTest {

    private static final String JSON_MIGRATIONS = "/compute/migrations.json";

   /* public void listMigrations() throws Exception {
        respondWith(JSON_MIGRATIONS);
        List<? extends Migration> migrations = osv3().compute().migrations().list();
        
        assertEquals(2, migrations.size());
        assertEquals(Status.DONE, migrations.get(0).getStatus());
    }*/
    
    @Override
    protected Service service() {
        return Service.COMPUTE;
    }

}
