package com.cjp.db.service;

import com.cjp.db.pojo.dto.AnnouncementDTO;
import com.cjp.db.pojo.vo.AnnouncementVO;

import java.util.List;

/**
 * @author chenjianpeng
 */
public interface IAnnouncementService {
    void publish(AnnouncementDTO announcementDTO);

    void update(AnnouncementDTO announcementDTO);

    void delete(Long id);

    AnnouncementVO details(Long id);

    List<AnnouncementVO> list();
}
