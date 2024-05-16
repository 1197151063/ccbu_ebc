package net.lab1024.smartadmin.module.business.param.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParStockCalculationIndexQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParStockCalculationIndexEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParStockCalculationIndexVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParStockCalculationIndexExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 股价计划指标 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-17 19:09:54
 * @since JDK1.8
 */
@Mapper
@Component
public interface ParStockCalculationIndexDao extends BaseMapper<ParStockCalculationIndexEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ParStockCalculationIndexVO
    */
    IPage<ParStockCalculationIndexVO> queryByPage(Page page, @Param("queryDTO") ParStockCalculationIndexQueryDTO queryDTO);

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
    List<ParStockCalculationIndexExcelVO> queryAllExportData(@Param("queryDTO")ParStockCalculationIndexQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ParStockCalculationIndexExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    //@Param("")
    Double queryCapitalAdequacy(@Param("capitalAdequacy")Double capitalAdequacy);

    Double queryLiquidityRatioScore(@Param("liquidityRatio")Double liquidityRatio);

    Double queryReturnOnNetAssetsScore(@Param("returnOnNetAssets")Double returnOnNetAssets);

    Double queryDividendProportionScore(@Param("dividendProportion")Double dividendProportion);
}
