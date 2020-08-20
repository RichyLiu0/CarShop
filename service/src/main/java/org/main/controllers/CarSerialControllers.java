package org.main.controllers;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.carshop.entities.PageRestResult;
import org.carshop.entities.RestResult;
import org.carshop.utilities.ExceptionUnits;
import org.main.biz.CarSerialBiz;
import org.main.controllers.wraper.CarSerialWrapper;
import org.main.enums.RestResultEnum;
import org.main.model.CarSerial;
import org.main.model.CarSerialAttr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


/**

 *
 * @author 刘伟锐
 * @date 2020/08/17
 */
@Api(value = "车系")
@RestController
@RequestMapping(value = "/base/carSerial")
public class CarSerialControllers {

    private static Logger log = LoggerFactory.getLogger(CarSerialControllers.class);

    @Autowired
    private CarSerialBiz biz;


    @ApiOperation(value = "获取列表", notes = "获取列表")
    @RequestMapping(path = "/getList", method = {RequestMethod.GET, RequestMethod.POST})
    public List<CarSerial> getLst() {
        return biz.getLst();
    }



    // 零件查询列表(分页)
    @ApiOperation(value = "零件信息查询列表", notes = "零件信息查询列表")
    @RequestMapping(path = "/getPage", method = {RequestMethod.GET, RequestMethod.POST})
    public PageRestResult<CarSerial> getPartPage(
            @RequestParam(name = "token", required = false) String token,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "desc", required = false) String desc,
            @RequestParam(name = "pageIndex", required = true, defaultValue = "1") Integer pageIndex,
            @RequestParam(name = "pageSize", required = true, defaultValue = "10") Integer pageSize) {


        Page<CarSerial> page = new Page<>(pageIndex, pageSize);
        PageRestResult<CarSerial> rs = biz.getPage(page, name, desc);
        return rs;
    }

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public RestResult add(@RequestBody(required = true) CarSerialWrapper wrapper,
                                @RequestHeader(name = "token", required = false
                                ) String token) {
        RestResult rs = new RestResult();
        try {
            wrapper.setId(UUID.randomUUID().toString());
            if(CollectionUtils.isEmpty( wrapper.getAttrs())==false)
            {
                for(CarSerialAttr attr : wrapper.getAttrs())
                {
                    attr.setId(UUID.randomUUID().toString());
                    attr.setSerialId(wrapper.getId());
                }
            }
            rs = biz.add(wrapper, wrapper.getAttrs());

        } catch (Exception ex) {
            ex.printStackTrace();
            rs.setResult(RestResultEnum.failth.getValue());
            rs.setMsg(ExceptionUnits.getMsg(ex));
        }
        return rs;
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(path = "/udpate", method = RequestMethod.POST)
    public RestResult update(@RequestBody(required = true) CarSerialWrapper wrapper,
                                @RequestHeader(name = "token", required = false
                                ) String token) {
        RestResult rs = new RestResult();
        try {
            rs = biz.update(wrapper, wrapper.getAttrs());

        } catch (Exception ex) {
            ex.printStackTrace();
            rs.setResult(RestResultEnum.failth.getValue());
            rs.setMsg(ExceptionUnits.getMsg(ex));
        }
        return rs;
    }


}