package com.test.mylibrary.new_utils.permission;

import java.util.List;

/**
 * 权限请求结果回调
 * @author Wang
 * Created on 2019-12-05 14:33
 */
public interface PermissionCallBack {
    /**
     * 返回所有允许的权限集合
     * @param grantedPerms 允许权限的集合
     */
    void granted(List<String> grantedPerms);

    /**
     * 返回所有不允许权限的集合
     * @param deniedPerms 不允许权限的集合
     */
    void denied(List<String> deniedPerms);
}
