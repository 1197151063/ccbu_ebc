package net.lab1024.smartadmin.module.business.param.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParPropertyLiabilityRateQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParPropertyLiabilityRateEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParPropertyLiabilityRateVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParPropertyLiabilityRateExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 资产负债利率表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:52:53
 * @since JDK1.8
 */
@Mapper
@Component
public interface ParPropertyLiabilityRateDao extends BaseMapper<ParPropertyLiabilityRateEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ParPropertyLiabilityRateVO
    */
    IPage<ParPropertyLiabilityRateVO> queryByPage(Page page, @Param("queryDTO") ParPropertyLiabilityRateQueryDTO queryDTO);

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
    List<ParPropertyLiabilityRateExcelVO> queryAllExportData(@Param("queryDTO")ParPropertyLiabilityRateQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ParPropertyLiabilityRateExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    List<ParPropertyLiabilityRateVO> queryAfter(@Param("afterCurrentStageId")Long afterCurrentStageId);

    List<ParPropertyLiabilityRateVO> selectRate(@Param("stageId")Long stageId);
}
