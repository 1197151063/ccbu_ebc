package net.lab1024.smartadmin.module.business.param.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParMarketShareQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParMarketShareEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParMarketShareVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParMarketShareExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 市场份额 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-12-13 20:06:33
 * @since JDK1.8
 */
@Mapper
@Component
public interface ParMarketShareDao extends BaseMapper<ParMarketShareEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ParMarketShareVO
    */
    IPage<ParMarketShareVO> queryByPage(Page page, @Param("queryDTO") ParMarketShareQueryDTO queryDTO);

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
    List<ParMarketShareExcelVO> queryAllExportData(@Param("queryDTO")ParMarketShareQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ParMarketShareExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    List<ParMarketShareExcelVO> queryExportData(@Param("currentProjectId")Long proId,@Param("currentStageId") Long currentStageId);
}
