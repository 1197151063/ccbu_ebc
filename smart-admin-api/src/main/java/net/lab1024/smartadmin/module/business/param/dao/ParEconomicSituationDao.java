package net.lab1024.smartadmin.module.business.param.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParEconomicSituationQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParEconomicSituationEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParEconomicSituationVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParEconomicSituationExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 经济形势分析报告 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-13 13:18:35
 * @since JDK1.8
 */
@Mapper
@Component
public interface ParEconomicSituationDao extends BaseMapper<ParEconomicSituationEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ParEconomicSituationVO
    */
    IPage<ParEconomicSituationVO> queryByPage(Page page, @Param("queryDTO") ParEconomicSituationQueryDTO queryDTO);

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
    List<ParEconomicSituationExcelVO> queryAllExportData(@Param("queryDTO")ParEconomicSituationQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ParEconomicSituationExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    ParEconomicSituationVO queryCurrentStageEconomy(@Param("currentStageId")Long currentStageId);
}
