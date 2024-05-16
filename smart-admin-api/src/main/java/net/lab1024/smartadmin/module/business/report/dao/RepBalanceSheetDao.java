package net.lab1024.smartadmin.module.business.report.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepBalanceSheetQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepBalanceSheetEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepBalanceSheetExcelVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepBalanceSheetVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 资产负债表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-21 14:33:59
 * @since JDK1.8
 */
@Mapper
@Component
public interface RepBalanceSheetDao extends BaseMapper<RepBalanceSheetEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return RepBalanceSheetVO
    */
    IPage<RepBalanceSheetVO> queryByPage(Page page, @Param("queryDTO") RepBalanceSheetQueryDTO queryDTO);

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
    List<RepBalanceSheetExcelVO> queryAllExportData(@Param("queryDTO")RepBalanceSheetQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<RepBalanceSheetExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    /**
     * 查询资产负债结果表 阶段不等于0或-1
     * @param
     * @return
     */
    RepBalanceSheetVO selectBalanceResultRep(@Param("currentTeamId")Long currentTeamId,@Param("currentStageId") Long currentStageId);
    /**
     * 查询资产负债结果表 阶段等于0或-1
     * @param
     * @return
     */
    RepBalanceSheetVO selectBalanceResultPar(@Param("currentStageId") Long currentStageId);

    List<RepBalanceSheetVO> selectBalanceAllResult(@Param("currentProjectId")Long currentProjectId, @Param("currentStageId")Long currentStageIds);

    RepBalanceSheetVO query(@Param("currentProjectId")Long currentProjectId,@Param("currentTeamId")Long currentTeamId,@Param("currentStageId")Long currentStageId);

    List<RepBalanceSheetVO> queryList(@Param("currentProjectId")Long currentProjectId, @Param("currentStageId")Long currentStageId);

    List<RepBalanceSheetVO> queryTotalAssets(@Param("currentProjectId")Long currentProjectId, @Param("currentTeamId")Long currentTeamId, @Param("currentStageId")Long currentStageId);

    RepBalanceSheetVO queryPar(@Param("beforeCurrentStageId")Long beforeCurrentStageId);

    List<RepBalanceSheetExcelVO> queryExcelList(@Param("currentProjectId")Long currentProjectId, @Param("currentStageId")Long currentStageId);
}
