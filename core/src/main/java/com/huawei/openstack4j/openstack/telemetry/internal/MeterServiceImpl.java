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
/******************************************************************************* 	                                                                                 
 *  Huawei has modified this source file.
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
 * *******************************************************************************/
package com.huawei.openstack4j.openstack.telemetry.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.huawei.openstack4j.api.telemetry.MeterService;
import com.huawei.openstack4j.model.telemetry.Meter;
import com.huawei.openstack4j.model.telemetry.MeterSample;
import com.huawei.openstack4j.model.telemetry.SampleCriteria;
import com.huawei.openstack4j.model.telemetry.Statistics;
import com.huawei.openstack4j.model.telemetry.SampleCriteria.NameOpValue;
import com.huawei.openstack4j.openstack.common.ListEntity;
import com.huawei.openstack4j.openstack.telemetry.domain.CeilometerMeter;
import com.huawei.openstack4j.openstack.telemetry.domain.CeilometerMeterSample;
import com.huawei.openstack4j.openstack.telemetry.domain.CeilometerStatistics;

/**
 * Provides Measurements against Meters within an OpenStack deployment
 * 
 * @author Jeremy Unruh
 */
public class MeterServiceImpl extends BaseTelemetryServices implements MeterService {

    private static final String FIELD = "q.field";
    private static final String OPER = "q.op";
    private static final String VALUE = "q.value";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Meter> list() {
        CeilometerMeter[] meters = get(CeilometerMeter[].class, uri("/meters")).execute();
        return wrapList(meters);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends MeterSample> samples(String meterName) {
        checkNotNull(meterName);

        CeilometerMeterSample[] samples = get(CeilometerMeterSample[].class, uri("/meters/%s", meterName)).execute();
        return wrapList(samples);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends MeterSample> samples(String meterName, SampleCriteria criteria) {
        checkNotNull(meterName);

        Invocation<CeilometerMeterSample[]> invocation = get(CeilometerMeterSample[].class, uri("/meters/%s", meterName));
        if (criteria != null && !criteria.getCriteriaParams().isEmpty()) {
            for (NameOpValue c : criteria.getCriteriaParams()) {
                invocation.param(FIELD, c.getField());
                invocation.param(OPER, c.getOperator().getQueryValue());
                invocation.param(VALUE, c.getValue());
            }
        }

        CeilometerMeterSample[] samples = invocation.execute();
        return wrapList(samples);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Statistics> statistics(String meterName) {
        return statistics(meterName, null, 0);
    }

    @Override
    public List<? extends Statistics> statistics(String meterName, int period) {
        return statistics(meterName, null, period);
    }

    @Override
    public List<? extends Statistics> statistics(String meterName, SampleCriteria criteria) {
        return statistics(meterName, criteria, 0);
    }

    @Override
    public List<? extends Statistics> statistics(String meterName, SampleCriteria criteria, int period) {
        checkNotNull(meterName);
        Invocation<CeilometerStatistics[]> invocation = get(CeilometerStatistics[].class, uri("/meters/%s/statistics", meterName))
                                                           .param(period > 0, "period", period);


        if (criteria != null && !criteria.getCriteriaParams().isEmpty()) {
            for (NameOpValue c : criteria.getCriteriaParams()) {
                invocation.param(FIELD, c.getField());
                invocation.param(OPER, c.getOperator().getQueryValue());
                invocation.param(VALUE, c.getValue());
            }
        }

        CeilometerStatistics[] stats = invocation.execute();
        return wrapList(stats);
    }

    @Override
    public void putSamples(List<MeterSample> sampleList, String meterName) {
        ListEntity<MeterSample> listEntity= new ListEntity<MeterSample>(sampleList);
        post(Void.class,uri("/meters/%s",meterName)).entity(listEntity).execute();
    }
}
