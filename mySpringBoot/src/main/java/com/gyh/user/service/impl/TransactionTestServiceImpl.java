package com.gyh.user.service.impl;

import com.gyh.base.exception.GyhException;
import com.gyh.user.bean.GyhUser;
import com.gyh.user.mapper.UserMapper;
import com.gyh.user.service.TransactionTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * service
 *
 * @author gyh
 */
@Service
public class TransactionTestServiceImpl implements TransactionTestService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 使用spring容器中的transactionTestService对象调用方法，可以再次进去aop拦截，因为transactionTestService是aop代理之后的对象
     * 使用this调用或者直接调用方法，使用的是没有代理的原始对象，不会进入aop拦截
     */
    @Autowired(required = false)
    private TransactionTestService transactionTestService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void rollbackForException(int type) {
        GyhUser user = new GyhUser("小红_" + type);
        userMapper.saveUser(user);

        switch (type) {
            case 1:
                // 1.在当前事务里抛出一个异常，事务回滚
                throwAnExceptionNoTransaction();
                break;
            case 2:
                // 2.在当前事务里catch一个异常，并没有抛出其他异常，事务提交
                try {
                    throwAnExceptionNoTransaction();
                } catch (Exception e) {
                    System.out.println("i catch a throwAnExceptionNoTransaction ...");
                }
                break;
            case 3:
                // 3.进入另一个service并加入了当前的事务里，并抛出一个异常，整个事务回滚（两个service里的方法都回滚）
                // 使用transactionTestService对象调用，会再次走事务拦截器。
//                throwAnExceptionWithYouTransaction();
                transactionTestService.throwAnExceptionWithYouTransaction();
                break;
            case 4:
                // 4.进入另一个service并加入了当前的事务里，并抛出一个异常，此时事务被标记为回滚状态，
                // 即使catch住此异常，当service结束提交时，检查事务状态，仍然会整个事务回滚（两个service里的方法都回滚）
                try {
                    // 使用transactionTestService对象调用，会再次走事务拦截器。
                    transactionTestService.throwAnExceptionWithYouTransaction();
                    // 使用this或直接调用只经过一个事务拦截，即使throwAnExceptionWithYouTransaction配置了事务拦截，也不会拦截到，因为也不会将事务标记为回滚状态，
                    // 此时异常被catch，两个方法里的sql都会正常提交，从而产生错误，不能这样使用
                    // throwAnExceptionWithYouTransaction();
                } catch (Exception e) {
                    System.out.println("i catch a throwAnExceptionWithYouTransaction ...");
                }
                break;
            case 14:
                // 14.进入另一个service并加入了当前的事务里，没有异常，正常提交，
                // 使用transactionTestService对象调用，会再次走事务拦截器。
                transactionTestService.noExceptionWithYouTransaction();
                break;
            case 5:
                // 5.进入另一个service开启了一个新事务，旧事务被挂起，不会影响旧事务的状态，
                // 新事务抛出异常，新事务回滚；异常抛到上一层，旧事务也回滚
                transactionTestService.throwAnExceptionWithNewTransaction();
                break;
            case 6:
                // 6.进入另一个service开启了一个新事务，旧事务被挂起，不会影响旧事务的状态，
                // 新事务抛出异常，新事务回滚；异常抛到上一层，上一层catch住异常，旧事务提交
                try {
                    transactionTestService.throwAnExceptionWithNewTransaction();
                } catch (Exception e) {
                    System.out.println("i catch a throwAnExceptionWithNewTransaction ...");
                }
                break;
            default:
                System.out.println("worry type");
                break;
        }
    }


    // Another Service
    @Override
    public void throwAnExceptionNoTransaction() {
        throw new GyhException();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void throwAnExceptionWithYouTransaction() {
        GyhUser user = new GyhUser("throwAnExceptionWithYouTransaction");
        userMapper.saveUser(user);
        throw new GyhException();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void noExceptionWithYouTransaction() {
        GyhUser user = new GyhUser("noExceptionWithYouTransaction");
        userMapper.saveUser(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void throwAnExceptionWithNewTransaction() {
        GyhUser user = new GyhUser("throwAnExceptionWithNewTransaction");
        userMapper.saveUser(user);
        throw new GyhException();
    }
}
