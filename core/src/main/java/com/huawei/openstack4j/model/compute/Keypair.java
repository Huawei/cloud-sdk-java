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
package com.huawei.openstack4j.model.compute;

import java.util.Date;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * An OpenStack Keypair is an SSH Key
 * 
 * @author Jeremy Unruh,whaon
 */
public interface Keypair extends ModelEntity {
	
	/**
	 * The name associated with the keypair
	 *
	 * @return the name of the keypair
	 */
	String getName();
	
	/**
	 * The public SSH key
	 *
	 * @return the public key
	 */
	String getPublicKey();
	
	/**
	 * The private key associated with this keypair.  Only populated on create when a public key is not specified and is auto-generated
	 * by the server
	 * 
	 * @return the private key
	 */
	String getPrivateKey();
	
	/**
	 * @return the server fingerprint
	 */
	String getFingerprint();
	
	/**
	 * 
	 * @return the user_id for a keypair.
	 */
	String getUserId();
	
	/**
	 * 
	 * @return is deleted
	 */
	Boolean getDeleted();
	
	/**
	 * time created
	 * @return
	 */
	Date getCreatedAt();
	
	/**
	 * time updated
	 * @return
	 */
	Date getUpdatedAt();
	
	/**
	 * time deleted
	 * @return
	 */
	Date getDeletedAt();
	
	/**
	 * 
	 * @return id of the keypair
	 */
	Integer getId();

}
