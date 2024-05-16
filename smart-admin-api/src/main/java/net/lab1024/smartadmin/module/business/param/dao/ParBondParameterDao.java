package net.lab1024.smartadmin.module.business.param.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParBondParameterQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParBondParameterEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParBondParameterVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParBondParameterExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 债券参数表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:42:33
 * @since JDK1.8
 */
@Mapper
@Component
public interface ParBondParameterDao extends BaseMapper<ParBondParameterEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ParBondParameterVO
    */
    IPage<ParBondParameterVO> queryByPage(Page page, @Param("queryDTO") ParBondParameterQueryDTO queryDTO);

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
    List<ParBondParameterExcelVO> queryAllExportData(@Param("queryDTO")ParBondParameterQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ParBondParameterExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    List<ParBondParameterVO> queryPriceData(@Param("currentStageId")Long currentStageId);
}
