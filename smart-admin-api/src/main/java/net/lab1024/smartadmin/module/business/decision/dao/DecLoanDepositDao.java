package net.lab1024.smartadmin.module.business.decision.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecLoanDepositQueryDTO;
import net.lab1024.smartadmin.module.business.decision.domain.entity.DecLoanDepositEntity;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecLoanDepositVO;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecLoanDepositExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 存贷款 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-22 13:54:20
 * @since JDK1.8
 */
@Mapper
@Component
public interface DecLoanDepositDao extends BaseMapper<DecLoanDepositEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return DecLoanDepositVO
    */
    IPage<DecLoanDepositVO> queryByPage(Page page, @Param("queryDTO") DecLoanDepositQueryDTO queryDTO);

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
    List<DecLoanDepositExcelVO> queryAllExportData(@Param("queryDTO")DecLoanDepositQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<DecLoanDepositExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    DecLoanDepositVO query(@Param("currentProjectId")Long currentProjectId, @Param("currentTeamId")Long currentTeamId,@Param("currentStageId") Long currentStageId);

    List<DecLoanDepositVO> queryAllTeam(@Param("currentProjectId")Long currentProjectId,@Param("currentStageId")  Long currentStageId);

    Integer deleteData(@Param("currentProjectId")Long currentProjectId,@Param("currentTeamId") Long currentTeamId, @Param("currentStageId")Long currentStageId);

//    DecLoanDepositVO queryDataAndStatus(@Param("currentProjectId")Long currentProjectId, @Param("currentTeamId")Long currentTeamId,@Param("currentStageId") Long currentStageId,@Param("businessName")String businessName);
    DecLoanDepositVO queryDataAndStatus(@Param("currentProjectId")Long currentProjectId, @Param("currentTeamId")Long currentTeamId,@Param("currentStageId") Long currentStageId);

    List<DecLoanDepositExcelVO> queryExportData(@Param("currentProjectId")Long currentProjectId,@Param("currentStageId")  Long currentStageId);
}
