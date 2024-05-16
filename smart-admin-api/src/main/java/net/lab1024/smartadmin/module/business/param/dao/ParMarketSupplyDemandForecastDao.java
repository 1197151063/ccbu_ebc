package net.lab1024.smartadmin.module.business.param.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParMarketSupplyDemandForecastQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParMarketSupplyDemandForecastEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParMarketSupplyDemandForecastVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParMarketSupplyDemandForecastExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 市场供求预测表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:49:31
 * @since JDK1.8
 */
@Mapper
@Component
public interface ParMarketSupplyDemandForecastDao extends BaseMapper<ParMarketSupplyDemandForecastEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ParMarketSupplyDemandForecastVO
    */
    IPage<ParMarketSupplyDemandForecastVO> queryByPage(Page page, @Param("queryDTO") ParMarketSupplyDemandForecastQueryDTO queryDTO);

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
    List<ParMarketSupplyDemandForecastExcelVO> queryAllExportData(@Param("queryDTO")ParMarketSupplyDemandForecastQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ParMarketSupplyDemandForecastExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    List<ParMarketSupplyDemandForecastVO> query(@Param("currentStageId")Long currentStageId);
}
