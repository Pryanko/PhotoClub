package com.photoprint.photoclub.impl;

import com.photoprint.photoclub.base.AppDatabase;
import com.photoprint.photoclub.impl.base.BaseRepositoryImpl;
import com.photoprint.photoclub.model.Order;
import com.photoprint.photoclub.repository.OrderRepository;
import com.photoprint.photoclub.room.dao.OrderDao;
import com.photoprint.photoclub.room.entity.OrderEntity;
import com.photoprint.photoclub.room.mapper.OrderMapper;

import java.util.List;

import javax.inject.Singleton;

/**
 * @author Grigoriy Pryamov.
 */
@Singleton
public class OrderRepositoryImpl extends BaseRepositoryImpl<Order, OrderEntity, Long> implements OrderRepository {

    private final OrderMapper mapper;

    protected OrderRepositoryImpl(AppDatabase appDatabase,
                                  OrderMapper mapper) {
        super(appDatabase);
        this.mapper = mapper;
    }

    @Override
    protected OrderMapper mapper() {
        return mapper;
    }

    @Override
    protected OrderDao dao() {
        return appDatabase.orderDao();
    }

    @Override
    public List<Order> getOrders() {
        return mapper.entityListToModelList(dao().getOrders());
    }

    @Override
    public Long getCountOrders() {
        return dao().getCountOrders();
    }
}
