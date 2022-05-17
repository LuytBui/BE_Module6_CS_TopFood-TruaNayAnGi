package com.codegym.service.cart_detail;

import com.codegym.model.entity.Cart;
import com.codegym.model.entity.CartDetail;
import com.codegym.service.IGeneralService;

public interface ICartDetailService extends IGeneralService<CartDetail> {
    Iterable<CartDetail> findAllByCart(Cart cart);
}
