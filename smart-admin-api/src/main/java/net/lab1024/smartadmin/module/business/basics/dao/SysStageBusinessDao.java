package net.lab1024.smartadmin.module.business.basics.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.basics.domain.dto.SysStageBusinessQueryDTO;
import net.lab1024.smartadmin.module.business.basics.domain.entity.SysStageBusinessEntity;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysStageBusinessVO;
import net.lab1024.smartadmin.module.business.basics.domain.vo.SysStageBusinessExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 阶段业务名称 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-30 09:36:32
 * @since JDK1.8
 */
@Mapper
@Component
public interface SysStageBusinessDao extends BaseMapper<SysStageBusinessEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return SysStageBusinessVO
    */
    IPage<SysStageBusinessVO> queryByPage(Page page, @Param("queryDTO") SysStageBusinessQueryDTO queryDTO);

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
    List<SysStageBusinessExcelVO> queryAllExportData(@Param("queryDTO")SysStageBusinessQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<SysStageBusinessExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    List<SysStageBusinessVO> queryAllData();

    SysStageBusinessVO selectBusiness(@Param("stageId")Long stageId);
}
