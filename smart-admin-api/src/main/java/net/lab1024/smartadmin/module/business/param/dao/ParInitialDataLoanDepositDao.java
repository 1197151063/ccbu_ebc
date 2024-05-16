package net.lab1024.smartadmin.module.business.param.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataLoanDepositQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParInitialDataLoanDepositEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataLoanDepositVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataLoanDepositExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 初始内容表-资产负债 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:46:26
 * @since JDK1.8
 */
@Mapper
@Component
public interface ParInitialDataLoanDepositDao extends BaseMapper<ParInitialDataLoanDepositEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ParInitialDataLoanDepositVO
    */
    IPage<ParInitialDataLoanDepositVO> queryByPage(Page page, @Param("queryDTO") ParInitialDataLoanDepositQueryDTO queryDTO);

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
    List<ParInitialDataLoanDepositExcelVO> queryAllExportData(@Param("queryDTO")ParInitialDataLoanDepositQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ParInitialDataLoanDepositExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);
}
