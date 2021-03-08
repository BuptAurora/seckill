package com.aurora.controller;

import com.aurora.domain.MiaoshaUser;
import com.aurora.domain.OrderInfo;
import com.aurora.redis.RedisService;
import com.aurora.result.CodeMsg;
import com.aurora.result.Result;
import com.aurora.service.GoodsService;
import com.aurora.service.MiaoshaUserService;
import com.aurora.service.OrderService;
import com.aurora.vo.GoodsVo;
import com.aurora.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	MiaoshaUserService userService;

	@Autowired
	RedisService redisService;

	@Autowired
	OrderService orderService;

	@Autowired
	GoodsService goodsService;

	@RequestMapping("/detail")
	@ResponseBody
	public Result<OrderDetailVo> info(Model model,MiaoshaUser user,
									  @RequestParam("orderId") long orderId) {
		if(user == null) {
			return Result.error(CodeMsg.SESSION_ERROR);
		}
		OrderInfo order = orderService.getOrderById(orderId);
		if(order == null) {
			return Result.error(CodeMsg.ORDER_NOT_EXIST);
		}
		long goodsId = order.getGoodsId();
		GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
		OrderDetailVo vo = new OrderDetailVo();
		vo.setOrder(order);
		vo.setGoods(goods);
		return Result.success(vo);
	}

}
