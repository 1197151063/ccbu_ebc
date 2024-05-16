package net.lab1024.smartadmin.module.business.param.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParProjectRateQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParProjectRateEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParProjectRateVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParProjectRateExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 项目利率表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:52:11
 * @since JDK1.8
 */
@Mapper
@Component
public interface ParProjectRateDao extends BaseMapper<ParProjectRateEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ParProjectRateVO
    */
    IPage<ParProjectRateVO> queryByPage(Page page, @Param("queryDTO") ParProjectRateQueryDTO queryDTO);

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
    List<ParProjectRateExcelVO> queryAllExportData(@Param("queryDTO")ParProjectRateQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ParProjectRateExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    List<ParProjectRateVO> queryBefore(@Param("beforeCurrentStageId")Long beforeCurrentStageId, @Param("afterCurrentStageId")Long afterCurrentStageId);


    List<ParProjectRateVO> selectPar(@Param("currentStageId")Long currentStageId);

    Double queryInterestRate(@Param("currentStageId")Long currentStageId, @Param("projectName")String projectName);

    ParProjectRateVO queryInterestStageRate(@Param("currentStageId")Long currentStageId, @Param("projectName")String projectName);

    List<ParProjectRateVO> selectRate(@Param("stageId")Long stageId);
}
