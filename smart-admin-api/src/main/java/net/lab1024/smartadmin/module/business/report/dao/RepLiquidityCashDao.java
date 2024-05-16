package net.lab1024.smartadmin.module.business.report.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepLiquidityCashQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepLiquidityCashEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepLiquidityCashExcelVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepLiquidityCashVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 流动性报表-现金 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-23 18:30:53
 * @since JDK1.8
 */
@Mapper
@Component
public interface RepLiquidityCashDao extends BaseMapper<RepLiquidityCashEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return RepLiquidityCashVO
    */
    IPage<RepLiquidityCashVO> queryByPage(Page page, @Param("queryDTO") RepLiquidityCashQueryDTO queryDTO);

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
    List<RepLiquidityCashExcelVO> queryAllExportData(@Param("queryDTO")RepLiquidityCashQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<RepLiquidityCashExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);


    RepLiquidityCashVO selectLiquidityCashStageIdPar(@Param("currentStageId") Long currentStageId);

    RepLiquidityCashVO selectLiquidityCashStageIdRep(@Param("currentTeamId") Long currentTeamId, @Param("currentStageId") Long currentStageId);

    RepLiquidityCashVO query(@Param("currentProjectId") Long currentProjectId,@Param("currentTeamId") Long currentTeamId, @Param("currentStageId") Long currentStageId);

    List<RepLiquidityCashExcelVO> queryExportData(@Param("currentProjectId")Long proId,@Param("currentStageId") Long stageId);
}
