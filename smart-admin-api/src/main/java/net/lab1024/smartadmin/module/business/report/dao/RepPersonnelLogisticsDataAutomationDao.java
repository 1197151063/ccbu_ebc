package net.lab1024.smartadmin.module.business.report.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepPersonnelLogisticsDataAutomationQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepPersonnelLogisticsDataAutomationEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepPersonnelLogisticsDataAutomationVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepPersonnelLogisticsDataAutomationExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 人事后勤数据表(自动化投资) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 11:24:21
 * @since JDK1.8
 */
@Mapper
@Component
public interface RepPersonnelLogisticsDataAutomationDao extends BaseMapper<RepPersonnelLogisticsDataAutomationEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return RepPersonnelLogisticsDataAutomationVO
    */
    IPage<RepPersonnelLogisticsDataAutomationVO> queryByPage(Page page, @Param("queryDTO") RepPersonnelLogisticsDataAutomationQueryDTO queryDTO);

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
    List<RepPersonnelLogisticsDataAutomationExcelVO> queryAllExportData(@Param("queryDTO")RepPersonnelLogisticsDataAutomationQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<RepPersonnelLogisticsDataAutomationExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    RepPersonnelLogisticsDataAutomationVO selectPerLogDataAutomationPar(@Param("currentStageId") Long currentStageId);

    RepPersonnelLogisticsDataAutomationVO selectPerLogDataAutomationRep(@Param("currentTeamId")Long currentTeamId,@Param("currentStageId") Long currentStageId);

    RepPersonnelLogisticsDataAutomationVO query(@Param("currentProjectId")Long projectId,@Param("currentTeamId")Long teamId,@Param("currentStageId") Long stageId);

    List<RepPersonnelLogisticsDataAutomationExcelVO> queryExportData(@Param("currentProjectId")Long proId,@Param("currentStageId") Long stageId);
}
