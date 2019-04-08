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
package com.huawei.openstack4j.openstack.storage.block.domain;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.storage.block.VolumeType;
import com.huawei.openstack4j.model.storage.block.builder.VolumeTypeBuilder;
import com.huawei.openstack4j.openstack.common.ListResult;

import com.google.common.base.MoreObjects;

/**
 * The volume type defines the characteristics of a volume. It usually maps to a set of capabilities
 * of the storage back-end driver to be used for this volume.
 * Examples: "Performance", "SSD", "Backup", etc.
 *
 * @author Jeremy Unruh
 */
@JsonRootName("volume_type")
public class CinderVolumeType implements VolumeType {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String description;
	@JsonProperty("qos_specs_id")
	private  String qosSpecsId;
	@JsonProperty("is_public")
	private Boolean isPublic;

	@JsonProperty("extra_specs")
	private Map<String, String> extraSpecs;

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
	public String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, String> getExtraSpecs() {
		return extraSpecs;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDescription()
	{
		return description;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getQosSpecsId()
	{
		return qosSpecsId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean isPublic()
	{
		return isPublic;
	}

	@Override
    public VolumeTypeBuilder toBuilder() {
        return new ConcreteVolumeTypeBuilder(this);
    }

    /**
     * @return the Volume Type Builder
     */
    public static VolumeTypeBuilder builder() {
        return new ConcreteVolumeTypeBuilder();
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
				     .add("id", id).add("name", name).add("extras", extraSpecs)
					 .add("description",description).add("isPublic",isPublic).add("qosSpecsId",qosSpecsId)
				     .toString();
	}

	public static class VolumeTypes extends ListResult<CinderVolumeType> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("volume_types")
		private List<CinderVolumeType> types;

		@Override
		protected List<CinderVolumeType> value() {
			return types;
		}

	}

    public static class ConcreteVolumeTypeBuilder implements VolumeTypeBuilder {

        private CinderVolumeType m;

        ConcreteVolumeTypeBuilder() {
            this(new CinderVolumeType());
        }

        ConcreteVolumeTypeBuilder(CinderVolumeType volumeType) {
            this.m = volumeType;
        }

        @Override
        public VolumeType build() {
            return m;
        }

        @Override
        public VolumeTypeBuilder from(VolumeType in) {
            m = (CinderVolumeType) in;
            return this;
        }

        @Override
        public VolumeTypeBuilder name(String name) {
            m.name = name;
            return this;
        }

        @Override
        public VolumeTypeBuilder extraSpecs(Map<String, String> extraSpecs) {
            m.extraSpecs = extraSpecs;
            return this;
        }
    }

}
