package net.lab1024.smartadmin.module.business.teacher.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.module.business.teacher.domain.dto.TMessageQueryDTO;
import net.lab1024.smartadmin.module.business.teacher.domain.entity.TMessageEntity;
import net.lab1024.smartadmin.module.business.teacher.domain.vo.TMessageExcelVO;
import net.lab1024.smartadmin.module.business.teacher.domain.vo.TMessageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * [ 消息管理表 ]
 *
 * @author SMS
 * @version 1.0
 * @company SMS
 * @copyright (c)  SMSInc. All rights reserved.
 * @date 2022-01-06 09:46:05
 * @since JDK1.8
 */
@Mapper
@Component
public interface TMessageDao extends BaseMapper<TMessageEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return TMessageVO
    */
    IPage<TMessageVO> queryByPage(Page page, @Param("queryDTO") TMessageQueryDTO queryDTO,@Param("messageType") Integer messageType);

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
    List<TMessageExcelVO> queryAllExportData(@Param("queryDTO")TMessageQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<TMessageExcelVO> queryBatchExportData(@Param("idList")List<Long> idList);

    TMessageVO selectBy(@Param("messageName")String messageName, @Param("content")String content,@Param("createTime") Date createTime);

    List<TMessageEntity> queryMessageData();

    TMessageEntity queryData(@Param("id")Long id);
}
