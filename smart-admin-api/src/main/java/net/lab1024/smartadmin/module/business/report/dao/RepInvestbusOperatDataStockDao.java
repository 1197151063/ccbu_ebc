package net.lab1024.smartadmin.module.business.report.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepInvestbusOperatDataStockQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepInvestbusOperatDataStockEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepInvestbusOperatDataStockExcelVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepInvestbusOperatDataStockVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 投资业务营业数据表(股票) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-24 18:23:18
 * @since JDK1.8
 */
@Mapper
@Component
public interface RepInvestbusOperatDataStockDao extends BaseMapper<RepInvestbusOperatDataStockEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return RepInvestbusOperatDataStockVO
    */
    IPage<RepInvestbusOperatDataStockVO> queryByPage(Page page, @Param("queryDTO") RepInvestbusOperatDataStockQueryDTO queryDTO);

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
    List<RepInvestbusOperatDataStockExcelVO> queryAllExportData(@Param("queryDTO")RepInvestbusOperatDataStockQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<RepInvestbusOperatDataStockExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    /**
     *
     * @param currentStageId
     * @return
     */
    List<RepInvestbusOperatDataStockVO> selectInvestbusOperatDataStockPar(@Param("currentStageId") Long currentStageId,@Param("stockType") String stockType);

    /**
     *
     * @param currentTeamId
     * @return
     */
    List<RepInvestbusOperatDataStockVO> selectInvestbusOperatDataStockRep(@Param("currentTeamId")Long currentTeamId,@Param("currentStageId") Long currentStageId, @Param("stockType")String stockType);

    /**
     *
     * @param currentTeamId
     * @return
     */
    List<RepInvestbusOperatDataStockVO> query(@Param("currentProjectId")Long currentProjectId,@Param("currentTeamId")Long currentTeamId,@Param("currentStageId") Long currentStageId, @Param("stockType")String stockType);


    RepInvestbusOperatDataStockVO selectInvestbusOperatData(@Param("currentTeamId")Long currentTeamId,@Param("currentStageId")Long currentStageId, @Param("stockProject")String stockProject, @Param("stockType")String stockType);

    List<RepInvestbusOperatDataStockExcelVO> queryExportData(@Param("currentProjectId")Long proId,@Param("currentStageId") Long stageId);
}
