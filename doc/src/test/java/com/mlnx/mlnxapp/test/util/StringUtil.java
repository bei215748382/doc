package com.mlnx.mlnxapp.test.util;

public class StringUtil {
	public static String url_base = "http://localhost:8080/doc/";

	/**
	 * 医生相关
	 */
	private static String doctors_base = url_base + "doctors/";
	// GET
	public static String doctors_all = doctors_base + "all";
	public static String doctors_find_by_hosptial_id = doctors_base + "find/hospital/{id}";
	public static String doctors_find_by_id = doctors_base + "find/{id}";
	//POST
	public static String doctors_register = doctors_base + "register";
	public static String doctors_find_by_name = doctors_base + "find/name";
	public static String doctors_find_by_phone = doctors_base + "find/phone";
	
	/**
	 * 省管理
	 */
	private static String provinces_base = url_base + "provinces/";
	//POST
	public static String provinces_register = provinces_base+"register";
	//GET
	public static String provinces_find_by_name = provinces_base+"find/name";
	
	/**
	 * 城市管理
	 */
	private static String cities_base = url_base + "cities/";
	//POST
	public static String cities_register = cities_base+"register";
	//GET
	public static String cities_find_by_name = cities_base+"find/name";
	public static String cities_find_by_province_id = cities_base+"find/province/{id}";
	
	/**
	 * 医院管理
	 */
	private static String hospitals_base = url_base + "hospitals/";
	//POST
	public static String hospitals_register = hospitals_base+"register";
	//GET
	public static String hospitals_find_by_name = hospitals_base+"find/name";
	public static String hospitals_find_by_city_id = hospitals_base+"find/province/{id}";
	
	
	
}
