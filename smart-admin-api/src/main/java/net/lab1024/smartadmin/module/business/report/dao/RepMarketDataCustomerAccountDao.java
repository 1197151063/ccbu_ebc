package net.lab1024.smartadmin.module.business.report.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.report.domain.dto.RepMarketDataCustomerAccountQueryDTO;
import net.lab1024.smartadmin.module.business.report.domain.entity.RepMarketDataCustomerAccountEntity;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepMarketDataCustomerAccountVO;
import net.lab1024.smartadmin.module.business.report.domain.vo.RepMarketDataCustomerAccountExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 市场营销数据(客户账户) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 11:25:07
 * @since JDK1.8
 */
@Mapper
@Component
public interface RepMarketDataCustomerAccountDao extends BaseMapper<RepMarketDataCustomerAccountEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return RepMarketDataCustomerAccountVO
    */
    IPage<RepMarketDataCustomerAccountVO> queryByPage(Page page, @Param("queryDTO") RepMarketDataCustomerAccountQueryDTO queryDTO);

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
    List<RepMarketDataCustomerAccountExcelVO> queryAllExportData(@Param("queryDTO")RepMarketDataCustomerAccountQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<RepMarketDataCustomerAccountExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    List<RepMarketDataCustomerAccountVO> selectMarDatCusAccPar(@Param("currentStageId") Long currentStageId);

    List<RepMarketDataCustomerAccountVO> selectMarDatCusAccRep(@Param("currentTeamId")Long currentTeamId,@Param("currentStageId") Long currentStageId);

    List<RepMarketDataCustomerAccountExcelVO> queryExportData(@Param("currentProjectId")Long proId,@Param("currentStageId") Long stageId);
}
