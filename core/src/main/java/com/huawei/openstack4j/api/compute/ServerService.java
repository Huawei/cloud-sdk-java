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
package com.huawei.openstack4j.api.compute;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.huawei.openstack4j.api.compute.ext.InstanceActionsService;
import com.huawei.openstack4j.api.compute.ext.InterfaceService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.compute.Action;
import com.huawei.openstack4j.model.compute.RebootType;
import com.huawei.openstack4j.model.compute.Server;
import com.huawei.openstack4j.model.compute.Server.Status;
import com.huawei.openstack4j.model.compute.ServerCreate;
import com.huawei.openstack4j.model.compute.ServerPassword;
import com.huawei.openstack4j.model.compute.ServerUpdateOptions;
import com.huawei.openstack4j.model.compute.StopType;
import com.huawei.openstack4j.model.compute.VNCConsole;
import com.huawei.openstack4j.model.compute.VNCConsole.Type;
import com.huawei.openstack4j.model.compute.VolumeAttachment;
import com.huawei.openstack4j.model.compute.actions.BackupOptions;
import com.huawei.openstack4j.model.compute.actions.EvacuateOptions;
import com.huawei.openstack4j.model.compute.actions.LiveMigrateOptions;
import com.huawei.openstack4j.model.compute.actions.RebuildOptions;
import com.huawei.openstack4j.model.compute.builder.ServerCreateBuilder;

/**
 * Server Operations API
 * 
 * @author Jeremy Unruh
 */
public interface ServerService {

    /**
     * List all servers (detailed) that the current tenant has access to
     *
     * @return list of all servers
     */
    List<? extends Server> list();

    /**
     * List all servers (detailed / brief) that the current tenant has access to
     *
     * @param detail if true all attributes will be populated, false (brief) will be ID, Name and Links
     * @return list of all servers
     */
    List<? extends Server> list(boolean detail);
    
    /**
     * List all servers (detailed / brief) that the current tenant has access to
     *
     * @param detail if true all attributes will be populated, false (brief) will be ID, Name and Links
     * @param filteringParams map (name, value) of filtering parameters
     * @return list of all servers
     */
    List<? extends Server> list(boolean detail , Map<String, String> filteringParams);

    /**
     * Returns list of servers filtered by parameters.
     * 
     * @param filteringParams map (name, value) of filtering parameters
     * @return 
     */
    List<? extends Server> list(Map<String, String> filteringParams);

    /**
     * List all servers for all tenants (detailed / brief) 
     *
     * @param detail if true all attributes will be populated, false (brief) will be ID, Name and Links
     * @return list of all servers
     */
//    List<? extends Server> listAll(boolean detail);

    /**
     * Get the specified server by ID
     *
     * @param serverId the server id
     * @return the server or null if not found
     */
    Server get(String serverId);

    /**
     * Create (boot) a new Server
     *
     * @param server the server to boot
     * @return the newly created server
     */
    Server boot(ServerCreate server);
    
    /**
     * Create (boot) multiple Servers and return reservation-id
     *
     * @param server the server to boot
     * @return The reservation id for the created servers
     
    String bootAndReturnReservationId(ServerCreate server);
    */

    /**
     * Create (boot) a new Server
     *
     * @param server the server to boot
     * @param maxWaitTime the max time to wait in milliseconds for the server to become ACTIVE
     * @return the newly created server
     */
    Server bootAndWaitActive(ServerCreate server, int maxWaitTime);

    /**
     * Delete (i.e shut down and delete the image) of the server
     * @param serverId the server identifier
     * @return the action response
     */
    ActionResponse delete(String serverId);

    /**
     * Executes the specified Action such as RESUME, PAUSE, START, STOP ... see (@link {@link Action} for
     * all possible actions
     *
     * @param serverId the server identifier to execute the action against
     * @param action the action the specified action
     * @return the action response
     */
    ActionResponse action(String serverId, Action action);

    /**
     * Stop a server by SOFT (software-level) or HARD (hardware power cycle)
     *
     * @param serverId the server id
     * @param type the type of stop
     * @return the action response
     */
    ActionResponse stop(String serverId, StopType type);

    /**
     * Reboot a server by SOFT (software-level) or HARD (hardware power cycle)
     *
     * @param serverId the server id
     * @param type the type of reboot
     * @return the action response
     */
    ActionResponse reboot(String serverId, RebootType type);
    
    /**
     * Rebuilds the specified server
     * 
     * @param serverId the server id
     * @param options additional options used during the rebuild. (OPTIONAL, can be null)
     * 
     * @return the action response
     */
    ActionResponse rebuild(String serverId, RebuildOptions options);

    /**
     * Resize a server's resources.  Until a resize event is confirmed {@link #confirmResize(String)}, the old server
     * will be kept around and you'll be able to roll back to the old flavor quick with {@link #revertResize(String)}.  All resizes
     * will be automatically confirmed after 24 hours.
     * 
     * @param serverId the server identifier
     * @param flavorId the new flavor id to resize to
     * @return the action response
     */
    ActionResponse resize(String serverId, String flavorId);

    /**
     * Confirm that the resize worked, thus removing the original server
     * 
     * @param serverId the server identifier
     * @return the action response
     */
    ActionResponse confirmResize(String serverId);

    /**
     * Revert a previous resize, switching back to the old server
     * 
     * @param serverId the server identifier
     * @return the action response
     */
    ActionResponse revertResize(String serverId);

    /**
     * Creates the snapshot for a Server
     *
     * @param serverId the server id
     * @param snapshotName the snapshot name
     * @return the newly created snapshot UUID
     */
    String createSnapshot(String serverId, String snapshotName);

    /**
     * Associates the specified Server Group by name to the Server by it's identifier
     * 
     * @param serverId the server identifier
     * @param secGroupName the security group name
     * @return the action response
     */
    ActionResponse addSecurityGroup(String serverId, String secGroupName);

    /**
     * Removes the specified Server Group by name from the Server by it's identifier
     * 
     * @param serverId the server identifier
     * @param secGroupName the security group name
     * @return the action response
     */
    ActionResponse removeSecurityGroup(String serverId, String secGroupName);

    /**
     * Gets usage information about the server.  Usage includes CPU, Memory, IO.  Information
     * is dependent on the hypervisor used by the OpenStack installation and whether that hypervisor
     * supports diagnostics
     *
     * @param serverId the server id
     * @return Map of collected usage statistics organized by key and value
     */
    Map<String, ? extends Number> diagnostics(String serverId);

    /**
     * @return a builder to create a ServerCreate
     */
    ServerCreateBuilder serverBuilder();

    /**
     * Will attempt to tail and return the last {@code numLines} from the given servers console.
     * @param serverId the server identifier
     * @param numLines the number of console lines to return.
     * 				   If lower or equal than zero, the whole console content will be returned.
     * @return console output as string or null
     */
    String getConsoleOutput(String serverId, int numLines);

    /**
     * Obtains the VNC Console connection information for the given server and VNC Console Type
     * 
     * @param serverId the server identifier
     * @param type the VNC Console type
     * @return VNCConsole or null if not applicable
     */
    VNCConsole getVNCConsole(String serverId, Type type);

    /**
     * attach the volume  to the given server 
     * 
     * @param serverId the server identifier
     * @param volumeId the volume identifier
     * @param device the device to attach the volume to, ex /dev/vda
     * @return volumeAttachment or null if not applicable
     */
    VolumeAttachment attachVolume(String serverId, String volumeId, String device);
    
    /**
     * Changes the admin/root password on the server
     * 
     * @param serverId the server identifier
     * @param adminPassword the new password
     * @return ActionResponse
     */
    ActionResponse changeAdminPassword(String serverId, String adminPassword);

    /**
     * detach the volume to the given server
     * @param serverId the server identifier
     * @param attachmentId the attachment identifier
     * 
     * @return the action response
     */
    ActionResponse detachVolume(String serverId,String attachmentId);
    
    /**
     * Only user with admin role can do this.
     * Migrate a server. The new host will be selected by the scheduler.  Until a resize event is confirmed {@link #confirmResize(String)}, the old server
     * will be kept around and you'll be able to roll back to the old host quick with {@link #revertResize(String)}.  All resizes
     * will be automatically confirmed after 24 hours.
     * 
     * @param serverId the server identifier
     * @return the action response
     */
    ActionResponse migrateServer(String serverId);
    
    /**
     * Live-migrates a server identified with {@code serverId} to a new host without rebooting
     * 
     * @param serverId the server identifier
     * @param options live migration options
     * @return ActionResponse
     */
    ActionResponse liveMigrate(String serverId, LiveMigrateOptions options); 
    
    /**
     * Resets the state of a server to a specified {@code state}
     * 
     * @param serverId the server identifier
     * @param state the new server state
     * @return ActionResponse
     */
    ActionResponse resetState(String serverId, Status state); 
    
    /**
     * Sets up a new backup schedule service for the given {@code serverId}
     * 
     * @param serverId the server identifier
     * @param options the backup options
     * @return ActionResponse
     */
    ActionResponse backupServer(String serverId, BackupOptions options);
    
    /**
     * Will poll the Server waiting for the {@code Status} to match or an Error state occurs for the {@code maxWait} 
     * 
     * @param serverId the server identifier
     * @param status the status to wait for
     * @param maxWait the max wait time
     * @param maxWaitUnit the unit the max wait time was specified in
     * @return the last Server polled or null.  User should re-check status in case max wait was hit and the status 
     *         was still not in the desired state.  
     */
    Server waitForServerStatus(String serverId, Status status, int maxWait, TimeUnit maxWaitUnit);
    
    /**
     * Returns the metadata for the specified server
     * 
     * @param serverId the server identifier
     * @return Map of metadata of key and value
     */
    Map<String, String> getMetadata(String serverId);
    
    /**
     * Creates or replaces metadata items for the specified server
     * 
     * @param serverId the server identifier
     * @param metadata the metadata to create or update
     * @return Map of metadata as the current state on the server
     */
    Map<String, String> updateMetadata(String serverId, Map<String, String> metadata);
    
    /**
     * Removes the specified metadata item via the specified {@code key} and {@code serverId}
     * 
     * @param serverId the server identifier
     * @param key the metadata key to remove
     * @return the action response
     */
    ActionResponse deleteMetadataItem(String serverId, String key);
    
    /**
     * Updates an existing Server instance
     * 
     * @param serverId the server identifier
     * @param options the options used to update
     * @return the updated server
     */
    Server update(String serverId, ServerUpdateOptions options);
    
    /**
     * The interface attachment service extension (os-interface)
     * @return the interface service
     */
    InterfaceService interfaces();
    
    /**
     * The instance actions service extension (os-instance-actions)
     * @return the instance actions service
     */
    InstanceActionsService instanceActions();

    /**
     * Returns the encrypted password for the specified server which can be decrypted with
     * the private key
     * 
     * @param serverId the server identifier
     * @return the encrypted server password
     */
    ServerPassword getPassword(String serverId);

    /**
     * Evacuates a server identified with {@code serverId} from a failed host to a new host
     * 
     * @param serverId the server identifier
     * @param options evaucate options
     * @return an administrative password to access the evacuated or rebuilt instance.
     */
    ServerPassword evacuate(String serverId, EvacuateOptions options);

//    /**
//     * get volumeAttachment by serverId and volumeId
//     *
//     * @param serverId the server identifier
//     * @param volumeId the volume identifier
//     * @return volumeAttachment or null if not applicable
//     */
//    VolumeAttachment getAttachVolume(String serverId, String volumeId);
//
//    /**
//     * Show Attached Volumes
//     *
//     * @param serverId the server identifier
//     * @return list of all volumeAttachments
//     */
//    List<? extends VolumeAttachment> listAttachedVolumes(String serverId);
//
//    /**
//     * Get metadata item via the specified {@code key} and {@code serverId}
//     *
//     * @param serverId the server identifier
//     * @return Map of metadata of key and value
//     */
//    Map<String, String> getMetadataItem(String serverId,String key);
//
//    /**
//     * Get metadata item via the specified {@code key} and {@code serverId}
//     *
//     * @param serverId the server identifier
//     * @return Map of metadata of key and value
//     */
//    Map<String, String> setMetadataItem(String serverId,String key, String value);
}
