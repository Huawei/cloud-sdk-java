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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import java.util.List;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.cloudeye.Alarm;
import com.huawei.openstack4j.model.cloudeye.AlarmState;
import com.huawei.openstack4j.model.cloudeye.OrderType;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.cloudeye.internal.AlarmFilterOptions;

@Test(groups = "cloudeye", suiteName = "cloudeye/Alarms")
public class CloudEyeAlarmServiceTest extends AbstractTest {
    private static final String JSON_ALARMS = "/cloudeye/list_alarms.json";
    public static final String ALARM_ID = "al1483387711418ZNpR8DX3g";


    @Override
    protected Service service() {
        return Service.CLOUD_EYE;
    }

    public void getAlarmListTest() throws Exception {
        respondWith(JSON_ALARMS);
        List<? extends Alarm> alarms = osv3().cloudEye().alarms().list();
        assertNotNull(alarms);
        assertEquals(alarms.size(), 1);
        assertEquals(alarms.get(0).getAlarmState(), AlarmState.OK);
    }

    public void getAlarmListWithFilterTest() throws Exception {
        respondWith(JSON_ALARMS);
        AlarmFilterOptions config = AlarmFilterOptions.create();
        AlarmFilterOptions options = config.limit(5);
        options.order(OrderType.ASC);

        List<? extends Alarm> alarms = osv3().cloudEye().alarms().list(options);
        assertNotNull(alarms);
        assertEquals(alarms.size(), 1);
        assertEquals(alarms.get(0).getAlarmState(), AlarmState.OK);
    }

    public void getAlarmByIDTest() throws Exception {
        respondWith(JSON_ALARMS);

        Alarm alarms = osv3().cloudEye().alarms().get(ALARM_ID);
        assertNotNull(alarms);
        assertEquals(alarms.getAlarmState(), AlarmState.OK);
    }

    public void startAlarmTest() throws Exception {
        respondWith(204);

        ActionResponse ptrRestoreActionResponse = osv3().cloudEye().alarms().startAlarm(ALARM_ID);
        assertTrue(ptrRestoreActionResponse.isSuccess());
    }
    public void stopAlarmTest() throws Exception {
        respondWith(204);

        ActionResponse ptrRestoreActionResponse = osv3().cloudEye().alarms().stopAlarm(ALARM_ID);
        assertTrue(ptrRestoreActionResponse.isSuccess());
    }
    public void deleteAlarmTest() throws Exception {
        respondWith(204);

        ActionResponse ptrRestoreActionResponse = osv3().cloudEye().alarms().deleteAlarm(ALARM_ID);
        assertTrue(ptrRestoreActionResponse.isSuccess());
    }

}

