package net.lab1024.smartadmin.module.business.param.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataInvestbusBondQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParInitialDataInvestbusBondEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataInvestbusBondExcelVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataInvestbusBondVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 初始数据表-投资业务营业数据表(债券) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 14:03:28
 * @since JDK1.8
 */
@Mapper
@Component
public interface ParInitialDataInvestbusBondDao extends BaseMapper<ParInitialDataInvestbusBondEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ParInitialDataInvestbusBondVO
    */
    IPage<ParInitialDataInvestbusBondVO> queryByPage(Page page, @Param("queryDTO") ParInitialDataInvestbusBondQueryDTO queryDTO);

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
    List<ParInitialDataInvestbusBondExcelVO> queryAllExportData(@Param("queryDTO")ParInitialDataInvestbusBondQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ParInitialDataInvestbusBondExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    ParInitialDataInvestbusBondVO selectInvestbusOperatBondData(@Param("currentStageId")Long currentStageId, @Param("bondKind")String bondKind, @Param("bondType")String bondType);
}
