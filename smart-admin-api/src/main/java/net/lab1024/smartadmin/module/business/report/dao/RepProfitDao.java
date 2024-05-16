package net.lab1024.smartadmin.module.business.report.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepProfitQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepProfitEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepProfitVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepProfitExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 利润表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-23 14:42:17
 * @since JDK1.8
 */
@Mapper
@Component
public interface RepProfitDao extends BaseMapper<RepProfitEntity> {


    /**
     * 分页查询
     * @param queryDTO
     * @return RepProfitVO
    */
    IPage<RepProfitVO> queryByPage(Page page, @Param("queryDTO") RepProfitQueryDTO queryDTO);

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
    List<RepProfitExcelVO> queryAllExportData(@Param("queryDTO")RepProfitQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<RepProfitExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);
    /**
     * 查询资产负债结果表 阶段不等于0或-1
     * @param
     * @return
     */
    RepProfitVO selectProfitStageIdRep(@Param("currentTeamId")Long currentTeamId,@Param("currentStageId") Long currentStageId);
    /**
     * 查询资产负债结果表 阶段不等于0或-1
     * @param
     * @return
     */
    RepProfitVO selectProfitStageIdRep(@Param("currentProjectId") Long currentProjectId,@Param("currentTeamId")Long currentTeamId,@Param("currentStageId") Long currentStageId);

    /**
     * 查询资产负债结果表 阶段等于0或-1
     * @param
     * @return
     */
    RepProfitVO selectProfitStageIdPar(@Param("currentStageId") Long currentStageId);
    /**
     * 查询某项目所有银行各阶段利润
     * @param
     * @return
     */
    List<RepProfitVO> selectProfitByProId(@Param("proId")Long proId);

    List<RepProfitExcelVO> queryExportData(@Param("currentProjectId")Long proId,@Param("currentStageId") Long stageId);

    RepProfitVO query(@Param("currentProjectId")Long currentProjectId, @Param("currentTeamId")Long currentTeamId, @Param("currentStageId")Long currentStageId);
}
