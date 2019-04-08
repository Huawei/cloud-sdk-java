/*******************************************************************************
 * 	Copyright 2017 HuaWei TLD and OTC                                          
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
package com.huawei.openstack4j.openstack.maas.internal;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Map;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.maas.constants.Operation;
import com.huawei.openstack4j.openstack.maas.constants.State;
import com.huawei.openstack4j.openstack.maas.domain.Task;
import com.huawei.openstack4j.openstack.maas.domain.TaskCreate;
import com.huawei.openstack4j.openstack.maas.domain.TaskCreateResp;
import com.huawei.openstack4j.openstack.maas.domain.TaskStart;
import com.huawei.openstack4j.openstack.maas.domain.TaskCreate.Node;
import com.huawei.openstack4j.openstack.maas.domain.TaskCreate.SmnInfo;
import com.huawei.openstack4j.openstack.maas.options.TaskListOptions;

public class TaskService extends BaseMaaSService implements RestService {

	/**
	 * create task
	 * @param task {@link TaskCreate}
	 * @return {@link TaskCreateResp}
	 */
	public TaskCreateResp create(TaskCreate task) {
		checkArgument(task != null, "task is required");

		checkArgument(task.getEnableKMS() != null, "enableKMS is required");
		checkArgument(task.getThreadNum() != null, "threadNum is required");
//		checkArgument(task.getEnableTas() != null, "enableTas is required");

		checkNode(task.getSrcNode(), "srcNode");
		checkNode(task.getDstNode(), "dstNode");

		checkSmnInfoWhenPresent(task.getSmnInfo());

		return post(TaskCreateResp.class, uri("/task")).entity(task).execute();
	}

	/**
	 * delete task
	 * @param taskId
	 * @return {@link ActionResponse}
	 */
	public ActionResponse delete(long taskId) {
		return deleteWithResponse(uri("/task/%s", taskId)).execute();
	}

	/**
	 * start task
	 * @param taskId
	 * @param task {@link TaskStart}
	 * @return {@link ActionResponse}
	 */
	public ActionResponse start(long taskId, TaskStart task) {
		checkArgument(task != null, "task is required");
		checkArgument(task.getOperation() != null, "task.operation is required");
		checkArgument(!Strings.isNullOrEmpty(task.getSourceAk()), "task.sourceAk is required");
		checkArgument(!Strings.isNullOrEmpty(task.getSourceSk()), "task.sourceSk is required");
		checkArgument(!Strings.isNullOrEmpty(task.getTargetAk()), "task.targetAk is required");
		checkArgument(!Strings.isNullOrEmpty(task.getTargetSk()), "task.targetSk is required");

		return putWithResponse(uri("/task/%s", taskId)).entity(task).execute();
	}

	/**
	 * stop task
	 * @param taskId
	 * @return {@link ActionResponse}
	 */
	public ActionResponse stop(long taskId) {
		Map<String, Object> entity = Maps.newHashMap();
		entity.put("operation", Operation.STOP.name().toLowerCase());
		return putWithResponse(uri("/task/%s", taskId)).entity(entity).execute();
	}

	/**
	 * list task
	 * @param options {@link TaskListOptions}
	 * @return {@link Task} array
	 */
	public Task[] list(TaskListOptions options) {
		checkArgument(options != null, "options is required");
		checkArgument(options.getOptions().containsKey("start"), "options.start is required");
		checkArgument(options.getOptions().containsKey("limit"), "options.limit is required");

		return get(Task[].class, uri("/task")).params(options.getOptions()).execute();
	}

	/**
	 * count task
	 * @return 
	 */
	@SuppressWarnings("rawtypes")
	public long count() {
		Map result = get(Map.class, uri("/task")).param("totalcount", true).execute();
		if (result.containsKey("taskcount")) {
			Integer taskcount = (Integer) result.get("taskcount");
			return taskcount.longValue();
		} else
			return 0;
	}

	/**
	 * count task by state
	 * @param state {@link State}
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public long count(State state) {
		checkArgument(state != null, "state is required");

		Map result = get(Map.class, uri("/task")).param("totalcount", true).param("state", state.getVal()).execute();
		if (result.containsKey("taskcount")) {
			Integer taskcount = (Integer) result.get("taskcount");
			return taskcount.longValue();
		} else
			return 0;
	}
	
	/**
	 * get task
	 * @param taskId
	 * @return {@link Task}
	 */
	public Task get(long taskId) {
		return get(Task.class, uri("/task/%s", taskId)).execute();
	}
	
	private void checkSmnInfoWhenPresent(SmnInfo smnInfo) {
		if (smnInfo != null) {
			checkArgument(!Strings.isNullOrEmpty(smnInfo.getTopicUrn()), "smnInfo.topicUrn is required");
			checkArgument(smnInfo.getTriggerConditions() != null, "smnInfo.triggerConditions is required");
		}
	}

	@SuppressWarnings("unchecked")
	private void checkNode(Node node, String fieldName) {
		checkArgument(node != null, String.format("%s is required", fieldName));
		checkArgument(node != null, String.format("%s is required", fieldName));
		
		checkArgument(!Strings.isNullOrEmpty(node.getRegion()), String.format("%s.region is required", fieldName));
		checkArgument(!Strings.isNullOrEmpty(node.getAk()), String.format("%s.ak is required", fieldName));
		checkArgument(!Strings.isNullOrEmpty(node.getSk()), String.format("%s.sk is required", fieldName));
		checkArgument(!Strings.isNullOrEmpty(node.getBucket()), String.format("%s.bucket is required", fieldName));
		checkArgument(node.getObjectKey() != null, String.format("%s.objectKey is required", fieldName));
		if (node.getObjectKey() instanceof Map) {
			Map<String, Object> map = (Map<String, Object>) node.getObjectKey();
			checkArgument(map.containsKey("path"), String.format("%s.objectKey.path is required", fieldName));
			checkArgument(map.containsKey("keys"), String.format("%s.objectKey.Keys is required", fieldName));
		}
	}
}
