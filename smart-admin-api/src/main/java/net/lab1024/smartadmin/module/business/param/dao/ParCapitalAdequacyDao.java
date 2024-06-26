package net.lab1024.smartadmin.module.business.param.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParCapitalAdequacyQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParCapitalAdequacyEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParCapitalAdequacyVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParCapitalAdequacyExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 资本充足率-风险加权系数% ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-05 11:21:36
 * @since JDK1.8
 */
@Mapper
@Component
public interface ParCapitalAdequacyDao extends BaseMapper<ParCapitalAdequacyEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ParCapitalAdequacyVO
    */
    IPage<ParCapitalAdequacyVO> queryByPage(Page page, @Param("queryDTO") ParCapitalAdequacyQueryDTO queryDTO);

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
    List<ParCapitalAdequacyExcelVO> queryAllExportData(@Param("queryDTO")ParCapitalAdequacyQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ParCapitalAdequacyExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    ParCapitalAdequacyVO query();
}
