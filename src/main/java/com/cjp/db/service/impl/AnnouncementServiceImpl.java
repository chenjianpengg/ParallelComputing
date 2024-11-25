package com.cjp.db.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.cjp.db.exceptions.AnnouncementNotExistException;
import com.cjp.db.mapper.AdminUserMapper;
import com.cjp.db.mapper.AnnouncementMapper;
import com.cjp.db.pojo.dto.AnnouncementDTO;
import com.cjp.db.pojo.entity.Announcement;
import com.cjp.db.pojo.vo.AnnouncementVO;
import com.cjp.db.service.IAnnouncementService;
import com.cjp.db.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author chenjianpeng
 */
@Service
public class AnnouncementServiceImpl implements IAnnouncementService {

    @Resource
    private AnnouncementMapper announcementMapper;

    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public void publish(AnnouncementDTO announcementDTO) {
        Announcement announcement = BeanUtil.copyProperties(announcementDTO, Announcement.class);
        announcement.setCreateTime(LocalDateTime.now());
        announcement.setUpdateTime(LocalDateTime.now());
        announcementMapper.insert(announcement);
    }

    @Override
    public void update(AnnouncementDTO announcementDTO) {
        Long id = announcementDTO.getId();
        if(id == null) {
            throw new AnnouncementNotExistException("公告不存在");
        }
        Announcement announcement = BeanUtil.copyProperties(announcementDTO, Announcement.class);
        announcement.setUpdateTime(LocalDateTime.now());
        announcementMapper.update(announcement);
    }

    @Override
    public void delete(Long id) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Long adminId = Long.parseLong(map.get("id").toString());
        Announcement announcement = Announcement.builder().id(id).deleted(adminId).updateTime(LocalDateTime.now()).build();
        announcementMapper.update(announcement);
    }

    @Override
    public AnnouncementVO details(Long id) {
        Announcement announcement = announcementMapper.selectById(id);
        return BeanUtil.copyProperties(announcement, AnnouncementVO.class);
    }

    @Override
    public List<AnnouncementVO> list() {
        List<Announcement> announcementList = announcementMapper.list();
        return BeanUtil.copyToList(announcementList, AnnouncementVO.class);
    }
}
