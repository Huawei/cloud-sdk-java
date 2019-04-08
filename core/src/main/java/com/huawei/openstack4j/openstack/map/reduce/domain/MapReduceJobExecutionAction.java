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
package com.huawei.openstack4j.openstack.map.reduce.domain;

import java.util.Date;

import com.google.common.base.MoreObjects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.map.reduce.JobExecutionAction;

/**
 * Model represent attributes of JobExecutionAction
 *
 * @author ekasit.kijsipongse@nectec.or.th
 * @author siwat.pru@outlook.com
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class MapReduceJobExecutionAction implements JobExecutionAction {

    private static final long serialVersionUID = 1L;

    @JsonProperty("status")
    private String status;
    @JsonProperty("retries")
    private int retries;
    @JsonProperty("transition")
    private String transition;
    @JsonProperty("stats")
    private String stats;
    @JsonProperty("startTime")
    @JsonFormat(pattern = "EEE, dd MMM yyyy HH:mm:ss 'GMT'", locale = "en")
    private Date startTime;
    @JsonProperty("cred")
    private String cred;
    @JsonProperty("errorMessage")
    private String errorMessage;
    @JsonProperty("externalId")
    private String externalId;
    @JsonProperty("errorCode")
    private String errorCode;
    @JsonProperty("consoleUrl")
    private String consoleUrl;
    @JsonProperty("toString")
    private String toString;
    @JsonProperty("externalStatus")
    private String externalStatus;
    @JsonProperty("conf")
    private String conf;
    @JsonProperty("type")
    private String type;
    @JsonProperty("trackerUri")
    private String trackerUri;
    @JsonProperty("externalChildIDs")
    private String externalChildIds;
    @JsonProperty("endTime")
    @JsonFormat(pattern = "EEE, dd MMM yyyy HH:mm:ss 'GMT'", locale = "en")
    private Date endTime;
    @JsonProperty("data")
    private String data;
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public int getRetries() {
        return retries;
    }

    @Override
    public String getTransition() {
        return transition;
    }

    @Override
    public String getStats() {
        return stats;
    }

    @Override
    public Date getStartTime() {
        return startTime;
    }

    @Override
    public Date getEndTime() {
        return endTime;
    }

    @Override
    public String getCred() {
        return cred;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String getExternalId() {
        return externalId;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getConsoleUrl() {
        return consoleUrl;
    }

    @Override
    public String getToString() {
        return toString;
    }

    @Override
    public String getExternalStatus() {
        return externalStatus;
    }

    @Override
    public String getConf() {
        return conf;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getTrackerUri() {
        return trackerUri;
    }

    @Override
    public String getExternalChildIds() {
        return externalChildIds;
    }

    @Override
    public String getData() {
        return data;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                   .add("status", status)
                   .add("retries", retries)
                   .add("transition", transition)
                   .add("stats", stats)
                   .add("startTime", startTime)
                   .add("cred", cred)
                   .add("errorMessage", errorMessage)
                   .add("externalId", externalId)
                   .add("errorCode", errorCode)
                   .add("consoleUrl", consoleUrl)
                   .add("toString", toString)
                   .add("externalStatus", externalStatus)
                   .add("conf", conf)
                   .add("type", type)
                   .add("trackerUri", trackerUri)
                   .add("externalChildIDs", externalChildIds)
                   .add("endTime", endTime)
                   .add("data", data)
                   .add("id", id)
                   .add("name", name)
                   .toString();
    }
}
