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
package com.huawei.openstack4j.openstack.identity.functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Function;

/**
 * Functions to help resolve specific Services and Versions
 * 
 * @author Jeremy Unruh
 */
public final class ServiceFunctions {

    private static final Pattern VERSION_PATTERN = Pattern.compile("(.*)v(\\d+)");
    
    /**
     * Takes a Service Type or Service Name and strips off any version (if applicable) and returns the 
     * common name.  For example: {@code nova21} would be returned as {@code nova}.
     */
    public static final Function<String, String> TYPE_WITHOUT_VERSION = new Function<String, String>() {
        @Override
        public String apply(String serviceType) {
            return matchForVersion(serviceType, false);
        }
    };

    /**
     * Determines the version from a Service Type or Name.  If the current service does not match the pattern
     * then a version 1 is always returned.  For example: {@code nova21} would return {@code 21}, {@code compute} would
     * return {@code 1}
     */
    public static final Function<String, Integer> VERSION_FROM_TYPE = new Function<String, Integer>() {

        @Override
        public Integer apply(String serviceType) {
            return matchForVersion(serviceType, true);
        }
    };
    
    @SuppressWarnings("unchecked")
    private static <T> T matchForVersion(String service, boolean returnVersion) {
        Matcher m = VERSION_PATTERN.matcher(service);
        if (m.matches()) {
            if (returnVersion) {
                return (T) Integer.valueOf(m.group(2));
            }
            return (T) m.group(1);
        }
        if (returnVersion) {
            return (T) Integer.valueOf(1);
        }
        return (T) service;
    }
    
}
