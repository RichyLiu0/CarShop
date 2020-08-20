package org.main.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.main.model.CarSerial;
import org.main.model.CarType;


@Mapper
public interface CarTypeMapper extends BaseMapper<CarType> {

    IPage<CarType> getPage(Page<CarType> page, @Param("serialId") String serialId , @Param("name") String name, @Param("desc") String desc );

}
