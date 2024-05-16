package net.lab1024.smartadmin.module.business.teacher.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.basics.dao.SysProjectDao;
import net.lab1024.smartadmin.module.business.basics.dao.SysStageDao;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysProjectVO;
import net.lab1024.smartadmin.module.business.teacher.dao.TDecisionSummaryDao;
import net.lab1024.smartadmin.module.business.teacher.domain.dto.TDecisionSummaryAddDTO;
import net.lab1024.smartadmin.module.business.teacher.domain.dto.TDecisionSummaryQueryDTO;
import net.lab1024.smartadmin.module.business.teacher.domain.dto.TDecisionSummaryUpdateDTO;
import net.lab1024.smartadmin.module.business.teacher.domain.entity.TDecisionSummaryEntity;
import net.lab1024.smartadmin.module.business.teacher.domain.vo.TDecisionSummaryExcelVO;
import net.lab1024.smartadmin.module.business.teacher.domain.vo.TDecisionSummaryVO;
import net.lab1024.smartadmin.module.system.department.DepartmentDao;
import net.lab1024.smartadmin.module.system.login.domain.LoginDetailVO;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import net.lab1024.smartadmin.util.SmartPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * [ 决策总结表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2022-01-09 16:01:51
 * @since JDK1.8
 */
@Service
public class TDecisionSummaryService {

    @Autowired
    private TDecisionSummaryDao tDecisionSummaryDao;
    @Autowired
    private SysProjectDao sysProjectDao;
    @Autowired
    private SysStageDao sysStageDao;
    @Autowired
    private DepartmentDao departmentDao;
    /**
     * 根据id查询
     */
    public TDecisionSummaryEntity getById(Long id){
        return  tDecisionSummaryDao.selectById(id);
    }

    /**
     * 分页查询
     * @author SMS
     * @date 2022-01-09 16:01:51
     */
    public ResponseDTO<PageResultDTO<TDecisionSummaryVO>> queryByPage(TDecisionSummaryQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<TDecisionSummaryVO> voList = tDecisionSummaryDao.queryByPage(page, queryDTO);
        PageResultDTO<TDecisionSummaryVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }
    /**
     * 分页查询
     * @author SMS
     * @date 2022-01-09 16:01:51
     */
    public TDecisionSummaryEntity queryData(TDecisionSummaryQueryDTO queryDTO) {
        TDecisionSummaryEntity tDecisionSummaryEntity = tDecisionSummaryDao.queryData(queryDTO);
        return tDecisionSummaryEntity;
    }

    /**
     * 添加前查询
     * @author SMS
     * @date 2022-01-09 16:01:51
     */
    public TDecisionSummaryEntity queryDecisionSummary(Long projectId, Long teamId, Long stageId, String business) {
        SysProjectVO sysProjectVO = sysProjectDao.queryData(projectId);
        String teamName = departmentDao.queryName(teamId);
        String stageName = sysStageDao.queryData(stageId);

        TDecisionSummaryQueryDTO queryDTO =new TDecisionSummaryQueryDTO();
        queryDTO.setProject(sysProjectVO.getProName());
        queryDTO.setTeam(teamName);
        queryDTO.setStage(stageName);
        queryDTO.setBusiness(business);
        TDecisionSummaryEntity tDecisionSummaryEntity = queryData(queryDTO);
        return tDecisionSummaryEntity;
    }

    /**
     * 添加前操作
     * @author SMS
     * @date 2022-01-09 16:01:51
     */
    public void addDecisionSummary(Long projectId, Long teamId, Long stageId, String business, HttpServletRequest request) {
        SysProjectVO sysProjectVO = sysProjectDao.queryData(projectId);
        String teamName = departmentDao.queryName(teamId);
        String stageName = sysStageDao.queryData(stageId);
        LoginDetailVO vo = (LoginDetailVO)request.getSession().getAttribute("app_user_login");

        TDecisionSummaryAddDTO addDTO =new TDecisionSummaryAddDTO();
        addDTO.setProject(sysProjectVO.getProName());
        addDTO.setStage(stageName);
        addDTO.setBusiness(business);
        addDTO.setTeam(teamName);
        addDTO.setName(vo.getActualName());
        addDTO.setStatus(0);
        add(addDTO);
    }

    /**
     * 添加
     * @author SMS
     * @date 2022-01-09 16:01:51
     */
    public ResponseDTO<String> add(TDecisionSummaryAddDTO addDTO) {
        TDecisionSummaryEntity entity = SmartBeanUtil.copy(addDTO, TDecisionSummaryEntity.class);
        tDecisionSummaryDao.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SMS
     * @date 2022-01-09 16:01:51
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(TDecisionSummaryUpdateDTO updateDTO) {
        TDecisionSummaryEntity entity = SmartBeanUtil.copy(updateDTO, TDecisionSummaryEntity.class);
        tDecisionSummaryDao.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SMS
     * @date 2022-01-09 16:01:51
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(List<Long> idList) {
        tDecisionSummaryDao.deleteByIdList(idList);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SMS
     * @date 2022-01-09 16:01:51
     */
    public List<TDecisionSummaryExcelVO> queryAllExportData(TDecisionSummaryQueryDTO queryDTO) {
        return tDecisionSummaryDao.queryAllExportData( queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SMS
     * @date 2022-01-09 16:01:51
     */
    public List<TDecisionSummaryExcelVO> queryBatchExportData(List<Long> idList) {
        return tDecisionSummaryDao.queryBatchExportData(idList);
    }

    public ResponseDTO<String> updateStatus(Long id) {
        return tDecisionSummaryDao.updateStatus(1,id);
    }

    public ResponseDTO<List<TDecisionSummaryVO>> queryDataIndex(Long proId) {
        List<TDecisionSummaryVO> tDecisionSummaryVOS = tDecisionSummaryDao.queryDataIndex(proId);
        return ResponseDTO.succData(tDecisionSummaryVOS);
    }
}
