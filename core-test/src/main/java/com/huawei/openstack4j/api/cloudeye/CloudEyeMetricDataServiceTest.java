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
package com.huawei.openstack4j.api.cloudeye;

import org.testng.annotations.ExpectedExceptions;
import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.cloudeye.Filter;
import com.huawei.openstack4j.model.cloudeye.MetricAggregation;
import com.huawei.openstack4j.model.cloudeye.Period;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.cloudeye.domain.CloudEyeMetric;
import com.huawei.openstack4j.openstack.cloudeye.domain.CloudEyeMetricData;
import com.huawei.openstack4j.openstack.cloudeye.domain.CloudEyeMetricDemension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Tests the CloudEye API version Metric Service
 */
@Test(groups = "Cloudeye", suiteName = "Cloudeye/MetricDatas")
public class CloudEyeMetricDataServiceTest extends AbstractTest {
    private static final String JSON_METRIC_AGGREGATION = "/cloudeye/get_metric_aggregation.json";
    private static final String JSON_METRIC_AGGREGATION_ERROR = "/cloudeye/get_metric_aggregation_error.json";

    @Override
    protected Service service() {
        return Service.CLOUD_EYE;
    }

    public void getMetricAggregationDataTest() throws Exception {
        respondWith(JSON_METRIC_AGGREGATION);

        MetricAggregation metricAggregation = osv3().cloudEye().metricsDatas().get("SYS.ECS", "network_incoming_bytes_aggregate_rate",
                new Date(1498321875058l), new Date(), Period.FIVE_MINS, Filter.AVERAGE, new String[]{"instance_id,33328f02-3814-422e-b688-bfdba93d4050"});
        assertNotNull(metricAggregation);
        assertNotNull(metricAggregation.getDatapoints());
        assertEquals(metricAggregation.getDatapoints().size(), 1);
        assertEquals(metricAggregation.getMetricName(), "cpu_util");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void getMetricAggregationDataFailed1Test() throws Exception {
       osv3().cloudEye().metricsDatas().get("SYS.ECS", "network_incoming_bytes_aggregate_rate",
                new Date(1498321875058l), new Date(), Period.FIVE_MINS, null, new String[]{"instance_id,33328f02-3814-422e-b688-bfdba93d4050"});
    }

    public void getMetricAggregationDataFailed2Test() throws Exception {
        respondWith(JSON_METRIC_AGGREGATION_ERROR);
        //From & To are same
        MetricAggregation metricAggregation = osv3().cloudEye().metricsDatas().get("SYS.ECS", "network_incoming_bytes_aggregate_rate",
                new Date(1498321875058l), new Date(1498321875058l), Period.FIVE_MINS, Filter.AVERAGE, new String[]{"instance_id,33328f02-3814-422e-b688-bfdba93d4050"});
        assertNotNull(metricAggregation);
        assertNull(metricAggregation.getDatapoints());
    }

    public void addMetricDataTest() throws Exception {
        respondWith(201);
        List<CloudEyeMetricData> metrics = new ArrayList<>();
        CloudEyeMetricDemension.CloudEyeMetricDemensionBuilder dimBuilder = CloudEyeMetricDemension.builder().name("instance_id" +
                "").value("33328f02-3814-422e-b688-bfdba93d4050");
        CloudEyeMetricDemension dim1 = dimBuilder.build();
        List<CloudEyeMetricDemension> dimList = new ArrayList<>();
        dimList.add(dim1);

        CloudEyeMetric.CloudEyeMetricBuilder metricBuilder = CloudEyeMetric.builder().namespace("MINE.APP")
                .metricName("test_add_metric_data_1")
                .dimensions(dimList);
        CloudEyeMetricData.CloudEyeMetricDataBuilder builder1 = CloudEyeMetricData.builder()
                .metric(metricBuilder.build())
                .ttl(172800)
                .collectTime(new Date())
                .value(60)
                .unit("%");

        CloudEyeMetric.CloudEyeMetricBuilder metricBuilder2 = CloudEyeMetric.builder().namespace("MINE.APP")
                .metricName("cpu_util")
                .dimensions(dimList);
        CloudEyeMetricData.CloudEyeMetricDataBuilder builder2 = CloudEyeMetricData.builder()
                .metric(metricBuilder2.build())
                .ttl(172800)
                .collectTime(new Date())
                .value(70)
                .unit("%");
        metrics.add(builder1.build());
        metrics.add(builder2.build());

        ActionResponse actionResponse = osv3().cloudEye().metricsDatas().add(metrics);
        assertTrue(actionResponse.isSuccess());
    }

}
