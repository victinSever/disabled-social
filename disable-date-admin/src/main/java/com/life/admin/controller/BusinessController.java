package com.life.admin.controller;

import com.life.admin.common.R;
import com.life.admin.common.Save;
import com.alibaba.fastjson.JSONObject;
import com.life.admin.pojo.*;
import com.life.admin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * 业务管理模块
 *      广告管理
 *      广告位管理
 *      活动管理
 *      节日庆典
 *      VIP充值
 */

//@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    private PaymentInfoService paymentInfoService;

    @Autowired
    private VipManageService vipManageService;

    @Autowired
    private IAdvertisingService advertisingService;

    @Autowired
    private IAdvertiseplaceService advertiseplaceService;

    @Autowired
    private IActivityService activityService;

    @Autowired
    private IFestivalService festivalService;

    @Autowired
    private ChildrentypesService childrentypesService;

    @Autowired
    private Save save;

    /**
     * 广告的总页数
     * @param size
     * @return
     */
    @GetMapping("/getAdvertisingPages")
    public Integer getAdvertisingPages(@RequestParam("size") Integer size){
        System.out.println(advertisingService.list().size());
        Integer pages = ( advertisingService.list().size() + size -1 ) / size;
        System.out.println("共"+pages+"页");
        return pages;
    }

    /**
     * 广告位的总页数
     * @param size
     * @return
     */
    @GetMapping("/getAdvertiseplacePages")
    public Integer getAdvertiseplacePages(@RequestParam("size") Integer size){
        Integer pages = ( advertiseplaceService.list().size() + size -1 ) / size;
        System.out.println("共"+pages+"页");
        return pages;
    }

    /**
     * 节日的总页数
     * @param size
     * @return
     */
    @GetMapping("/getFestivalPages")
    public Integer getFestivalPages(@RequestParam("size") Integer size){
        Integer pages = ( festivalService.list().size() + size -1 ) / size;
        System.out.println("共"+pages+"页");
        return pages;
    }

    /**
     * 活动的总页数
     * @param size
     * @return
     */
    @GetMapping("/getActivityPages")
    public Integer getActivityPages(@RequestParam("size") Integer size){
        Integer pages = ( activityService.list().size() + size -1 ) / size;
        System.out.println("共"+pages+"页");
        return pages;
    }

    /**
     * 获取广告的列表（按照序号进行升序查询）
     */
    @GetMapping("/getAdvertisingList")
    public JSONObject getAdvertisingList(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        Integer index = (page-1)*size;
//        Integer pages = ( advertisingService.list().size() + size -1 ) / size;
        Integer total =advertisingService.list().size();
        List<Advertising> list = advertisingService.getAdvertisingList(index,size);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",total);
        jsonObject.put("list",list);
        System.out.println(jsonObject);
        return jsonObject;
    }

    /**
     * 获取广告位的列表（按照序号进行升序查询）
     */
    @GetMapping("/getAdvertiseplaceList")
    public JSONObject getAdvertiseplaceList(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        Integer total = advertiseplaceService.list().size();
        Integer index = (page-1)*size;
        List<Advertiseplace> list = advertiseplaceService.getAdvertiseplaceList(index,size);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",total);
        jsonObject.put("list",list);
        return jsonObject;
    }

    /**
     * 获取节日的列表（按照序号进行升序查询）
     */
    @GetMapping("/getFestivalList")
    public JSONObject getFestivalList(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        Integer total = festivalService.list().size();
        Integer index = (page-1)*size;
        List<Festival> list = festivalService.getFestivalList(index,size);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",total);
        jsonObject.put("list",list);
        return jsonObject;
    }


    /**
     * 获取活动的列表（按照序号进行升序查询）
     */
    @GetMapping("/getActivityList")
    public JSONObject getActivityList(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        Integer total = activityService.list().size();
        Integer index = (page-1)*size;
        List<Activity> list = activityService.getActivityList(index,size);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("toal",total);
        jsonObject.put("list",list);
        return jsonObject;
    }

    @GetMapping("/getChildren")
    public List<Childrentypes> getChildren(@RequestParam("type") String type){
        List<Childrentypes> list = childrentypesService.getChildrenByParentName(type);
        return list;
    }

    /**
     * 添加广告
     */
    @PostMapping("/addAdvertising")
//    public boolean addActivity(JSONObject jsonObject){
    public boolean addAdvertising(@RequestBody Advertising advertising){
        try {
            advertising.setId(null);
            System.out.println("嘿嘿嘿 addAdvertising 来添加广告啦");
            System.out.println("图片链接"+advertising.getAdvertisingLinks());
//            String picName = UUID.randomUUID()+".jpg";  //给照片文件命名
//            String path = save.savePicture((File) picture,picName); //存到服务器中并返回路径
//            advertising.setAppearencePath(path);  //将路径存进实体类中
            boolean flag = advertisingService.save(advertising);  //整个实体类插进数据库表中
            System.out.println(flag);
            return flag;
//            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加广告位
     */
    @PostMapping("/addAdvertiseplace")
    @ResponseBody
    public boolean addAdvertiseplace(@RequestBody Advertiseplace advertiseplace){
        boolean flag = advertiseplaceService.save(advertiseplace);
        System.out.println(flag);
        return flag;
    }

    /**
     * 添加活动
     */
    @PostMapping("/addActivity")
    @ResponseBody
    public boolean addActivity(@RequestBody Activity activity){
        System.out.println("嘿嘿嘿 addActivity 来添加活动啦");
        String picName = UUID.randomUUID()+".jpg";  //给照片文件命名
//            String path = save.savePicture((File) picture,picName); //存到服务器中并返回路径
//            activity.setCoverPath(path);  //将路径存进实体类中
        boolean flag = activityService.save(activity);
        System.out.println(flag);
        return flag;
    }

    /**
     * 添加节日庆典
     * @param festival
     * @return
     */
    @PostMapping("/addFestival")
    public Boolean addFestival(@RequestBody Festival festival){
        boolean flag = festivalService.save(festival);
        return flag;
    }

    /**
     * 删除广告
     */
    @DeleteMapping("/deleteAdvertsing")
    public boolean deleteAdvertsing(@RequestParam("id")Integer id){
        boolean flag = advertisingService.removeById(id);
        System.out.println("删除成功");
        return flag;
    }

    /**
     * 删除广告位
     */
    @DeleteMapping("/deleteAdvertsingPlace")
    public boolean deleteAdvertsingPlace(@RequestParam("id")Integer id){
        boolean flag = advertiseplaceService.removeById(id);
        System.out.println("删除成功");
        return flag;
    }

    /**
     * 删除活动
     */
    @DeleteMapping("/deleteActivity")
    public boolean deleteActivity(@RequestParam("id")Integer id){
        boolean flag = activityService.removeById(id);
        System.out.println("删除成功");
        return flag;
    }

    /**
     *  删除节日庆典
     */
    @DeleteMapping("/deleteFestival")
    public boolean deleteFestival(@RequestParam("id")Integer id){
        boolean flag = festivalService.removeById(id);
        System.out.println("删除成功");
        return flag;
    }

    /**
     * 修改广告
     * @param advertising
     * @return
     */
    @PutMapping("/changeAdvertising")
    public R changeAdvertising(@RequestBody Advertising advertising){
        System.out.println("嘿嘿嘿 changeAdvertising 来修改 广告了");
        R result = new R();
        boolean f = advertisingService.updateById(advertising);
        if(f) {
            result.setCode(1);
            result.setMsg("修改成功");
        }
        else {
            result.setData(0);
            result.setMsg("修改失败");
        }
        return result;

    }

    /**
     * 修改特权管理的vip功能开放状态
     */
    @PutMapping("/changeVipStatus")
    public boolean changeVipStatus(@RequestParam("name") String name,@RequestParam("status") Integer status){
        vipManageService.changeVipStatus(name,status);
        return true;
    }

    /**
     * 获取vip特权的开放标识
     */
    @GetMapping("/getVipFunctionStatus")
    public JSONObject getVipFunctionStatus(){
        JSONObject jsonObject = new JSONObject();
        List<VipManage> list = vipManageService.list();
        for(VipManage vipManage : list){
            String key = vipManage.getFunctionName();
            jsonObject.put(key ,vipManage.getStatus());
        }
        return jsonObject;
    }

    /**
     * 支付订单的查询列表
     */
    @GetMapping("/getPaymentInfoList")
    public JSONObject getPaymentInfoList(@RequestParam("page") Integer page,@RequestParam("size") Integer size){
        JSONObject jsonObject = new JSONObject();
        Integer pages = (paymentInfoService.list().size() + size - 1)/size;
        Integer index = (page - 1) * size;
        List<PaymentInfo> list = paymentInfoService.getPaymentInfoList(index,size);
        jsonObject.put("total",pages);
        jsonObject.put("payment",list);
        return jsonObject;
    }

    /**
     * 删除支付订单
     */
    @DeleteMapping("/deletePaymentInfo")
    public boolean getPaymentInfoList(@RequestParam("orderId") Integer orderId){
        System.out.println(orderId);
        boolean flag = paymentInfoService.deletePaymentInfo(orderId);
        return flag;
    }

}
