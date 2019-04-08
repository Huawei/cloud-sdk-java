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
package com.huawei.openstack4j.api.image.v2;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Payload;
import com.huawei.openstack4j.model.common.Payloads;
import com.huawei.openstack4j.model.image.v2.ContainerFormat;
import com.huawei.openstack4j.model.image.v2.DiskFormat;
import com.huawei.openstack4j.model.image.v2.Image;
import com.huawei.openstack4j.model.image.v2.Image.ImageStatus;
import com.huawei.openstack4j.model.image.v2.Image.ImageVisibility;
import com.huawei.openstack4j.model.image.v2.Member;

/**
 * @author emjburns
 */
@Test(suiteName="Image/imagesv2", enabled=true)
public class ImageV2Tests extends AbstractTest {
    private static final String IMAGES_JSON = "/image/v2/images.json";
    private static final String IMAGE_JSON = "/image/v2/image.json";
    private static final String IMAGE_UPDATE_JSON = "/image/v2/image-update.json";
    private static final String MEMBER_JSON = "/image/v2/member.json";
    private static final String MEMBER_UPDATE_JSON = "/image/v2/member-update.json";
    private static final String MEMBERS_JSON = "/image/v2/members.json";
    private static final String TASK_JSON = "/image/v2/task.json";
    private static final String TASKS_JSON = "/image/v2/tasks.json";
    private static final String TASKS_FILTERED_JSON = "/image/v2/tasks-filtered.json";
    private static final String BINARY_IMAGE_DATA =
            "943c 7b3c 3ef4 eac8 e906 b220 1efb f01f\n" +
            "00b4 5b1b b4fa 0707 c2ac 378b e722 514d\n" +
            "5fb9 e9a0 7f9f fa4c 645d 113c 0524 b380\n" +
            "acee 6344 1f45 b58b 1eb2 8776 3e9b 9aef";

    public void listImagesTest() throws IOException {
        respondWith(IMAGES_JSON);
        List<? extends Image> list = osv3().imagesV2().list();
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).getId(), "7541b8be-c62b-46c3-b5a5-5bb5ce43ce01");
    }

    public void listImagesFilterTest() throws IOException {
        respondWith(IMAGES_JSON);
        Map<String, String> map = new HashMap<>();
        map.put("container_format", "bare");
        List<? extends Image> list = osv3().imagesV2().list(map);
        assertEquals(list.size(), 2);
    }

    public void getImageTest() throws IOException {
        respondWith(IMAGE_JSON);
        String id = "8a2ea42d-06b5-42c2-a54d-97105420f2bb";
        String file = "/v2/images/8a2ea42d-06b5-42c2-a54d-97105420f2bb/file";
        String owner = "48e3c436235547a68324c2891bea41ac";
        String size = "566600704";
        String self = "/v2/images/8a2ea42d-06b5-42c2-a54d-97105420f2bb";
        String schema = "/v2/schemas/image";
        String name = "amphora-x64-haproxy";
        String checkSum = "896e5473caaafac8899c21c912a46c98";
        String osbit = "32";
        String osVersion = "v";
        String description = "this is a test";
        String regist = "true";
        String platform = "Windows";
        String osType = "Windows";
        String envType = "FusionCompute";
        String sourceType = "uds";
        String originalImage = "987654321";
        String bacupId = "48e3c436235547a68324c2891bea41ac";
        String productCode = "48e3c436235547a68324c2891bea41ac";
        String imageSize = "22";
        String dataOrigin = "48e3c436235547a68324c2891bea41ac";
        String global = "22";
        
        Image image = osv3().imagesV2().get(id);
        assertNotNull(image);
        assertNotNull(image.getId());
        assertEquals(image.getId(),id);
        assertEquals(image.getFile(), file);
        assertEquals(image.getOwner(), owner);
        assertEquals(image.getSize(), Long.valueOf(size));
        assertEquals(image.getSelf(), self);
        assertEquals(image.getSchema(), schema);
        assertEquals(image.getStatus(), ImageStatus.ACTIVE);
        assertEquals(image.getTags().isEmpty(), true);
        assertEquals(image.getVisibility(), ImageVisibility.PUBLIC);
        assertEquals(image.getName(), name);
        assertEquals(image.getChecksum(), checkSum);
        assertEquals(image.getDeleted(), Boolean.FALSE);
        assertEquals(image.getIsProtected(), Boolean.FALSE);
        assertEquals(image.getContainerFormat(), ContainerFormat.BARE);
        assertEquals(image.getMinRam(), Long.valueOf(0));
        assertEquals(image.getOsBit(), osbit);
        assertEquals(image.getOsVersion(), osVersion);
        assertEquals(image.getDescription(), description);
        assertEquals(image.getDiskFormat(), DiskFormat.QCOW2);
        assertEquals(image.getIsRegistered(), regist);
        assertEquals(image.getPlatForm(), platform);
        assertEquals(image.getOsType(), osType);
        assertEquals(image.getMinDisk(), Long.valueOf(0));
        assertEquals(image.getVirtualEnvType(), envType);
        assertEquals(image.getImageSourceType(), sourceType);
        assertEquals(image.getVirtualSize(), Long.valueOf(12));
        assertEquals(image.getOriginalImageName(), originalImage);
        assertEquals(image.getBackupId(), bacupId);
        assertEquals(image.getProductCode(), productCode);
        assertEquals(image.getImageSize(), imageSize);
        assertEquals(image.getDataOrigin(), dataOrigin);
        assertEquals(image.getSequenceNum(), global);
        assertEquals(image.getSupportKvm(), global);
        assertEquals(image.getSupportXen(), global);
        assertEquals(image.getSupportDiskIntensive(), global);
        assertEquals(image.getSupportHighPerformance(), global);
        assertEquals(image.getSupportXenGpuType(), global);
        
    }

    @SuppressWarnings("unchecked")
	public void createImageTest() throws IOException {
        respondWith(IMAGE_JSON);
        String id = "8a2ea42d-06b5-42c2-a54d-97105420f2bb";
        String name = "amphora-x64-haproxy";
        ContainerFormat cf = ContainerFormat.BARE;
        DiskFormat df = DiskFormat.QCOW2;
        Long mindisk = 0L;
        Long minram = 0L;
        Image.ImageVisibility vis = Image.ImageVisibility.PUBLIC;
        String key1 = "test-key1";
        String key2 = "test-key2";
        String key3 = "id";
        String value1 = "test-value1";
        String value2 = "test-value2";
        String value3 = "test-value3";
        Image im = Builders.imageV2()
                .id(id)
                .name(name)
                .containerFormat(cf)
                .diskFormat(df)
                .minDisk(mindisk)
                .minRam(minram)
                .visibility(vis)
                .osVersion("******")
                .visibility(ImageVisibility.PRIVATE)
                .isProtected(true)
                .tags(Collections.EMPTY_LIST)
                .additionalProperty(key1, value1)
                .additionalProperty(key2, value2)
                .additionalProperty(key3, value3)
                .build();
        Image image = osv3().imagesV2().create(im);
        assertNotNull(image);
        assertEquals(image.getId(), id);
        assertEquals(image.getName(), name);
        assertEquals(image.getContainerFormat(), cf);
        assertEquals(image.getDiskFormat(), df);
        assertEquals(image.getVisibility(), vis);
        assertEquals(image.getMinDisk(), mindisk);
        assertEquals(image.getMinRam(), minram);
        assertEquals(image.getAdditionalPropertyValue(key1), value1);
        assertEquals(image.getAdditionalPropertyValue(key2), value2);
        assertNull(image.getAdditionalPropertyValue(key3));
    }

    public void deleteImageTest() throws IOException {
        respondWith(204);
        ActionResponse delete = osv3().imagesV2().delete("8a2ea42d-06b5-42c2-a54d-97105420f2bb");
        assertTrue(delete.isSuccess());
    }

    public void deactivateImageTest() throws IOException {
        respondWith(204);
        ActionResponse deactivate = osv3().imagesV2().deactivate("8a2ea42d-06b5-42c2-a54d-97105420f2bb");
        assertTrue(deactivate.isSuccess());
    }

    public void reactivateImageTest() throws IOException {
        respondWith(204);
        ActionResponse reactivate = osv3().imagesV2().reactivate("8a2ea42d-06b5-42c2-a54d-97105420f2bb");
        assertTrue(reactivate.isSuccess());
    }

    public void createMemberTest() throws IOException {
        respondWith(MEMBER_JSON);
        String imageId = "4b434528-032b-4467-946c-b5880ce15c06";
        String memberId = "66cabdfb14bd48d48402f7464bda7733";
        Member member = osv3().imagesV2().createMember(imageId, memberId);
        assertEquals(member.getStatus(), Member.MemberStatus.PENDING);
        assertEquals(member.getImageId(), imageId);
        assertEquals(member.getMemberId(), memberId);

    }

    public void getMemberTest() throws IOException {
        respondWith(MEMBER_JSON);
        String imageId = "4b434528-032b-4467-946c-b5880ce15c06";
        String memberId = "66cabdfb14bd48d48402f7464bda7733";
        Member member = osv3().imagesV2().getMember(imageId, memberId);
        assertEquals(member.getStatus(), Member.MemberStatus.PENDING);
        assertEquals(member.getImageId(), imageId);
        assertEquals(member.getMemberId(), memberId);
    }

    public void listMembersTest() throws IOException {
        respondWith(MEMBERS_JSON);
        String imageId = "4b434528-032b-4467-946c-b5880ce15c06";
        List<? extends Member> members = osv3().imagesV2().listMembers(imageId);
        assertNotNull(members);

        Member member = members.get(0);
        assertEquals(member.getImageId(), "4b434528-032b-4467-946c-b5880ce15c06");
        assertEquals(member.getMemberId(), "66cabdfb14bd48d48402f7464bda7733");
    }

    public void updateMemberTest() throws IOException {
        respondWith(MEMBER_UPDATE_JSON);
        String imageId = "4b434528-032b-4467-946c-b5880ce15c06";
        String memberId = "66cabdfb14bd48d48402f7464bda7733";
        Member.MemberStatus ms = Member.MemberStatus.ACCEPTED;
        Member member = osv3().imagesV2().updateMember(imageId, memberId, ms);
        assertNotNull(member);
        assertEquals(member.getImageId(), imageId);
        assertEquals(member.getMemberId(), memberId);
        assertEquals(member.getStatus(), ms);
    }

    public void deleteMemberTest() throws IOException {
        respondWith(204);
        String imageId = "4b434528-032b-4467-946c-b5880ce15c06";
        String memberId = "66cabdfb14bd48d48402f7464bda7733";
        ActionResponse deleteMember = osv3().imagesV2().deleteMember(imageId, memberId);
        assertTrue(deleteMember.isSuccess());
    }

    public void updateImageTagTest() throws IOException {
        respondWith(204);
        String tag = "tag1";
        String imageId = "8a2ea42d-06b5-42c2-a54d-97105420f2bb";
        ActionResponse ur = osv3().imagesV2().updateTag(imageId, tag);
        assertTrue(ur.isSuccess());
    }

    public void deleteImageTagTest() throws IOException {
        respondWith(204);
        String tag = "tag1";
        String imageId = "8a2ea42d-06b5-42c2-a54d-97105420f2bb";
        ActionResponse deleteTag = osv3().imagesV2().deleteTag(imageId, tag);
        assertTrue(deleteTag.isSuccess());
    }

   /* public void getTask() throws IOException {
        respondWith(TASK_JSON);
        String id = "78925244-2951-462d-b979-773a49274d7f";
        Task task = osv3().imagesV2().tasks().get(id);
        assertNotNull(task);
        assertEquals(task.getId(), id);
        assertEquals(task.getType(), "import");
    }

    public void createTask() throws IOException {
        respondWith(TASK_JSON);
        Map<String, Object> input = new HashMap<>();
        input.put("test", "hi");
        String type = "import";
        Task t = Builders.taskBuilder().type(type).input(input).build();
        Task task = osv3().imagesV2().tasks().create(t);
        assertNotNull(task);
        assertEquals(task.getType(), type);
    }

    public void listTasks() throws IOException {
        respondWith(TASKS_JSON);
        List<? extends Task> list = osv3().imagesV2().tasks().list();
        assertNotNull(list);
        assertTrue(list.size() == 2);
    }

    public void listTaskWithParams() throws IOException {
        respondWith(TASKS_FILTERED_JSON);
        String id = "78925244-2951-462d-b979-773a49274d7f";
        Map<String,String> params = new HashMap<>();
        params.put("id", id);
        List<? extends Task> list = osv3().imagesV2().tasks().list(params);
        assertNotNull(list);
        assertTrue(list.size() == 1);
        assertEquals(list.get(0).getId(), id);
    }
*/
    public void uploadImageTest() throws IOException {
        respondWith(204);
        String imageId = "4b434528-032b-4467-946c-b5880ce15c06";
        InputStream s = new ByteArrayInputStream(BINARY_IMAGE_DATA.getBytes(StandardCharsets.UTF_8));
        Payload<InputStream> payload = Payloads.create(s);
        ActionResponse upload  = osv3().imagesV2().upload(imageId, payload, null);
        assertTrue(upload.isSuccess());
    }

     public void downloadImageTest() throws IOException {
         respondWith(200);
         String imageId = "4b434528-032b-4467-946c-b5880ce15c06";
         URI uri = null;
         try {
             uri = new URI("file:////test.iso");
         }catch (URISyntaxException e) {
             e.printStackTrace();
         }
         if(uri == null) return;
         File file = new File(uri);
         ActionResponse download = osv3().imagesV2().download(imageId, file);
         // Should fail to write to file
         assertEquals(download.getCode(), 400);
     }

    @Override
    protected Service service() {
        return Service.IMAGE;
    }
}
