package org.main.biz;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.carshop.entities.PageRestResult;
import org.carshop.entities.RestResult;
import org.carshop.utilities.ExceptionUnits;
import org.main.dao.*;
import org.main.enums.RestResultEnum;
import org.main.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;


@Component
public class OrderBiz extends ServiceImpl<OrderMapper, Order> {

    @Autowired
    private OrderMapper mapper;

    @Autowired
    private OrderConfigMapper cfgMapper;

    @Autowired
    private OrderOperateMapper operateMapper;

    public List<Order> getLst() {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        return mapper.selectList(wrapper);
    }

    public RestResult add (Order order, List<OrderConfig> cfgs)
    {
        RestResult rs =new RestResult(RestResultEnum.sucess.getValue(),"成功");

        try {
            mapper.insert(order);
            if (CollectionUtils.isEmpty(cfgs) != true) {
                for (OrderConfig cfg : cfgs) {
                    cfgMapper.insert(cfg);
                }
            }
        }
        catch (Exception ex)
        {
            rs.setResult(RestResultEnum.failth.getValue());
            rs.setMsg(ExceptionUnits.getMsg(ex));
        }
        return rs;
    }

    public RestResult update (Order order, List<OrderConfig> cfgs)
    {
        RestResult rs =new RestResult(RestResultEnum.sucess.getValue(),"成功");

        try {
            mapper.updateById(order);

            if (CollectionUtils.isEmpty(cfgs) != true) {

                QueryWrapper<OrderConfig> wrapper=new QueryWrapper<>();
                wrapper.eq("order_id",order.getId());
                List<OrderConfig> delAttrs= cfgMapper.selectList(wrapper);

                //删除
                for( OrderConfig  cfg: delAttrs)
                {
                    cfgMapper.deleteById(cfg);
                }

                //添加
                for (OrderConfig cfg : cfgs) {
                    cfgMapper.insert(cfg);
                }
            }
        }
        catch (Exception ex)
        {
            rs.setResult(RestResultEnum.failth.getValue());
            rs.setMsg(ExceptionUnits.getMsg(ex));
        }
        return rs;
    }


    public RestResult operate (String orderId,OrderOperate operate)
    {
        RestResult rs =new RestResult(RestResultEnum.sucess.getValue(),"成功");

        try {
            Order en =mapper.selectById(orderId);
            en.setUpdateTime(operate.getOperatorDate());
            en.setOrderStatus(operate.getOperatorType());

            operateMapper.insert(operate);
            mapper.updateById(en);
        }
        catch (Exception ex)
        {
            rs.setResult(RestResultEnum.failth.getValue());
            rs.setMsg(ExceptionUnits.getMsg(ex));
        }
        return rs;
    }

    public PageRestResult<Order> getPage(Page<Order> page, String orderNo, String serialId , String carTypeId, String desc) {
        PageRestResult<Order> rs=new PageRestResult();

        QueryWrapper<Order> wrapper =new QueryWrapper<>();
        if(StringUtils.isEmpty(orderNo)!=true)
        {
            wrapper.eq("order_no",orderNo);
        }

        if(StringUtils.isEmpty(serialId)!=true) {
            wrapper.eq("serial_id", serialId);
        }
        if(StringUtils.isEmpty(carTypeId)!=true) {
            wrapper.eq("car_type_id", carTypeId);
        }
        if(StringUtils.isEmpty(desc)!=true) {
            wrapper.like("decription", desc);
        }


        IPage<Order> list= mapper.selectPage(page,wrapper);
        if (list.getSize()==0) {
            rs.setResult(RestResultEnum.failth.getValue());
            rs.setMsg("获取失败");
        }else {
            rs.setResult(RestResultEnum.sucess.getValue());
            rs.setMsg("获取成功");
            rs.filPage(list); //数据赋值
        }
        return rs;
    }



}
