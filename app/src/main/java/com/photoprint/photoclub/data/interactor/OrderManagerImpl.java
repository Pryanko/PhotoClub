package com.photoprint.photoclub.data.interactor;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.network.Response;
import com.photoprint.network.api.ApiWorker;
import com.photoprint.network.api.model.base.SingleData;
import com.photoprint.photoclub.base.DbTransaction;
import com.photoprint.photoclub.data.api.mapper.OrderMapper;
import com.photoprint.photoclub.helper.runtimepermission.AppSchedulers;
import com.photoprint.photoclub.model.Order;
import com.photoprint.photoclub.repository.OrderRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Класс реализация для управлениея состояния заказа
 *
 * @author Grigoriy Pryamov.
 */
public class OrderManagerImpl implements OrderManager {

    private static final Logger logger = LoggerFactory.getLogger(OrderManager.class);

    private final ApiWorker apiWorker;
    private final DbTransaction dbTransaction;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper = OrderMapper.INSTANCE;

    @Inject
    public OrderManagerImpl(ApiWorker apiWorker,
                            DbTransaction dbTransaction,
                            OrderRepository orderRepository) {
        this.apiWorker = apiWorker;
        this.dbTransaction = dbTransaction;
        this.orderRepository = orderRepository;
    }

    @Override
    public Completable create(long serviceId) {
        return apiWorker.createOrder()
                .map(Response::get)
                .map(SingleData::getData)
                .observeOn(AppSchedulers.db())
                .doOnSuccess(order -> dbTransaction.callInTx(() -> {
                    Order dbOrder = orderMapper.entityToModel(order);
                    dbOrder.setServiceId(serviceId);
                    dbOrder.setActive(true);
                    orderRepository.insert(dbOrder);
                    logger.trace("Order " + dbOrder.getId() + " is created");
                }))
                .toCompletable();
    }

    /**
     * Метод Проверяющий на наличие активного закза на текущий момент
     */
    @Override
    public boolean containsActiveOrder() {
        return orderRepository.containsActiveOrder();
    }
}
