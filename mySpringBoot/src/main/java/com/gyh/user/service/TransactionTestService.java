package com.gyh.user.service;

/**
 * @author guoyanhong
 * @date 2018/9/11 15:47
 */
public interface TransactionTestService {

    void rollbackForException(int type);

    void throwAnExceptionNoTransaction();

    void throwAnExceptionWithYouTransaction();
    void noExceptionWithYouTransaction();

    void throwAnExceptionWithNewTransaction();
}
