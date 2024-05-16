package net.lab1024.smartadmin.module.business.decision.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecFinancialManagementQueryDTO;
import net.lab1024.smartadmin.module.business.decision.domain.entity.DecFinancialManagementEntity;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecFinancialManagementVO;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecFinancialManagementExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 财务管理表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-23 16:32:30
 * @since JDK1.8
 */
@Mapper
@Component
public interface DecFinancialManagementDao extends BaseMapper<DecFinancialManagementEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return DecFinancialManagementVO
    */
    IPage<DecFinancialManagementVO> queryByPage(Page page, @Param("queryDTO") DecFinancialManagementQueryDTO queryDTO);

    /**
     * 根据id删除
     * @param id
     * @return
    */
    void deleteById(@Param("id")Long id);

    /**
     * 根据id批量删除
     * @param idList
     * @return
    */
    void deleteByIdList(@Param("idList") List<Long> idList);

        /**
     * 查询所有导出数据
     * @param queryDTO
     * @return
     */
    List<DecFinancialManagementExcelVO> queryAllExportData(@Param("queryDTO")DecFinancialManagementQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<DecFinancialManagementExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    DecFinancialManagementVO query(@Param("currentProjectId")Long currentProjectId, @Param("currentTeamId")Long currentTeamId,@Param("currentStageId") Long currentStageId);

    Integer deleteData(@Param("currentProjectId")Long currentProjectId, @Param("currentTeamId")Long currentTeamId,@Param("currentStageId") Long currentStageId);

    DecFinancialManagementVO queryDataAndStatus(@Param("currentProjectId")Long currentProjectId, @Param("currentTeamId")Long currentTeamId, @Param("currentStageId")Long currentStageId);
}
