package com.velvet.dao;

import java.util.List;
import java.util.Map;

import com.velvet.constants.IApplicationConstants;
import com.velvet.vo.Bill;
import com.velvet.vo.NameValuePairBean;
import com.velvet.vo.Order;
import com.velvet.vo.PartAvailabilityBean;
import com.velvet.vo.Parts;
import com.velvet.vo.PartsSearchBean;
import com.velvet.vo.PartsSearchResultsBean;

public interface IPartsManagementDao {
	
	
	public static final String INSERT_PARTS_QUERY = new StringBuilder("INSERT INTO parts_master(parts_name,parts_code,parts_threshold,parts_quantity) VALUES(?,?,?,?)").toString();
	
	public static final String GET_PARTS_CATEGORY_QUERY = new StringBuilder("SELECT category_sl_no as 'code',category_code as 'desc',category_name as 'name' FROM tbl_part_category_master where logical_delete = 'N' order by upper(category_name) ").toString();
	
	public static final String GET_PARTS_QUERY = new StringBuilder("SELECT prts.part_sl_no as 'code',prts.part_code as 'desc',concat(prts.part_name,'_(',ctgry.category_name,')') as 'name' FROM tbl_part_master prts,tbl_part_category_master ctgry ")
	.append(" where ctgry.category_sl_no = prts.category_sl_no and prts.logical_delete = 'N'")
	.append(" order by upper(part_name)")
	.toString();
	
	public static final String SQL_GET_PARTS_INFO = new StringBuilder("select * ")
																.append("from tbl_part_master where part_sl_no = :partId and logical_delete = 'N'").toString();
	
	public void addParts(Parts parts) throws Exception;
	
	public List<NameValuePairBean> getCategoryList() throws Exception;
	
	public List<NameValuePairBean> getPartList() throws Exception;
	
	public Parts getPartsInfo(String partsId) throws Exception;
	
	public static final String SQL_GET_PARTS_QNTY_INFO = new StringBuilder("select part_quantity as 'quantity' from tbl_part_master where part_sl_no = :partId and logical_delete = 'N'").toString();
	
	public static final String SQL_UPDATE_PARTS_QUANTITY  = new StringBuilder("update tbl_part_master set part_quantity  = :partQnty where part_sl_no = :partId").toString();

	public static final String GET_PARTS_QUERY_BY_CATEGORY = new StringBuilder("SELECT prts.part_sl_no as 'code',prts.part_code as 'desc',concat(prts.part_name,'_(',ctgry.category_name,')') as 'name' FROM tbl_part_master prts,tbl_part_category_master ctgry ")
	.append(" where ctgry.category_sl_no = prts.category_sl_no")
	.append(" and IF(:categoryId IS NULL, TRUE , prts.category_sl_no = :categoryId)")
	.append(" and prts.logical_delete = 'N'")
	.append(" order by upper(part_name)")
	.toString();
	
	
	public static final String GET_PARTS_QUERY_BY_PATTERN = new StringBuilder("SELECT prts.part_sl_no as 'code',prts.part_code as 'desc',concat(prts.part_name,'_(',ctgry.category_name,')') as 'name' FROM tbl_part_master prts,tbl_part_category_master ctgry ")
	.append(" where IF(:categoryId IS NULL, TRUE , ctgry.category_sl_no = :categoryId) and ctgry.category_sl_no = prts.category_sl_no and prts.logical_delete = 'N' and prts.part_name like '%").append(IApplicationConstants.PART_PATTERN_STRING).append("%'")
	.append(" order by upper(part_name)")
	.toString();
	
	public static final String INSERT_PARTS_STAG_QUERY = new StringBuilder("INSERT INTO tbl_part_stage(part_sl_no,part_quantity,created_on,operation,part_threshold) VALUES(:partId,:partQnty,:created_on,:operation,:threshold);").toString();
	
	public static final String GET_ORDERS_LIST = new StringBuilder("SELECT concat(client_name,'_',order_sl_no) as 'name',order_sl_no as 'code' FROM velvet_db.tbl_order where logical_delete = 'N' and is_bill_generated = 'N'").toString();
	
	public static final String GET_ORDER_INFO = new StringBuilder("SELECT mstr.item_sl_no as 'itemId',trans.item_quantity as 'item_quantity',mstr.item_code as 'code',")
	.append(" mstr.item_price as 'item_price',concat(mstr.item_name,'_',ctgry.category_name) as 'item_name',ord.client_name as 'client',")
	.append(" DATE_FORMAT(ord.order_date,'%Y-%m-%d') as 'order_date'")
	.append(" FROM velvet_db.tbl_item_transaction trans,tbl_item_master mstr,tbl_order ord,tbl_item_category_master ctgry")
	.append(" where mstr.item_sl_no = trans.item_sl_no ")
	.append(" and ctgry.category_sl_no = mstr.category_sl_no")
	.append(" and ord.order_sl_no = trans.order_sl_no")
	.append(" and ord.order_sl_no = :orderId ")
	.append(" order by trans.item_transaction_sl_no").toString();
	
	public static final String INSERT_BILL_DETAILS = new StringBuilder("INSERT INTO `tbl_bill`(order_sl_no,bill_date,bill_amount,tax_amount,discount_amount,created_by,created_on,logical_delete) VALUES (:orderId,:billDate,:totalAmnt,:taxAmount,:discAmount,'admin',now(),'N');").toString();
	
	public static final String GET_BILL_ID = new StringBuilder("SELECT bill_sl_no FROM tbl_bill where order_sl_no = :orderId and logical_delete='N'").toString();
	
	public static final String SQL_UPDATE_ORDER_BILL_FLAG  = new StringBuilder("update tbl_order set is_bill_generated ='Y' where order_sl_no = :orderId").toString();
	
	public static final String SQL_GET_BILL_DETAILS = new StringBuilder("select bill_sl_no,order_sl_no,DATE_FORMAT(bill_date,'%Y-%m-%d') as 'bill_date',bill_amount,tax_amount,discount_amount from tbl_bill where bill_sl_no = :billId and logical_delete = 'N'").toString();
	
	public static final String SQL_CANCEL_ORDER  = new StringBuilder("update tbl_order set logical_delete  = 'Y' where order_sl_no = :orderId").toString();
	
	public static final String SQL_PART_SEARCH_QUERY =  new StringBuilder(" SELECT DATE_FORMAT(trans.created_on,'%Y-%m-%d') as date,")
	.append(" concat(mstr.part_name,'_(',ctgry.category_name,')') as partName,mstr.part_code as partCode,")
	.append(" trans.part_quantity as quantity,trans.operation as operation")
	.append(" FROM tbl_part_stage trans,tbl_part_category_master ctgry,tbl_part_master mstr")
	.append(" where IF(:categoryId IS NULL, TRUE , ctgry.category_sl_no = :categoryId)")
	.append(" and ctgry.category_sl_no = mstr.category_sl_no")
	.append(" and mstr.part_sl_no = trans.part_sl_no")
	.append(" and IF(:partId IS NULL, TRUE , mstr.part_sl_no = :partId)")
	.append(" and (trans.created_on between IF(:fromDate is NULL,'2011-01-01',:fromDate) and IF(:toDate is NULL,now(),:toDate))")
	.append(" order by mstr.part_name").toString();
	
	public static final String SQL_PART_THRESHOLD_SEARCH_QUERY =  new StringBuilder(" SELECT DATE_FORMAT(mstr.created_on,'%Y-%m-%d') as date,")
	.append(" concat(mstr.part_name,'_(',ctgry.category_name,')') as partName,mstr.part_code as partCode,")
	.append(" mstr.part_threshold as partThreshold,mstr.part_quantity as avQuantity")
	.append(" FROM tbl_part_category_master ctgry,tbl_part_master mstr")
	.append(" where IF(:categoryId IS NULL, TRUE , ctgry.category_sl_no = :categoryId)")
	.append(" and ctgry.category_sl_no = mstr.category_sl_no")
	.append(" and IF(:partId IS NULL, TRUE , mstr.part_sl_no = :partId)")
	.append(" and mstr.part_quantity < mstr.part_threshold")
	.append(" order by mstr.part_name").toString();
	
	public List<NameValuePairBean> getOrderList() throws Exception;
	
	public List<NameValuePairBean> getPartListByPattern(String patternId,String categoryId) throws Exception;
	
	
	public int getPartsQnty(Parts parts) throws Exception;
	
	public int submitAddDeleteParts(Parts parts,String action,String updatedQnty,String date) throws Exception;
	
	public List<NameValuePairBean> getPartListByCategory(String categoryId) throws Exception;
	
	public Order getOrdersInfo(String orderId) throws Exception;
	
	public Bill insertBillDetails(Bill bill) throws Exception;
	
	public Bill getBillInfo(String billId) throws Exception;
	
	public Map<String, String> fetchItemOrderQuantityMap(String orderId) throws Exception;
	
	public void placeOrderForItems(String orderId, Map<String, PartAvailabilityBean> partAvailabilityMap) throws Exception;
	
	public List<PartsSearchResultsBean> getPartsSearchResults(PartsSearchBean partBean) throws Exception;
	
	public List<PartsSearchResultsBean> getPartsThresholdResults(PartsSearchBean partBean) throws Exception;
	
}
