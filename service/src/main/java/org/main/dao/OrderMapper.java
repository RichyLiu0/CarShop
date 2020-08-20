package org.main.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.main.model.CarType;
import org.main.model.Order;


@Mapper
public interface OrderMapper extends BaseMapper<Order> {


}
