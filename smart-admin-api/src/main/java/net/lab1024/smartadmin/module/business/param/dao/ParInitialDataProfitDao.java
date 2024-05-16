package net.lab1024.smartadmin.module.business.param.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParInitialDataProfitQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParInitialDataProfitEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataProfitVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParInitialDataProfitExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 初始数据表-利润 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 13:47:09
 * @since JDK1.8
 */
@Mapper
@Component
public interface ParInitialDataProfitDao extends BaseMapper<ParInitialDataProfitEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ParInitialDataProfitVO
    */
    IPage<ParInitialDataProfitVO> queryByPage(Page page, @Param("queryDTO") ParInitialDataProfitQueryDTO queryDTO);

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
    List<ParInitialDataProfitExcelVO> queryAllExportData(@Param("queryDTO")ParInitialDataProfitQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ParInitialDataProfitExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);
}
