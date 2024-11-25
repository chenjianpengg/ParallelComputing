package com.cjp.db.controller;

import com.cjp.db.annotation.Authority;
import com.cjp.db.enums.Role;
import com.cjp.db.pojo.dto.AnnouncementDTO;
import com.cjp.db.pojo.vo.AnnouncementVO;
import com.cjp.db.service.IAnnouncementService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chenjianpeng
 */
@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

    @Resource
    private IAnnouncementService announcementService;

    /**
     * 发布公告
     */
    @Authority(Role.ADMIN)
    @PostMapping("/publish")
    public void publishAnnouncement(@RequestBody @Validated AnnouncementDTO announcementDTO) {
        announcementService.publish(announcementDTO);
    }

    /**
     * 修改公告
     */
    @Authority(Role.ADMIN)
    @PutMapping("/update")
    public void updateAnnouncement(@RequestBody @Validated AnnouncementDTO announcementDTO) {
        announcementService.update(announcementDTO);
    }

    /**
     * 删除公告
     */
    @Authority(Role.ADMIN)
    @DeleteMapping("/delete/{id}")
    public void deleteAnnouncement(@PathVariable Long id) {
        announcementService.delete(id);
    }

    /**
     * 根据id查看公告详情
     */
    @GetMapping("/details/{id}")
    public AnnouncementVO details(@PathVariable Long id) {
        return announcementService.details(id);
    }

    /**
     * 查询所有公告
     */
    @GetMapping("/list")
    public List<AnnouncementVO> list() {
        return announcementService.list();
    }
}
