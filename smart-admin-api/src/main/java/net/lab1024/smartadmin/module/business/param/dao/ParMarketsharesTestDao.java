package net.lab1024.smartadmin.module.business.param.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.lab1024.smartadmin.module.business.param.domain.dto.ParMarketsharesTestQueryDTO;
import net.lab1024.smartadmin.module.business.param.domain.entity.ParMarketsharesTestEntity;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParMarketsharesTestVO;
import net.lab1024.smartadmin.module.business.param.domain.vo.ParMarketsharesTestExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 各团队各阶段市场份额(开发阶段测试数据用:共6个团队，数据按六个团队计算) ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2021-11-25 14:04:54
 * @since JDK1.8
 */
@Mapper
@Component
public interface ParMarketsharesTestDao extends BaseMapper<ParMarketsharesTestEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ParMarketsharesTestVO
    */
    IPage<ParMarketsharesTestVO> queryByPage(Page page, @Param("queryDTO") ParMarketsharesTestQueryDTO queryDTO);

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
    List<ParMarketsharesTestExcelVO> queryAllExportData(@Param("queryDTO")ParMarketsharesTestQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ParMarketsharesTestExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);
}
