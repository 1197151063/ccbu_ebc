package net.lab1024.smartadmin.module.business.param.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParWastageRateQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParWastageRateEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParWastageRateVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParWastageRateExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 人事后勤信息录入 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-08 16:05:49
 * @since JDK1.8
 */
@Mapper
@Component
public interface ParWastageRateDao extends BaseMapper<ParWastageRateEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ParWastageRateVO
    */
    IPage<ParWastageRateVO> queryByPage(Page page, @Param("queryDTO") ParWastageRateQueryDTO queryDTO);

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
    List<ParWastageRateExcelVO> queryAllExportData(@Param("queryDTO")ParWastageRateQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ParWastageRateExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    List<ParWastageRateVO> queryData(@Param("currentStageId")Long currentStageId);
}
