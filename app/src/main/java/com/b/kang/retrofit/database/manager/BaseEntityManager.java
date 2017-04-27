package com.b.kang.retrofit.database.manager;

import com.b.kang.retrofit.database.dao.GreenDaoManager;

/**
 * Created by kang on 17-4-27.
 */
public class BaseEntityManager {
    protected GreenDaoManager greenDaoManager;

    protected BaseEntityManager() {
        greenDaoManager = GreenDaoManager.getInstance();
    }
}
