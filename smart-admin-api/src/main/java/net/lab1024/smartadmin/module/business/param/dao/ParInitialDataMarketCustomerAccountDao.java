package net.lab1024.smartadmin.module.business.param.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataMarketCustomerAccountQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParInitialDataMarketCustomerAccountEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataMarketCustomerAccountVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataMarketCustomerAccountExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 初始数据表-市场营销数据(客户账户) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-02 16:38:35
 * @since JDK1.8
 */
@Mapper
@Component
public interface ParInitialDataMarketCustomerAccountDao extends BaseMapper<ParInitialDataMarketCustomerAccountEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ParInitialDataMarketCustomerAccountVO
    */
    IPage<ParInitialDataMarketCustomerAccountVO> queryByPage(Page page, @Param("queryDTO") ParInitialDataMarketCustomerAccountQueryDTO queryDTO);

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
    List<ParInitialDataMarketCustomerAccountExcelVO> queryAllExportData(@Param("queryDTO")ParInitialDataMarketCustomerAccountQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ParInitialDataMarketCustomerAccountExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    ParInitialDataMarketCustomerAccountVO selectMarDataCusBazPar(@Param("currentStageId")Long currentStageId,@Param("customerMarketBusinessData") String customerMarketBusinessData);
}
