package net.lab1024.smartadmin.module.business.basics.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.basics.dao.SysProjectDao;
import net.lab1024.smartadmin.module.business.basics.dao.SysProjectStageDao;
import net.lab1024.smartadmin.module.business.basics.dao.SysProjectTeamStageDao;
import net.lab1024.smartadmin.module.business.basics.dao.SysStageDao;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysProjectAddDTO;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysProjectQueryDTO;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysProjectUpdateDTO;
import net.lab1024.smartadmin.module.business.basics.domain.entity.SysProjectEntity;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectExcelVO;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectVO;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import net.lab1024.smartadmin.util.SmartPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * [ ��Ŀ�� ]
 *
 * @author SMS
 * @version 1.0
 * @company 1024����ʵ����( www.1024lab.net )
 * @copyright (c)  1024����ʵ����( www.1024lab.net )Inc. All rights reserved.
 * @date 2021-11-21 09:08:37
 * @since JDK1.8
 */
@Service
public class SysProjectService {

    @Autowired
    private SysProjectDao sysProjectDao;
    @Autowired
    private SysProjectTeamStageDao sysProjectTeamStageDao;
    @Autowired
    private SysStageDao sysStageDao;
    @Autowired
    private SysProjectStageDao sysProjectStageDao;

    /**
     * ����id��ѯ
     */
    public SysProjectEntity getById(Long id) {
        return sysProjectDao.selectById(id);
    }
    public SysProjectVO queryData(Long id) {
        return sysProjectDao.queryData(id);
    }
    /**
     * 分页查询
     *
     * @author SMS
     * @date 2021-11-21 09:08:37
     */
    public ResponseDTO<PageResultDTO<SysProjectVO>> queryByPage(SysProjectQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<SysProjectVO> voList = sysProjectDao.queryByPage(page, queryDTO);
        PageResultDTO<SysProjectVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }


    public ResponseDTO<List<SysProjectVO>> queryBy(SysProjectQueryDTO queryDTO) {
        List<SysProjectVO> voList = sysProjectDao.queryBy(queryDTO);
        return ResponseDTO.succData(voList);
    }

    /**
     * 添加项目
     *
     * @author SMS
     * @date 2021-11-21 09:08:37
     */
    public ResponseDTO<String> add(SysProjectAddDTO addDTO) {
        SysProjectEntity entity = SmartBeanUtil.copy(addDTO, SysProjectEntity.class);
        entity.setStatus(0);
        sysProjectDao.insertProject(entity);
        //新增项目时生成阶段信息
        if (addDTO.getProId() == null) {
            List<Integer> stageIds = sysStageDao.queryAllId();
            for (Integer stageId : stageIds) {
                System.out.println(stageId);
                //创建项目后自动生成阶段开关
                if (stageId <= 1) {
                    sysProjectStageDao.insertProjectStage(entity.getProId(), stageId, 2);
                }
                if (2 == stageId) {
                    sysProjectStageDao.insertProjectStage(entity.getProId(), stageId, 1);
                }
                if (2 < stageId) {
                    sysProjectStageDao.insertProjectStage(entity.getProId(), stageId, 0);
                }
            }
        }
        return ResponseDTO.succ();
    }

    /**
     * �༭
     *
     * @author SMS
     * @date 2021-11-21 09:08:37
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(SysProjectUpdateDTO updateDTO) {
        SysProjectEntity entity = SmartBeanUtil.copy(updateDTO, SysProjectEntity.class);
        entity.setUpdateTime(new Date());
        sysProjectDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * ɾ��
     *
     * @author SMS
     * @date 2021-11-21 09:08:37
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        sysProjectDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * ��ѯȫ����������
     *
     * @author SMS
     * @date 2021-11-21 09:08:37
     */
    public List<SysProjectExcelVO> queryAllExportData(SysProjectQueryDTO queryDTO) {
        return sysProjectDao.queryAllExportData(queryDTO);
    }

    /**
     * ������ѯ��������
     *
     * @author SMS
     * @date 2021-11-21 09:08:37
     */
    public List<SysProjectExcelVO> queryBatchExportData(List<Long> idList) {
        return sysProjectDao.queryBatchExportData(idList);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteById(Long id) {
        sysProjectDao.deleteById(id);
        return ResponseDTO.succ();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> updateProjectByProId(SysProjectAddDTO project) {
        sysProjectDao.updateProjectByProId(project);
        return ResponseDTO.succ();
    }

    public ResponseDTO<List<SysProjectVO>> queryAllProject() {
        List<SysProjectVO> voList = sysProjectDao.queryByAllProject();
        return ResponseDTO.succData(voList);
    }

    public ResponseDTO<Integer> selectByStageId(Long proId) {
        Integer integers = sysProjectDao.selectByStageId(proId, 1);
        return ResponseDTO.succData(integers);
    }

}
