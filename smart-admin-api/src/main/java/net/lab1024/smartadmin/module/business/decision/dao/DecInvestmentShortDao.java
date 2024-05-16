package net.lab1024.smartadmin.module.business.decision.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.module.business.decision.domain.dto.DecInvestmentShortQueryDTO;
import net.lab1024.smartadmin.module.business.decision.domain.entity.DecInvestmentShortEntity;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecInvestmentShortExcelVO;
import net.lab1024.smartadmin.module.business.decision.domain.vo.DecInvestmentShortVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 投资业务(短期投资)提交表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-23 16:36:08
 * @since JDK1.8
 */
@Mapper
@Component
public interface DecInvestmentShortDao extends BaseMapper<DecInvestmentShortEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return DecInvestmentShortVO
    */
    IPage<DecInvestmentShortVO> queryByPage(Page page, @Param("queryDTO") DecInvestmentShortQueryDTO queryDTO);

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
    List<DecInvestmentShortExcelVO> queryAllExportData(@Param("queryDTO")DecInvestmentShortQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<DecInvestmentShortExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    DecInvestmentShortVO query(@Param("currentProjectId")Long currentProjectId, @Param("currentTeamId")Long currentTeamId, @Param("currentStageId")Long currentStageId);

    void deleteData(@Param("currentProjectId")Long currentProjectId, @Param("currentTeamId")Long currentTeamId, @Param("currentStageId")Long currentStageId);

}
