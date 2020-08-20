package org.main.controllers;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.carshop.entities.PageRestResult;
import org.carshop.entities.RestResult;
import org.carshop.utilities.ExceptionUnits;
import org.main.biz.CarSerialBiz;
import org.main.biz.OrderBiz;
import org.main.controllers.wraper.CarSerialWrapper;
import org.main.controllers.wraper.OrderWrapper;
import org.main.enums.RestResultEnum;
import org.main.model.CarSerial;
import org.main.model.CarSerialAttr;
import org.main.model.Order;
import org.main.model.OrderConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 *
 * @author 刘伟锐
 * @date 2020/08/20
 */
@Api(value = "订单")
@RestController
@RequestMapping(value = "/bus/order")
public class OrderControllers {

    private static Logger log = LoggerFactory.getLogger(OrderControllers.class);

    @Autowired
    private OrderBiz biz;


    @ApiOperation(value = "获取列表", notes = "获取列表")
    @RequestMapping(path = "/getList", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Order> getLst() {
        return biz.getLst();
    }



    // 零件查询列表(分页)
    @ApiOperation(value = "订单查询列表", notes = "订单信息查询列表")
    @RequestMapping(path = "/getPage", method = {RequestMethod.GET, RequestMethod.POST})
    public PageRestResult<Order> getPartPage(
            @RequestParam(name = "token", required = false) String token,
            @RequestParam(name = "orderNo", required = false) String orderNo,
            @RequestParam(name = "serialId", required = false) String serialId,
            @RequestParam(name = "carTypeId", required = false) String carTypeId,
            @RequestParam(name = "desc", required = false) String desc,
            @RequestParam(name = "pageIndex", required = true, defaultValue = "1") Integer pageIndex,
            @RequestParam(name = "pageSize", required = true, defaultValue = "10") Integer pageSize) {


        Page<Order> page = new Page<>(pageIndex, pageSize);
        PageRestResult<Order> rs = biz.getPage(page,orderNo,serialId,carTypeId,desc);
        return rs;
    }

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public RestResult add(@RequestBody(required = true) OrderWrapper wrapper,
                                @RequestHeader(name = "token", required = false
                                ) String token) {
        RestResult rs = new RestResult();
        try {
            wrapper.setId(UUID.randomUUID().toString());
            wrapper.setOrderDate(new Date());

            if(CollectionUtils.isEmpty( wrapper.getCfgs())==false)
            {
                for(OrderConfig cfg : wrapper.getCfgs())
                {
                    cfg.setId(UUID.randomUUID().toString());
                    cfg.setOrderId(wrapper.getId());
                }
            }
            rs = biz.add(wrapper, wrapper.getCfgs());

        } catch (Exception ex) {
            ex.printStackTrace();
            rs.setResult(RestResultEnum.failth.getValue());
            rs.setMsg(ExceptionUnits.getMsg(ex));
        }
        return rs;
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(path = "/udpate", method = RequestMethod.POST)
    public RestResult update(@RequestBody(required = true) OrderWrapper wrapper,
                                @RequestHeader(name = "token", required = false
                                ) String token) {
        RestResult rs = new RestResult();
        try {
            rs = biz.update(wrapper, wrapper.getCfgs());

            if(CollectionUtils.isEmpty( wrapper.getCfgs())==false)
            {
                for(OrderConfig cfg : wrapper.getCfgs())
                {
                    cfg.setId(UUID.randomUUID().toString());
                    cfg.setOrderId(wrapper.getId());
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            rs.setResult(RestResultEnum.failth.getValue());
            rs.setMsg(ExceptionUnits.getMsg(ex));
        }
        return rs;
    }


}