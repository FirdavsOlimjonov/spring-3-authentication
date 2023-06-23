package com.alibou.security.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@Getter
public enum PermissionEnum implements GrantedAuthority {
    ADD_ORDER(PageEnum.ORDERS),
    EDIT_ORDER(PageEnum.ORDERS),
    DELETE_ORDER(PageEnum.ORDERS),
    GET_ORDER(PageEnum.ORDERS),
    GET_ORDERS(PageEnum.ORDERS),

    ADD_ROLE(PageEnum.ROLE),
    GET_ROLE(PageEnum.ROLE),
    EDIT_ROLE(PageEnum.ROLE),
    GET_ALL_ROLES(PageEnum.ROLE),
    DELETE_ROLE(PageEnum.ROLE),

    ADD_EMPLOYEE(PageEnum.EMPLOYEE),
    GET_EMPLOYEES(PageEnum.EMPLOYEE),
    GET_EMPLOYEE(PageEnum.EMPLOYEE),
    EDIT_EMPLOYEE(PageEnum.EMPLOYEE),
    DELETE_EMPLOYEE(PageEnum.EMPLOYEE),
    ADD_EMPLOYEE_ACTIVE_HOTEL(PageEnum.MYHOTEL),
    RESET_PASSWORD_BY_ADMIN(PageEnum.EMPLOYEE),

    ADD_HOTEL(PageEnum.HOTELS),
    EDIT_HOTEL(PageEnum.HOTELS),
    DELETE_HOTEL(PageEnum.HOTELS),
    GET_HOTEL(PageEnum.HOTELS),
    GET_ALL_HOTELS(PageEnum.HOTELS),

    UPDATE_HOTEL(PageEnum.MYHOTEL),

    GET_ALL_CATEGORIES_OF_HOTEL(PageEnum.CATEGORY), GET_ALL_CATEGORIES(PageEnum.CATEGORY),
    ADD_CATEGORY(PageEnum.CATEGORY), EDIT_CATEGORY(PageEnum.CATEGORY), DELETE_CATEGORY(PageEnum.CATEGORY),
    ADD_AMENITY(PageEnum.AMENITY), EDIT_AMENITY(PageEnum.AMENITY), GET_AMENITY(PageEnum.AMENITY), GET_ALL_AMENITY(PageEnum.AMENITY),
    ADD_RATE(PageEnum.RATE) , EDIT_RATE(PageEnum.RATE), GET_RATE(PageEnum.RATE) , GET_ALL_RATE(PageEnum.RATE), DELETE_RATE(PageEnum.RATE), GET_CATEGORY_FORM(PageEnum.CATEGORY),

    ADD_PROMO_CODE(PageEnum.PROMO_CODE), GET_PROMO_CODE(PageEnum.PROMO_CODE), DELETE_PROMO_CODE(PageEnum.PROMO_CODE),

    UPDATE_CALENDAR_RATE(PageEnum.PRICES), GET_CALENDAR_RATES(PageEnum.PRICES);

    private final PageEnum page;

    @Override
    public String getAuthority() {
        return name();
    }
}
