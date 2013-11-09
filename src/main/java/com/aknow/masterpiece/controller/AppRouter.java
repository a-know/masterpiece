package com.aknow.masterpiece.controller;

import org.slim3.controller.router.RouterImpl;

public class AppRouter extends RouterImpl {

    public AppRouter() {
        addRouting("/user/{loginID}", "/user?loginID={loginID}&function=top");
        addRouting("/user/{loginID}/page/{pageCount}", "/user?loginID={loginID}&function=page&pageCount={pageCount}");
        addRouting("/user/{loginID}/item/{itemKey}", "/user?loginID={loginID}&function=item&itemKey={itemKey}");
        addRouting("/user/{loginID}/edit/editItem", "/editItem");
        addRouting("/user/{loginID}/edit/{itemKey}", "/user?loginID={loginID}&function=edit&itemKey={itemKey}");
        addRouting("/user/{loginID}/list", "/user?loginID={loginID}&function=list&pageNo=1");
        addRouting("/user/{loginID}/list/{pageNo}", "/user?loginID={loginID}&function=list&pageNo={pageNo}");
        addRouting("/user/{loginID}/{function}", "/user?loginID={loginID}&function={function}");
        addRouting("/search/category/{categoryCode}", "/categorySearch?categoryCode={categoryCode}&pageNo=1");
        addRouting("/search/category/{categoryCode}/{pageNo}", "/categorySearch?categoryCode={categoryCode}&pageNo={pageNo}");
        addRouting("/searchUser", "/user?function=top");
    }
}