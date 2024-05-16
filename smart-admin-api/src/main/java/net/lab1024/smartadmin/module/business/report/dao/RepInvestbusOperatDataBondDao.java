package net.lab1024.smartadmin.module.business.report.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepInvestbusOperatDataBondQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepInvestbusOperatDataBondEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepInvestbusOperatDataBondVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepInvestbusOperatDataBondExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 投资业务营业数据表(债券) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-24 18:22:46
 * @since JDK1.8
 */
@Mapper
@Component
public interface RepInvestbusOperatDataBondDao extends BaseMapper<RepInvestbusOperatDataBondEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return RepInvestbusOperatDataBondVO
    */
    IPage<RepInvestbusOperatDataBondVO> queryByPage(Page page, @Param("queryDTO") RepInvestbusOperatDataBondQueryDTO queryDTO);

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
    List<RepInvestbusOperatDataBondExcelVO> queryAllExportData(@Param("queryDTO")RepInvestbusOperatDataBondQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<RepInvestbusOperatDataBondExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    List<RepInvestbusOperatDataBondVO> selectInvestbusOperatDataBondPar(@Param("currentStageId")Long currentStageId, @Param("bondType")String bondType);

    List<RepInvestbusOperatDataBondVO> selectInvestbusOperatDataBondRep(@Param("currentTeamId")Long currentTeamId,@Param("currentStageId")Long currentStageId,@Param("bondType")String bondType);

    List<RepInvestbusOperatDataBondVO> query(@Param("currentProjectId")Long currentProjectId,@Param("currentTeamId")Long currentTeamId,@Param("currentStageId")Long currentStageId,@Param("bondType")String bondType);

    RepInvestbusOperatDataBondVO selectInvestbusOperatDataBondPriceValueRep(@Param("currentTeamId")Long currentTeamId, @Param("currentStageId")Long currentStageId, @Param("bondKind")String bondKind, @Param("bondType")String bondType);

    List<RepInvestbusOperatDataBondVO> selectInvestbusOperatDataBondNum(@Param("currentProjectId")Long currentProjectId,@Param("currentTeamId") Long currentTeamId,@Param("currentStageId")Long currentStageId,@Param("bondKind") String bondKind);

    List<RepInvestbusOperatDataBondExcelVO> queryExportData(@Param("currentProjectId")Long proId,@Param("currentStageId") Long stageId);
}
