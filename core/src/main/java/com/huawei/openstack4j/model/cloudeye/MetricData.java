/*******************************************************************************
 * 	Copyright 2017 HuaWei Tld
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
package com.huawei.openstack4j.model.cloudeye;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.huawei.openstack4j.model.ModelEntity;

public interface MetricData extends ModelEntity {
	
	public enum ValueType {
		
		Integer("int"), Float("float");

		String value;

		ValueType(String value) {
			this.value = value;
		}
		
		@JsonValue
		String value() {
			return value;
		}

		@JsonCreator
		public static ValueType forValue(String value) {
			if (value != null) {
				for (ValueType state : ValueType.values()) {
					if (value.equals(state.value)) {
						return state;
					} 
				}
			}
			return null;
		}
	}
	
	
    Metric getMetric();
    Number getTtl();
    Date getCollectTime();
    Number getValue();
    ValueType getType();
}
