/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matkansuunnittelija;

/**
 *
 * @author Samuli
 */
public enum StatusCode {

    STATUS_TRAVEL_PLAN_CREATE_FAIL_ALREADY_EXISTS,
    STATUS_TRAVEL_PLAN_CREATE_FAIL_FILE_NOT_FOUND,
    STATUS_TRAVEL_PLAN_CREATE_SUCCEED,
    STATUS_TRAVEL_PLAN_CREATE_FAIL_DATE_DIFFERENCE_TOO_LONG,
    STATUS_TRAVEL_PLAN_CREATE_FAIL_START_DATE_AFTER_END_DATE,
    STATUS_TRAVEL_PLAN_INFO_OK
}
