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
package com.huawei.openstack4j.functional.key.management;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.openstack.common.Quota;
import com.huawei.openstack4j.openstack.common.Quota.ResourceType;
import com.huawei.openstack4j.openstack.kms.constants.KeyState;
import com.huawei.openstack4j.openstack.kms.domain.Key;
import com.huawei.openstack4j.openstack.kms.domain.Key.Keys;
import com.huawei.openstack4j.openstack.kms.domain.KeyCreate;
import com.huawei.openstack4j.openstack.kms.options.KeyListOptions;

/**
 *
 * @author Super Stone
 * @date 2018-07-13 14:43:01
 */
@Test(suiteName = "KeyManagement/Key/Sample")
public class KmsTest extends AbstractTest {

	String name = randomName();
	String keyId = null;
	boolean deleteKey = false;

	/**
	 * create a key for test
	 */
	@BeforeClass
	public void prepare() {
		List<Key> keys = osclient.keyManagement().keys().list(null).value();
		if (keys != null && keys.size() > 0) {
			for (Key key : keys) {
				if (key.getAlias().startsWith("SDK-")) {
					this.keyId = key.getId();
					break;
				}
			}
		}
		if (null == keyId) {
			KeyCreate create = KeyCreate.builder().alias(name).description("desc").build();
			keyId = osclient.keyManagement().keys().create(create).getId();
		}
	}

	/**
	 * after all, we submit a schedule deletion to delete the key in future
	 */
	@AfterClass
	public void cleanup() {
		if (deleteKey) {
			osclient.keyManagement().keys().scheduleDeletion(keyId, 7, null);
		}
	}

	@Test(priority = 1)
	public void testGetKey() {
		Key get = osclient.keyManagement().keys().get(keyId, null);
		Assert.assertEquals(get.getAlias(), name);
		Assert.assertEquals(get.getDescription(), "desc");
	}

	@Test(priority = 2)
	public void testScheduleDeletion() {
		Key deleted = osclient.keyManagement().keys().scheduleDeletion(keyId, 7, null);
		Assert.assertEquals(deleted.getState(), KeyState.ScheduledDeletion);
	}

	@Test(priority = 3)
	public void testCancelDeletion() {
		Key canceled = osclient.keyManagement().keys().cancelDeletion(keyId, null);
		Assert.assertEquals(canceled.getState(), KeyState.Disabled);
	}

	@Test(priority = 4)
	public void testEnableKey() {
		Key enabled = osclient.keyManagement().keys().enable(keyId, null);
		Assert.assertEquals(enabled.getState(), KeyState.Enabled);
	}

	@Test(priority = 5)
	public void testDisableKey() {
		Key disabled = osclient.keyManagement().keys().disable(keyId, null);
		Assert.assertEquals(disabled.getState(), KeyState.Disabled);
	}

	@Test(priority = 6)
	public void testListKeys() {
		KeyListOptions options = KeyListOptions.create().limit(20).keyState(KeyState.Enabled);
		Keys keys = osclient.keyManagement().keys().list(options);

		boolean contains = keys.get().contains(keyId);
		while (!contains && keys.getTruncated()) {
			options.marker(keys.getNextMarker());
			keys = osclient.keyManagement().keys().list(options);
			contains = keys.get().contains(keyId);
		}

		Assert.assertTrue(contains);
	}

	@Test(priority = 7)
	public void testGetAmount() {
		Integer keyCreatedAmount = osclient.keyManagement().keys().getKeyCreatedAmount();
		Assert.assertTrue(keyCreatedAmount > 0);
	}

	@Test(priority = 8)
	public void testListQuota() {
		List<Quota> quotas = osclient.keyManagement().keys().quotas();
		Assert.assertTrue(quotas.size() > 0);
		boolean found = false;
		for (Quota quota : quotas) {
			if (quota.getType().equals(ResourceType.CMK)) {
				Assert.assertTrue(quota.getUsed() > 0);
				found = true;
			}
		}

		Assert.assertTrue(found);
	}

}
