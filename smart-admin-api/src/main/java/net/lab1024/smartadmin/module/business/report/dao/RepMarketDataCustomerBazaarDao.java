package net.lab1024.smartadmin.module.business.report.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepMarketDataCustomerBazaarQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepMarketDataCustomerBazaarEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepMarketDataCustomerBazaarVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepMarketDataCustomerBazaarExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 市场营销数据(客户市场)单位：百万元 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 11:26:24
 * @since JDK1.8
 */
@Mapper
@Component
public interface RepMarketDataCustomerBazaarDao extends BaseMapper<RepMarketDataCustomerBazaarEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return RepMarketDataCustomerBazaarVO
    */
    IPage<RepMarketDataCustomerBazaarVO> queryByPage(Page page, @Param("queryDTO") RepMarketDataCustomerBazaarQueryDTO queryDTO);

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
    List<RepMarketDataCustomerBazaarExcelVO> queryAllExportData(@Param("queryDTO")RepMarketDataCustomerBazaarQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<RepMarketDataCustomerBazaarExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    List<RepMarketDataCustomerBazaarVO> selectMarDataCusBazPar(@Param("currentStageId") Long currentStageId);

    List<RepMarketDataCustomerBazaarVO> selectMarDataCusBazRep(@Param("currentTeamId")Long currentTeamId,@Param("currentStageId") Long currentStageId);

    List<RepMarketDataCustomerBazaarExcelVO> queryExportData(@Param("currentProjectId")Long proId,@Param("currentStageId") Long stageId);
}
