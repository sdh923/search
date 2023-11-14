package com.klic.search.service.common;

public class WNCollection {
	/**
    *  subject: 검색 환경 설정 페이지
    **/
    //final public static int CONNECTION_TIMEOUT = 20000;
	final public static String CHARSET = "UTF-8";
	final public static int REALTIME_COUNT=100;
	final public static int PAGE_SCALE = 10; //view page list count

	final public static int CONNECTION_KEEP = 0; //recevive mode
	final public static int CONNECTION_REUSE = 2;
	final public static int CONNECTION_CLOSE = 3;

	final public static int ASC = 0; //order
	final public static int DESC = 1; //order

	final public static int USE_KMA_OFFOFF = 0; //synonym, morpheme
	final public static int USE_KMA_ONON = 1;
	final public static int USE_KMA_ONOFF = 2;
	
	final public static int USE_RESULT_STRING = 0; //result data type	
	final public static int USE_RESULT_XML = 1;
	final public static int USE_RESULT_JSON = 2;
	final public static int USE_RESULT_DUPLICATE_STRING = 3; //uid result data type	
	final public static int USE_RESULT_DUPLICATE_XML = 4;
	final public static int USE_RESULT_DUPLICATE_JSON = 5;

	final public static int IS_CASE_ON = 1; //case on, off
	final public static int IS_CASE_OFF = 0;

	final public static int HI_SUM_OFFOFF = 0; //summarizing, highlighting
	final public static int HI_SUM_OFFON = 1;
	final public static int HI_SUM_ONOFF = 2;
	final public static int HI_SUM_ONON = 3;
			
	final public static int COMMON_OR_WHEN_NORESULT_OFF = 0;
	final public static int COMMON_OR_WHEN_NORESULT_ON = 1;

	final public static int INDEX_NAME = 0;
	final public static int COLLECTION_NAME = 1;
	final public static int PAGE_INFO = 2;
	final public static int ANALYZER = 3;
	final public static int SORT_FIELD = 4;
	final public static int RANKING_OPTION = 5;
	final public static int SEARCH_FIELD = 6;
	final public static int RESULT_FIELD = 7;
	final public static int DATE_RANGE = 8;
	final public static int RANK_RANGE = 9;
	final public static int EXQUERY_FIELD = 10;
	final public static int COLLECTION_QUERY =11;
	final public static int BOOST_QUERY =12;
	final public static int FILTER_OPERATION = 13;
	final public static int GROUP_BY = 14;
	final public static int GROUP_SORT_FIELD = 15;
	final public static int CATEGORY_BOOST = 16;
	final public static int CATEGORY_GROUPBY = 17;
	final public static int CATEGORY_QUERY = 18;
	final public static int PROPERTY_GROUP = 19;
	final public static int PREFIX_FIELD = 20;
	final public static int FAST_ACCESS = 21;
	final public static int MULTI_GROUP_BY = 22;
	final public static int AUTH_QUERY = 23;
	final public static int DEDUP_SORT_FIELD = 24;
	final public static int COLLECTION_KOR = 25;
	final public static int DISPPLAY_RESULT_FIELD = 26;
	
	final public static int MERGE_COLLECTION_NAME = 0;
	final public static int MERGE_MAPPING_COLLECTION_NAME = 1;
	final public static int MERGE_PAGE_INFO = 2;
	final public static int MERGE_RESULT_FIELD = 3;
	final public static int MERGE_MAPPING_RESULT_FIELD = 4;
	final public static int MERGE_MULTI_GROUP_BY_FIELD = 5;
	final public static int MERGE_MAPPING_MULTI_GROUP_BY_FIELD = 6;
	final public static int MERGE_CATEGORY_GROUPBY_FIELD = 7;
	final public static int MERGE_MAPPING_CATEGORY_GROUPBY_FIELD = 8;

	//가상 통합 컬렉션을 사용하지 않을 경우 아래와 같이MERGE_COLLECTIONS에 정의한다.
	public String[] mergeCollections = new String[]{""};

	
	public String[] collections = new String[]{"webpage","board","file","menu"};
	public String[] collectionsName = new String[]{"webpage","board","file","menu"};
	
	public String[][] mergeCollectionInfo= null;
	public String[][] collectionInfo = null;
	WNCollection(){
		collectionInfo = new String[][]
		{
			{
				"webpage", // set index name
				"webpage", // set collection name
				"0,3",  // set pageinfo (start,count)
				"1,0,1,1,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
				"RANK/DESC,DATE/DESC",  // set sort field (field,order) multi sort '/'
				"basic,rpfmo,100",  // set sort field (field,order) multi sort '/'
				"SYS_NM,CONTENT,UPPER_MENU_NM,MENU_NM,KEYWORD",// set search field
				"DOCID,SYS_ID,SYS_NM,SYS_TY,CONTENT,DATE,UPPER_MENU_NM,MENU_ID,MENU_NM,CREATE_DT,UPDT_DT,URL,ALIAS,TERMS,TOPIC,KEYWORD",// set document field
				"", // set date range
				"", // set rank range
				"", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1,  (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
				"", // set collection query (<fieldname:contains:value^weight | value^weight>/option...) and ' ', or '|'
				"", // set boost query (<fieldname:contains:value> | <field3:contains:value>...) and ' ', or '|'
				"", // set filter operation (<fieldname:operator:value>)
				"", // set groupby field(field, count)
				"", // set sort field group(field/order,field/order,...)
				"", // set categoryBoost(fieldname,matchType,boostID,boostKeyword)
				"", // set categoryGroupBy (fieldname:value)
				"", // set categoryQuery (fieldname:value)
				"", // set property group (fieldname,min,max, groupcount)
				"SYS_TY,ALIAS", // use check prefix query filed
				"CREATE_DT,UPDT_DT,TERMS,TOPIC", // set use check fast access field
				"", // set multigroupby field
				"", // set auth query (Auth Target Field, Auth Collection, Auth Reference Field, Authority Query)
				"", // set Duplicate Detection Criterion Field, RANK/DESC,DATE/DESC
				"webpage", // collection display name
				"DOCID,SYS_ID,SYS_NM,SYS_TY,CONTENT,DATE,UPPER_MENU_NM,MENU_ID,MENU_NM,CREATE_DT,UPDT_DT,URL,ALIAS,TERMS,TOPIC,KEYWORD"
				}
	         ,
				{
				"board", // set index name
				"board", // set collection name
				"0,3",  // set pageinfo (start,count)
				"1,0,1,1,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
				"RANK/DESC,DATE/DESC",  // set sort field (field,order) multi sort '/'
				"basic,rpfmo,100",  // set sort field (field,order) multi sort '/'
				"SYS_NM,UPPER_MENU_NM,MENU_NM,TITLE,CONTENT",// set search field
				"DOCID,SYS_ID,SYS_NM,SYS_TY,DATE,UPPER_MENU_NM,MENU_ID,MENU_NM,BBS_ID,SN,TITLE,CONTENT,REG_NM,CREATE_DT,URL,ALIAS,TERMS,TOPIC,KEYWORD",// set document field
				"", // set date range
				"", // set rank range
				"", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1,  (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
				"", // set collection query (<fieldname:contains:value^weight | value^weight>/option...) and ' ', or '|'
				"", // set boost query (<fieldname:contains:value> | <field3:contains:value>...) and ' ', or '|'
				"", // set filter operation (<fieldname:operator:value>)
				"", // set groupby field(field, count)
				"", // set sort field group(field/order,field/order,...)
				"", // set categoryBoost(fieldname,matchType,boostID,boostKeyword)
				"", // set categoryGroupBy (fieldname:value)
				"", // set categoryQuery (fieldname:value)
				"", // set property group (fieldname,min,max, groupcount)
				"SYS_TY,ALIAS", // use check prefix query filed
				"CREATE_DT,TERMS,TOPIC", // set use check fast access field
				"", // set multigroupby field
				"", // set auth query (Auth Target Field, Auth Collection, Auth Reference Field, Authority Query)
				"", // set Duplicate Detection Criterion Field, RANK/DESC,DATE/DESC
				"board", // collection display name
				"DOCID,SYS_ID,SYS_NM,SYS_TY,DATE,UPPER_MENU_NM,MENU_ID,MENU_NM,BBS_ID,SN,TITLE,CONTENT,REG_NM,CREATE_DT,URL,ALIAS,TERMS,TOPIC,KEYWORD",// set document field
				}
	         ,
				{
				"file", // set index name
				"file", // set collection name
				"0,3",  // set pageinfo (start,count)
				"1,0,1,1,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
				"RANK/DESC,DATE/DESC",  // set sort field (field,order) multi sort '/'
				"basic,rpfmo,100",  // set sort field (field,order) multi sort '/'
				"SYS_NM,UPPER_MENU_NM,MENU_NM,FILE_NAME,FILE_CONTENT",// set search field
				"DOCID,SYS_ID,SYS_NM,SYS_TY,DATE,UPPER_MENU_NM,MENU_ID,MENU_NM,BBS_ID,SN,FILE_NAME,FILE_CONTENT,REG_NM,CREATE_DT,URL,FILE_URL,FILE_SIZE,FILE_EXT,ALIAS,TERMS,TOPIC,KEYWORD",// set document field
				"", // set date range
				"", // set rank range
				"", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1,  (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
				"", // set collection query (<fieldname:contains:value^weight | value^weight>/option...) and ' ', or '|'
				"", // set boost query (<fieldname:contains:value> | <field3:contains:value>...) and ' ', or '|'
				"", // set filter operation (<fieldname:operator:value>)
				"", // set groupby field(field, count)
				"", // set sort field group(field/order,field/order,...)
				"", // set categoryBoost(fieldname,matchType,boostID,boostKeyword)
				"", // set categoryGroupBy (fieldname:value)
				"", // set categoryQuery (fieldname:value)
				"", // set property group (fieldname,min,max, groupcount)
				"SYS_TY,ALIAS", // use check prefix query filed
				"CREATE_DT,TERMS,TOPIC", // set use check fast access field
				"", // set multigroupby field
				"", // set auth query (Auth Target Field, Auth Collection, Auth Reference Field, Authority Query)
				"", // set Duplicate Detection Criterion Field, RANK/DESC,DATE/DESC
				"file", // collection display name
				"DOCID,SYS_ID,SYS_NM,SYS_TY,DATE,UPPER_MENU_NM,MENU_ID,MENU_NM,BBS_ID,SN,FILE_NAME,FILE_CONTENT,REG_NM,CREATE_DT,URL,FILE_URL,FILE_SIZE,FILE_EXT,ALIAS,TERMS,TOPIC,KEYWORD",// set document field
				}
	         ,
				{
				"menu", // set index name
				"menu", // set collection name
				"0,3",  // set pageinfo (start,count)
				"1,0,1,1,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
				"RANK/DESC,DATE/DESC",  // set sort field (field,order) multi sort '/'
				"basic,rpfmo,100",  // set sort field (field,order) multi sort '/'
				"SYS_NM,UPPER_MENU_NM,MENU_NM,KEYWORD",// set search field
				"DOCID,SYS_ID,SYS_NM,SYS_TY,DATE,UPPER_MENU_NM,MENU_ID,MENU_NM,CREATE_DT,UPDT_DT,URL,ALIAS,TERMS,TOPIC,KEYWORD",// set document field
				"", // set date range
				"", // set rank range
				"", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1,  (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
				"", // set collection query (<fieldname:contains:value^weight | value^weight>/option...) and ' ', or '|'
				"", // set boost query (<fieldname:contains:value> | <field3:contains:value>...) and ' ', or '|'
				"", // set filter operation (<fieldname:operator:value>)
				"", // set groupby field(field, count)
				"", // set sort field group(field/order,field/order,...)
				"", // set categoryBoost(fieldname,matchType,boostID,boostKeyword)
				"", // set categoryGroupBy (fieldname:value)
				"", // set categoryQuery (fieldname:value)
				"", // set property group (fieldname,min,max, groupcount)
				"SYS_TY,ALIAS", // use check prefix query filed
				"CREATE_DT,UPDT_DT,TERMS,TOPIC", // set use check fast access field
				"", // set multigroupby field
				"", // set auth query (Auth Target Field, Auth Collection, Auth Reference Field, Authority Query)
				"", // set Duplicate Detection Criterion Field, RANK/DESC,DATE/DESC
				"menu", // collection display name
				"DOCID,SYS_ID,SYS_NM,SYS_TY,DATE,UPPER_MENU_NM,MENU_ID,MENU_NM,CREATE_DT,UPDT_DT,URL,ALIAS,TERMS,TOPIC,KEYWORD",// set document field
				}
		};
	}
}
