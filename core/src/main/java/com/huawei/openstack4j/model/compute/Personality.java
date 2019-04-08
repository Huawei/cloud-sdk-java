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

import com.huawei.openstack4j.model.ModelEntity;

/**
 * A Personality is a file (path to file) and the contents that should be replaced on a new VM Server
 * 
 * @author Jeremy Unruh
 */
public class Personality implements ModelEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String path;
	private String contents;

	public Personality() {
	}

	public Personality(String path, String contents) {
		this.path = path;
		this.contents = contents;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getPath() {
		return path;
	}

	public String getContents() {
		return contents;
	}

}
