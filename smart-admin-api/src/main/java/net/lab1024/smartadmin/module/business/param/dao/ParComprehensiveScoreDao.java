package net.lab1024.smartadmin.module.business.param.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParComprehensiveScoreQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParComprehensiveScoreEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParComprehensiveScoreVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParComprehensiveScoreExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 综合评分指标 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-16 10:39:05
 * @since JDK1.8
 */
@Mapper
@Component
public interface ParComprehensiveScoreDao extends BaseMapper<ParComprehensiveScoreEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ParComprehensiveScoreVO
    */
    IPage<ParComprehensiveScoreVO> queryByPage(Page page, @Param("queryDTO") ParComprehensiveScoreQueryDTO queryDTO);

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
    List<ParComprehensiveScoreExcelVO> queryAllExportData(@Param("queryDTO")ParComprehensiveScoreQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ParComprehensiveScoreExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    Double queryCapitalAdequacy(@Param("capitalAdequacy") Double capitalAdequacy);

    Double queryLiquidityRatioScore(@Param("liquidityRatio") Double liquidityRatio);

    Double queryDepositLoanRatioScore(@Param("depositLoanRatio") Double depositLoanRatio);

    Double queryReturnOnAssetsScore(@Param("returnOnAssets") Double returnOnAssets);

    Double queryReturnOnNetAssetsScore(@Param("returnOnNetAssets") Double returnOnNetAssets);

    Double queryRCostIncomeRatioScore(@Param("costIncomeRatio")Double costIncomeRatio);

    Double queryDividendProportionScore(@Param("dividendProportion")Double dividendProportion);

    Double queryAssetScopeScore(@Param("assetScope")Double assetScope);
}
