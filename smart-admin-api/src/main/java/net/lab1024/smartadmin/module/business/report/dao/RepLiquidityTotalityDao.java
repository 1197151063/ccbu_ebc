package net.lab1024.smartadmin.module.business.report.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepLiquidityTotalityQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepLiquidityTotalityEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepLiquidityTotalityExcelVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepLiquidityTotalityVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 流动性报表-总体 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-23 18:34:36
 * @since JDK1.8
 */
@Mapper
@Component
public interface RepLiquidityTotalityDao extends BaseMapper<RepLiquidityTotalityEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return RepLiquidityTotalityVO
    */
    IPage<RepLiquidityTotalityVO> queryByPage(Page page, @Param("queryDTO") RepLiquidityTotalityQueryDTO queryDTO);

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
    List<RepLiquidityTotalityExcelVO> queryAllExportData(@Param("queryDTO")RepLiquidityTotalityQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<RepLiquidityTotalityExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    RepLiquidityTotalityVO selectLiquidityTotalityStageIdPar(@Param("currentStageId") Long currentStageId);

    RepLiquidityTotalityVO selectTotalityTotalityStageIdRep(@Param("currentTeamId")Long currentTeamId,@Param("currentStageId") Long currentStageId);

    RepLiquidityTotalityVO query(@Param("currentProjectId")Long currentProjectId,@Param("currentTeamId") Long currentTeamId, @Param("currentStageId")Long currentStageId);

    List<RepLiquidityTotalityExcelVO> queryExportData(@Param("currentProjectId")Long proId,@Param("currentStageId") Long stageId);
}
