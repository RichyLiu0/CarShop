package org.main.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.main.model.CarSerial;


@Mapper
public interface CarSerialMapper extends BaseMapper<CarSerial> {
    // 查询并分页
    IPage<CarSerial> getPage(Page<CarSerial> page, @Param("name") String name, @Param("desc") String desc );

}
